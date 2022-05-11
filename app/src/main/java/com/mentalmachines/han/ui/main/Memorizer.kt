package com.mentalmachines.cjkdroid

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.mentalmachines.han.R

class Memorizer : Activity() {
    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val button_yes: ImageButton = findViewById<View>(R.id.button_yes) as ImageButton
        val button_no: ImageButton = findViewById<View>(R.id.button_no) as ImageButton
        val button_maybe: ImageButton = findViewById<View>(R.id.button_maybe) as ImageButton
        setContentView(R.layout.main)
    }

    internal inner class buttonClickedListener : View.OnClickListener {
        var testdeck = arrayOfNulls<String>(4)
        var x = 0
        var text1: TextView = findViewById<View>(R.id.textView1) as TextView
        var text2: TextView = findViewById<View>(R.id.textView2) as TextView
        override fun onClick(v: View) {
            text1.text = testdeck[x]
        }
    }
}