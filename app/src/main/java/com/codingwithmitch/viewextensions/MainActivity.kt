package com.codingwithmitch.viewextensions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupDemo()
    }

    private fun setupDemo(){
        success_toast.setOnClickListener {
            displayToast("You successfully did that thing!")
        }

        error_toast.setOnClickListener {
            displayToast("Something went wrong...")
        }

        success_dialog.setOnClickListener {
            displaySuccessDialog("You successfully did that thing!")
        }

        error_dialog.setOnClickListener {
            displayErrorDialog("Something went wrong...")
        }

        are_you_sure.setOnClickListener {
            val areYouSureCallback = object: AreYouSureCallback{
                override fun proceed() {
                    displayToast("You successfully did that thing!")
                }

                override fun cancel() {
                    displayToast("Cancelled.")
                }
            }
            areYouSureDialog(
                "Are you sure you want to do that? This can't be un-done.",
                areYouSureCallback
            )
        }
    }


    fun displayToast(message:String?){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    fun displaySuccessDialog(message: String){
        MaterialDialog(this)
            .show{
                title(R.string.text_success)
                message(text = message)
                positiveButton(R.string.text_ok)
            }
    }

    fun displayErrorDialog(errorMessage: String){
        MaterialDialog(this)
            .show{
                title(R.string.text_error)
                message(text = errorMessage)
                positiveButton(R.string.text_ok)
            }
    }

    fun areYouSureDialog(message: String, callback: AreYouSureCallback){
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

}
















