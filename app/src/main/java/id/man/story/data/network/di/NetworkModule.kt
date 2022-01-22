package id.man.story.data.network.di

import id.man.story.data.network.api.Api
import id.man.story.data.network.utils.buildOkHttpClient
import id.man.story.data.network.utils.buildRetrofit
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
const val CLIENT = "OKHTTP_CLIENT"
const val NAME = "NAME_CLIENT"

val networkModule = module {

    single(named(CLIENT)) { buildOkHttpClient() }
    single(named(NAME)) { buildRetrofit(get(named(CLIENT))) }
    single { provideApi(get(named(NAME))) }

}

private fun provideApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}