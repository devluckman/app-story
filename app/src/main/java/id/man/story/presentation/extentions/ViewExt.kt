package id.man.story.presentation.extentions

import android.view.View

/**
 *
 * Created by Lukmanul Hakim on  1/23/2022
 * devs.lukman@gmail.com
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}