package com.codingwithmitch.viewextensions

import android.app.Activity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog


fun Activity.displayToast(message:String?){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}

fun Activity.displaySuccessDialog(message: String){
    MaterialDialog(this)
        .show{
            title(R.string.text_success)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayErrorDialog(errorMessage: String){
    MaterialDialog(this)
        .show{
            title(R.string.text_error)
            message(text = errorMessage)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.areYouSureDialog(message: String, callback: AreYouSureCallback){
    MaterialDialog(this)
        .show{
            title(R.string.are_you_sure)
            message(text = message)
            negativeButton(R.string.text_cancel){
                callback.cancel()
            }
            positiveButton(R.string.text_yes){
                callback.proceed()
            }
        }
}

interface AreYouSureCallback {

    fun proceed()

    fun cancel()
}