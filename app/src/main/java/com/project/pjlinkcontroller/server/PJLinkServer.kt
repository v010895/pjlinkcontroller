package com.project.pjlinkcontroller.server

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PJLinkServer(searchCallback:(String,String)->Unit) {
    val tcpPort = 4352
    private var password:String?=null
    private var ipAddress:String?=null
    private val searchThread:SearchThread = SearchThread(searchCallback);
    private val mPJLinkQueue: PJLinkQueue by lazy{
        PJLinkQueue();
    }
    private val mPJLinkSocket:PJLinkSocket by lazy{
        PJLinkSocket();
    }

    fun startSearchThread(){
        searchThread.start()
    }
    fun stopSearchThread(){
        searchThread.stop()
    }
    inner class PJLinkSocket{
        private val socket:Socket? = null
        private val executor:ExecutorService = Executors.newSingleThreadExecutor();
        fun sendCommand(command:String)
        {

        }
    }

    inner class PJLinkQueue{
        val mCommandQueue = arrayListOf<PJLinkCommand>()
        private val mCommandRunner: CommandQueueRunner by lazy{
            CommandQueueRunner();
        }
        init{
            mCommandRunner.start()
        }

        @Synchronized
        private fun pop():PJLinkCommand{
            return mCommandQueue.removeFirst();
        }
        @Synchronized
        private fun push(command:PJLinkCommand)
        {
            mCommandQueue.add(command)
        }

       inner class CommandQueueRunner: Thread(){
            override fun run() {
                while(true)
                {
                    if(mCommandQueue.isEmpty())
                    {
                        yield()
                    }
                    else{
                        val command = pop();
                        command
                    }
                }
            }
        }

    }

    inner class PJLinkCommand(command:String){
        private val mCommand = command;

        fun execute()
        {

        }
    }

}