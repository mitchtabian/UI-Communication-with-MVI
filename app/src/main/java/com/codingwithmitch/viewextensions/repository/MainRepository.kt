package com.codingwithmitch.viewextensions.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.codingwithmitch.viewextensions.ui.UIMessage
import com.codingwithmitch.viewextensions.ui.UIMessageType
import kotlinx.coroutines.delay

object MainRepository {

    fun getSuccessToastFromApi(): LiveData<UIMessage> {
        return liveData {
            emit(
                UIMessage(
                    "",
                    UIMessageType.Loading()
                )
            )
            delay(1500)
            emit(
                UIMessage(
                    "You have successfully done that thing!",
                    UIMessageType.Toast()
                )
            )
        }
    }

    fun getErrorToastFromApi(): LiveData<UIMessage> {
        return liveData {
            emit(
                UIMessage(
                    "",
                    UIMessageType.Loading()
                )
            )
            delay(1500)
            emit(
                UIMessage(
                    "Something went wrong...",
                    UIMessageType.Toast()
                )
            )
        }
    }

    fun getSuccessDialogFromApi(): LiveData<UIMessage> {
        return liveData {
            emit(
                UIMessage(
                    "",
                    UIMessageType.Loading()
                )
            )
            delay(1500)
            emit(
                UIMessage(
                    "You have successfully done that thing!",
                    UIMessageType.DialogSuccess()
                )
            )
        }
    }

    fun getErrorDialogFromApi(): LiveData<UIMessage> {
        return liveData {
            emit(
                UIMessage(
                    "",
                    UIMessageType.Loading()
                )
            )
            delay(1500)
            emit(
                UIMessage(
                    "Something went wrong...",
                    UIMessageType.DialogError()
                )
            )
        }
    }
}














