package com.jackstraw.jhttp.base

import android.app.Application
import android.content.Context
import com.jackstraw.jackhttp.okhttp3.Https
import com.jackstraw.jackhttp.okhttp3.OkHttpUtils
import com.jackstraw.jhttp.base.api.request.RequestBody
import com.jackstraw.jhttp.base.api.request.ResponseBody
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @date 2018/10/26
 * @author pink-jackstraw
 * @describe
 */
class JApplication : Application() {


    companion object {
        var appContext : Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initOkHttp()
    }

    /**
     * 初始化OkHttp
     */
    private fun initOkHttp() {
        val cache = externalCacheDir
        val cacheSize = 10 * 1024 * 1024
        val sslParams = Https.getSslSocketFactory(null, null, null)
        val okHttpClient = OkHttpClient.Builder()
                //连接超时(单位:秒)
                .connectTimeout(15, TimeUnit.SECONDS)
                //写入超时(单位:秒)
                .writeTimeout(20, TimeUnit.SECONDS)
                //读取超时(单位:秒)
                .readTimeout(20, TimeUnit.SECONDS)
                //webSocket轮训间隔(单位:秒)
                .pingInterval(20, TimeUnit.SECONDS)
                //设置缓存
                .cache(Cache(cache!!.absoluteFile, cacheSize.toLong()))
                //
                .hostnameVerifier { hostname, session -> true }
                //https配置
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build()
        OkHttpUtils.initClient(okHttpClient)
                .buildRequest(RequestBody())
                .buildResponse(ResponseBody())

    }

}
