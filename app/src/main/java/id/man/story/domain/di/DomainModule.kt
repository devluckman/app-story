package id.man.story.domain.di

import id.man.story.domain.usecase.NewsUseCase
import id.man.story.domain.usecase.NewsUseCaseImpl
import org.koin.dsl.module

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
val domainModule = module {

    factory<NewsUseCase> { NewsUseCaseImpl(get()) }

}