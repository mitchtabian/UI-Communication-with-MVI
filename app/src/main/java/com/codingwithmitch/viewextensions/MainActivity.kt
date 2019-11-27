package com.codingwithmitch.viewextensions

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initPrefs()

        filter_dialog.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun initPrefs(){
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        editor = preferences.edit()
    }

    fun showFilterDialog(){

        val dialog = MaterialDialog(this)
            .noAutoDismiss()
            .customView(R.layout.layout_filter)

        // set initial preferences
        val filter = preferences.getString(getString(R.string.key_filter), getString(R.string.filter_date))
        if(filter.equals(getString(R.string.filter_date))){
            dialog.findViewById<RadioGroup>(R.id.filter_group).check(R.id.filter_date)
        }
        else{
            dialog.findViewById<RadioGroup>(R.id.filter_group).check(R.id.filter_author)
        }

        val order = preferences.getString(getString(R.string.key_order), getString(R.string.order_asc))
        if(order.equals(getString(R.string.order_asc))){
            dialog.findViewById<RadioGroup>(R.id.order_group).check(R.id.order_asc)
        }
        else{
            dialog.findViewById<RadioGroup>(R.id.order_group).check(R.id.order_desc)
        }

        // get new preferences
        dialog.findViewById<TextView>(R.id.positive_button).setOnClickListener{
            Log.d(TAG, "FilterDialog: apply filter.")

            val selectedFilter = dialog.getCustomView().findViewById<RadioButton>(
                dialog.getCustomView().findViewById<RadioGroup>(R.id.filter_group).checkedRadioButtonId
            )
            val selectedOrder= dialog.getCustomView().findViewById<RadioButton>(
                dialog.getCustomView().findViewById<RadioGroup>(R.id.order_group).checkedRadioButtonId
            )

            editor.putString("filter", selectedFilter.text.toString())
            editor.apply()
            editor.putString("order", selectedOrder.text.toString())
            editor.apply()

            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.negative_button).setOnClickListener {
            Log.d(TAG, "FilterDialog: cancelling filter.")
            dialog.dismiss()
        }

        dialog.show()
    }

}
















