package id.man.story.presentation.di

import id.man.story.presentation.ui.detail.DetailStoryVM
import id.man.story.presentation.ui.main.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
val presentationModule = module {

    viewModel{ MainVM(get()) }

    viewModel{ DetailStoryVM(get()) }

}