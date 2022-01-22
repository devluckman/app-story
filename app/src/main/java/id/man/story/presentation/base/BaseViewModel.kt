package id.man.story.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
abstract class BaseViewModel : ViewModel(){

    var errorMessage = MutableLiveData<String>()

}