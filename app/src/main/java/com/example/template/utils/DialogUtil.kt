package com.example.template.utils

import android.app.Activity
import android.app.Dialog
import com.example.template.R

object DialogUtil {

    fun createLoaderDialog(activity: Activity): Dialog {
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.dialog_loader)
        return dialog
    }

}