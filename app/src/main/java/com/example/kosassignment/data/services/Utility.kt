package com.example.kosassignment.data.services

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout


object Utility {

    private var progressBar: ProgressBar? = null


    // show progressbar
    fun Context.showProgressBar() {
        try {
                val layout = (this as? Activity)?.findViewById<View>(android.R.id.content)?.rootView as? ViewGroup

                progressBar = ProgressBar(this, null, R.attr.progressBarStyleLarge)
                progressBar?.let { it1 ->
                    it1.isIndeterminate = true

                    val params = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                    )

                    val rl = RelativeLayout(this)

                    rl.gravity = Gravity.CENTER
                    rl.addView(it1)

                    layout?.addView(rl, params)

                    it1.visibility = View.VISIBLE
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // hide progressbar
    fun hideProgressBar() {
        try {
            progressBar?.let {
                it.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}