package com.example.mobile_laboratoryproject2.viewModel.video_screen

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.mutableStateOf
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.viewModel.ValidationResult

class VideoViewClient: WebViewClient() {
    val isLoading = mutableStateOf(true)
    val loadingResult = mutableStateOf(
        ValidationResult(true, R.string.ok)
    )

    // Запрет перехода на другие ссылки
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return true;
    }

    // Загрузка страницы
    override fun onPageCommitVisible(view: WebView?, url: String?) {
        isLoading.value = false
    }

    // Ошибка сети
    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        loadingResult.value = ValidationResult(false, R.string.no_connection)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        loadingResult.value = ValidationResult(false, R.string.no_connection)
    }
}