package id.man.story.presentation.utils

import android.app.Activity
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.google.android.material.progressindicator.LinearProgressIndicator
import id.man.story.R

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
object CommonHelper {

    private var dialog: AlertDialog? = null
    private var progress: LinearProgressIndicator? = null

    fun showProgress(context: Activity) {
        val builder = AlertDialog.Builder(context)
        val view: View = context.layoutInflater.inflate(R.layout.progress_loading, null)
        progress = view.findViewById(R.id.progress_bar)

        builder.setView(view)
        dialog = builder.create()

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog?.setCancelable(false)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog?.show()

    }

    fun setProgress(percent: Int) {
        dialog?.let {
            if (it.isShowing) {
                progress?.progress = percent
            }
        }
    }

    fun hideProgress() {
        dialog?.let {
            if (it.isShowing) {
                dialog?.dismiss()
            }
        }
    }

}