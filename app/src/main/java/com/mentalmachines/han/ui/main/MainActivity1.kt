package com.mentalmachines.cjkdroid

import android.view.Menu
import android.view.View
import com.mentalmachines.hadoken.CJKEnglishFragment
import java.util.*

class MainActivity : FragmentActivity() {
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [android.support.v4.app.FragmentPagerAdapter] derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */
    var mViewPager: ViewPager? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = SectionsPagerAdapter(
            getSupportFragmentManager()
        )

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.pager) as ViewPager?
        mViewPager.setAdapter(mSectionsPagerAdapter)
    }

    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        /* @Override
		public Fragment getItem(int position) {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		} */
        fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            var fragment: Fragment? = Fragment()
            when (position) {
                0 -> fragment = CJKEnglishFragment()
                1 -> fragment = FlashCardFragment()
                2 -> fragment = ThousandCharacterClassicFragment()
                3 -> fragment = VerbConjugationFragment()
                else -> fragment = HaDoKenFragment()
            }
        }

        // Show 3 total pages.
        val count: Int
            get() =// Show 3 total pages.
                3

        fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            when (position) {
                0 -> return getString(R.string.title_section1).toUpperCase(l)
                1 -> return getString(R.string.title_section2).toUpperCase(l)
                2 -> return getString(R.string.title_section3).toUpperCase(l)
            }
            return null
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    class DummySectionFragment : Fragment() {
        fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val rootView: View = inflater.inflate(
                R.layout.fragment_main_dummy,
                container, false
            )
            val dummyTextView: TextView = rootView
                .findViewById<View>(R.id.section_label) as TextView
            dummyTextView.setText(
                Integer.toString(
                    getArguments().getInt(
                        ARG_SECTION_NUMBER
                    )
                )
            )
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            const val ARG_SECTION_NUMBER = "section_number"
        }
    }
}