package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.viewModel.video_screen.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val videoModule = module {
    viewModel<VideoViewModel> {
        VideoViewModel()
    }
}