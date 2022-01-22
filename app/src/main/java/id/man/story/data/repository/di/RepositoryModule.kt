package id.man.story.data.repository.di

import id.man.story.data.repository.RepositoryImpl
import id.man.story.domain.repository.Repository
import org.koin.dsl.module


/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */

val repositoryModule = module {

    factory<Repository> { RepositoryImpl(get()) }

}