package com.codingwithmitch.viewextensions.ui

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

abstract class BaseActivity : AppCompatActivity(),
    UICommunicationListener
{

    private val TAG: String = "AppDebug"

    override fun onUIMessageReceived(uiMessage: UIMessage) {

        when(uiMessage.uiMessageType){

            is UIMessageType.Loading ->{
                handleProgressBar(true)
            }

            is UIMessageType.AreYouSureDialog -> {
                handleProgressBar(false)
                areYouSureDialog(
                    uiMessage.message,
                    uiMessage.uiMessageType.callback
                )
            }

            is UIMessageType.Toast -> {
                handleProgressBar(false)
                displayToast(uiMessage.message)
            }

            is UIMessageType.DialogSuccess -> {
                handleProgressBar(false)
                displaySuccessDialog(uiMessage.message)
            }

            is UIMessageType.DialogError -> {
                handleProgressBar(false)
                displayErrorDialog(uiMessage.message)
            }

            is UIMessageType.None -> {
                handleProgressBar(false)
                Log.i(TAG, "onUIMessageReceived: ${uiMessage.message}")
            }
        }
    }

    abstract fun handleProgressBar(bool: Boolean)
}















