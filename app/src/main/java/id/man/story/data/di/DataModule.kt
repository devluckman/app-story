package id.man.story.data.di

import id.man.story.data.network.di.networkModule
import id.man.story.data.repository.di.repositoryModule

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
val dataModule = listOf(
    networkModule,
    repositoryModule
)