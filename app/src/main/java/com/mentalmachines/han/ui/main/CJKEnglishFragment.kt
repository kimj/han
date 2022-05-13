package com.mentalmachines.han.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
class CJKEnglishFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create a new TextView and set its text to the fragment's section
        // number argument value.
        val textView = TextView(activity)
        textView.gravity = Gravity.CENTER
        textView.text = arguments?.let {
            Integer.toString(
                it.getInt(
                    ARG_SECTION_NUMBER
                )
            )
        }
        return textView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        const val ARG_SECTION_NUMBER = "section_number"
    }
}