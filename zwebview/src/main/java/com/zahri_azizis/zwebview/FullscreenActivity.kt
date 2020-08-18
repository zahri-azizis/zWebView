package com.zahri_azizis.zwebview

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FullscreenActivity : AppCompatActivity() {
    private lateinit var appSplash: ImageView
    private lateinit var appContent: WebView
    private val hideHandler = Handler()

    @SuppressLint("InlinedApi")
    private val hideSystemUI = Runnable {

        appContent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    @SuppressLint("InlinedApi")
    private val hideSplash = Runnable {
        appSplash.visibility = View.GONE
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.hide()

        appSplash = findViewById(R.id.fullscreen_splash)
        appSplash.visibility = View.VISIBLE

        appContent = findViewById(R.id.fullscreen_content)
        appContent.webViewClient = appWebViewClient()
        setMode()
        appContent.loadUrl("https://m.chaturbate.com")
        //appContent.loadUrl("https://web.whatsapp.com")

        hideHandler.post(hideSystemUI)
        hideHandler.postDelayed(hideSplash,3000)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()

        hideHandler.post(hideSystemUI)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && appContent.canGoBack()) {
            appContent.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    private class appWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading( view: WebView, url: String ): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    fun setMode() {
        appContent.getSettings().setJavaScriptEnabled(true);
        //appContent.getSettings().setUseWideViewPort(true);
        //appContent.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Chrome/84.0.4147.125 Safari/537.36 Firefox/68.0");
        //appContent.getSettings().setGeolocationEnabled(true);
        //appContent.getSettings().setDomStorageEnabled(true);
        //appContent.getSettings().setDatabaseEnabled(true);
        //appContent.getSettings().setSupportMultipleWindows(true);
        //appContent.getSettings().setAppCacheEnabled(true);
        //appContent.getSettings().setNeedInitialFocus(true);
        //appContent.getSettings().setLoadWithOverviewMode(true);
        //appContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        ////appContent.getSettings().setBlockNetworkLoads(true);
        //appContent.getSettings().setBlockNetworkImage(true);
        //appContent.getSettings().setBuiltInZoomControls(true);
        //appContent.setInitialScale(100);
    }
}
