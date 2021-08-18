package com.example.myapplication

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class App  : Application(){
    override fun onCreate() {
        super.onCreate()
        val client = OkHttpImagePipelineConfigFactory
            .newBuilder(this, OkHttpClient.Builder().build())
            .setDiskCacheEnabled(true)
            .setDownsampleEnabled(true)
            .setResizeAndRotateEnabledForNetwork(true)
            .build()
        Fresco.initialize(this,client)
    }
}