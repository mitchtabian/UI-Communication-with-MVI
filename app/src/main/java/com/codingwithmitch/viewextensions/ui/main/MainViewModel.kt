package com.codingwithmitch.viewextensions.ui.main

import androidx.lifecycle.*
import com.codingwithmitch.viewextensions.repository.MainRepository
import com.codingwithmitch.viewextensions.ui.UIMessage

class MainViewModel : ViewModel() {

    private val _eventValue: MutableLiveData<Int> = MutableLiveData()

    val dataObj: LiveData<UIMessage> = Transformations
        .switchMap(_eventValue){ eventValue ->
            eventValue?.let {
                handleEvent(it)
            }
        }

    private fun handleEvent(value: Int): LiveData<UIMessage>{
        return when(value){
            1 -> {
                // return successful toast
                MainRepository.getSuccessToastFromApi()
            }

            2 -> {
                // return error toast
                MainRepository.getErrorToastFromApi()
            }

            3 -> {
                // return success dialog
                MainRepository.getSuccessDialogFromApi()
            }

            else -> {
                // return ereror dialog
                MainRepository.getErrorDialogFromApi()
            }
        }
    }

    fun setEventValue(value: Int){
        _eventValue.value = value
    }


}


















