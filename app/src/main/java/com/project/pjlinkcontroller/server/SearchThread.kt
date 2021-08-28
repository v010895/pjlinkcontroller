package com.project.pjlinkcontroller.server

import android.provider.ContactsContract
import android.util.Log
import com.project.pjlinkcontroller.constant.SEARCH_COMMAND
import com.project.pjlinkcontroller.helper.StringUtil
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class SearchThread(receiveCallback:(String,String)->Unit):Runnable {
    private val udpPort = 4352
    private var isConnected = false
    private var buffer = ByteArray(1024)
    private var receivePacket:DatagramPacket? = null
    private var sendPacket:DatagramPacket? = null
    private var callback:((String, String)->Unit) = receiveCallback;
    private var mStringBuilder:StringBuffer = StringBuffer();
    private var mSearchCallback = receiveCallback
    private val broadcastAddress:InetAddress by lazy{
        InetAddress.getByName("255.255.255.255")
    }
    private val server:DatagramSocket by lazy{
        DatagramSocket(udpPort)
    }

    private val locker = Object()
    fun start(){
        if(isConnected)
            return
        else{
            isConnected = true
            Thread(this).start()
        }
    }
    fun stop(){
        mStringBuilder.setLength(0);
        isConnected = false
    }
    private fun parseResponse(s:String)
    {
        Log.i("myDebug","receive: " + s);
        callback.invoke(s,s);
    }
    override fun run() {
        val sendBytes = StringUtil.hexStringToByte(StringUtil.StringToHexString(SEARCH_COMMAND));
        Log.i("myDebug","send command: " + SEARCH_COMMAND);
        sendPacket = DatagramPacket(sendBytes,0,sendBytes.size,broadcastAddress,udpPort)
        server.send(sendPacket);
        receivePacket = DatagramPacket(buffer,buffer.size)
        while(isConnected)
        {
            server.receive(receivePacket)
            val receiveString = String(receivePacket!!.data,0,receivePacket!!.data.size)
            if(receiveString.equals(SEARCH_COMMAND)) {
                receivePacket?.length = 1024;
                continue;
            }
            mStringBuilder.append(receiveString+"\n");
            mSearchCallback.invoke(mStringBuilder.toString(),mStringBuilder.toString());
            parseResponse(mStringBuilder.toString());
            receivePacket?.length = 1024;
        }
        server.close()
    }
}