package id.man.story.presentation.extentions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
fun <T> LifecycleOwner.observeData(observable : LiveData<T>, observer : (T) -> Unit){
    observable.observe(this, {
        observer(it)
    })
}