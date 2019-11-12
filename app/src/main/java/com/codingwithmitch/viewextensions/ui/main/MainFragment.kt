package com.codingwithmitch.viewextensions.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.viewextensions.R
import com.codingwithmitch.viewextensions.ui.AreYouSureCallback
import com.codingwithmitch.viewextensions.ui.UICommunicationListener
import com.codingwithmitch.viewextensions.ui.UIMessage
import com.codingwithmitch.viewextensions.ui.UIMessageType
import kotlinx.android.synthetic.main.main_fragment.*
import java.lang.ClassCastException

class MainFragment : Fragment() {

    private val TAG: String = "AppDebug"

    lateinit var uiCommunicationListener: UICommunicationListener

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        subscribeObservers()
        setupDemo()
    }

    private fun setupDemo(){
        success_toast.setOnClickListener {
            viewModel.setEventValue(1)
        }

        error_toast.setOnClickListener {
            viewModel.setEventValue(2)
        }

        success_dialog.setOnClickListener {
            viewModel.setEventValue(3)
        }

        error_dialog.setOnClickListener {
            viewModel.setEventValue(99)
        }

        are_you_sure.setOnClickListener {
            val areYouSureCallback = object: AreYouSureCallback{
                override fun proceed() {
                    viewModel.setEventValue(1)
                }

                override fun cancel() {
                    // do nothing
                }
            }
            uiCommunicationListener.onUIMessageReceived(
                UIMessage(
                    "",
                    UIMessageType.AreYouSureDialog(areYouSureCallback)
                )
            )
        }
    }

    private fun subscribeObservers(){
        viewModel.dataObj.observe(viewLifecycleOwner, Observer { uiMessage ->
            uiCommunicationListener.onUIMessageReceived(uiMessage)
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            uiCommunicationListener = context as UICommunicationListener
        }catch (e: ClassCastException){
            Log.e(TAG, "ClassCastException: ${e.message}")
        }
    }
}


















