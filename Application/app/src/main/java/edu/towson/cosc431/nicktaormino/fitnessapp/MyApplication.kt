package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.app.Application
import android.os.Bundle

class MyApplication : Application(), Application.ActivityLifecycleCallbacks {
    var isMainActivityVisible:Boolean=false

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }
    //todo2(done)
    override fun onActivityPaused(act: Activity?) {
        if(act is MainActivity) {
            var isMainActivityVisible=false
        }
    }
    //todo3(done)
    override fun onActivityResumed(act: Activity?) {
        if(act is MainActivity) {
            var isMainActivityVisible=true
        }
    }

    override fun onActivityStarted(p0: Activity?) {
    }

    override fun onActivityDestroyed(p0: Activity?) {
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
    }

    override fun onActivityStopped(p0: Activity?) {
    }

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
    }
}