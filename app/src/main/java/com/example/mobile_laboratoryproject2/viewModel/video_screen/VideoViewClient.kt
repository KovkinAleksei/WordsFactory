package com.example.mobile_laboratoryproject2.viewModel.video_screen

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class VideoViewClient: WebViewClient() {
    // Запрет перехода на другие ссылки
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return true;
    }
}