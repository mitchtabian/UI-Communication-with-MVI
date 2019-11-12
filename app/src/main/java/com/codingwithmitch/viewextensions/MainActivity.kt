package com.codingwithmitch.viewextensions

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setupDemo()
    }

    fun Button.setToastMessageListener(message: String?){
        this.setOnClickListener {
            message?.let {
                displayToast(message)
            }
        }
    }

    fun Button.setDialogMessageListener(message: String, isError: Boolean){
        this.setOnClickListener {
            if(isError) displayErrorDialog(message)
            else displaySuccessDialog(message)
        }
    }

    private fun setupDemo(){
        success_toast.setToastMessageListener("You successfully did that thing!")

        error_toast.setToastMessageListener("Something went wrong...")

        success_dialog.setDialogMessageListener(
            "You successfully did that thing!",
            false
        )

        error_dialog.setDialogMessageListener(
            "Something went wrong...",
            true
        )

        are_you_sure.setOnClickListener {
            val areYouSureCallback = object: AreYouSureCallback{
                override fun proceed() {
                    displayToast("You successfully did that thing!")
                }

                override fun cancel() {
                    displayToast("Cancelled...")
                }
            }
            areYouSureDialog(
                "Are you sure you want to do that? This can't be un-done.",
                areYouSureCallback
            )
        }
    }

}
















