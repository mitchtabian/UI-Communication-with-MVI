package com.codingwithmitch.viewextensions.ui

data class UIMessage(
    val message: String,
    val uiMessageType: UIMessageType
)

sealed class UIMessageType{

    class Loading: UIMessageType()

    class Toast: UIMessageType()

    class DialogSuccess: UIMessageType()

    class DialogError: UIMessageType()

    class AreYouSureDialog(
        val callback: AreYouSureCallback
    ): UIMessageType()

    class None: UIMessageType()
}

interface AreYouSureCallback {

    fun proceed()

    fun cancel()
}