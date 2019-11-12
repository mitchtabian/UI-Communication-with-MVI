package com.codingwithmitch.viewextensions.ui.main

import android.os.Bundle
import android.view.View
import com.codingwithmitch.viewextensions.R
import com.codingwithmitch.viewextensions.ui.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }


    override fun handleProgressBar(bool: Boolean){
        if(bool) progress_bar.visibility = View.VISIBLE
        else progress_bar.visibility = View.GONE
    }
}
















