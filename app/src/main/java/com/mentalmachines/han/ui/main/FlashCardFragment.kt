package com.mentalmachines.cjkdroid.fragments

import android.support.v4.app.Fragment
import android.view.View

class FlashCardFragment : Fragment() {
    fun onCreateView(
        inflater: LayoutInflater?, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create a new TextView and set its text to the fragment's section
        // number argument value.
        val textView = TextView(getActivity())
        textView.setGravity(Gravity.CENTER)
        textView.setText(
            Integer.toString(
                getArguments().getInt(
                    ARG_SECTION_NUMBER
                )
            )
        )
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