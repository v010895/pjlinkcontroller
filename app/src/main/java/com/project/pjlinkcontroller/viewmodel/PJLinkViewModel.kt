package com.project.pjlinkcontroller.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.pjlinkcontroller.server.PJLinkServer

class PJLinkViewModel: ViewModel(){
    private var isSearch = false
    private var pjLinkServer:PJLinkServer;
    public val searchResult = MutableLiveData<String>()
    init {
        pjLinkServer = PJLinkServer() { s1, s2 ->
            searchResult.postValue(s1);
        }
    }

    fun startSearch(){
        if(isSearch) {
            Log.i("myDebug","searchThread already enable");
            return
        }
        isSearch = true
        pjLinkServer.startSearchThread();
        val timer = object: CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                isSearch = false
                pjLinkServer.stopSearchThread()
                Log.i("myDebug","Stop searchThread")
            }

        }
        timer.start();
    }
}