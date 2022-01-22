package id.man.story

import android.app.Application
import id.man.story.data.di.dataModule
import id.man.story.domain.di.domainModule
import id.man.story.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            modules(dataModule)
            modules(domainModule)
            modules(presentationModule)
        }

    }

}