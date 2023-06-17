package com.mentalmachines.han.ui.dictionary

import androidx.fragment.app.Fragment

class HaDoKenFragment : Fragment() {

    /*    fun getWikiEMediaPage() {
            val pf = MediaWikiParserFactory()
            val parser: MediaWikiParser = pf.createParser()
            val pp: ParsedPage = parser.parse(pageContent)

            // only the links to other Wikipedia language editions
            for (language in pp.getLanguages()) {
                System.out.println(language.getTarget())
            }

            //get the internal links of each section
            for (section in pp.getSections()) {
                System.out.println("Section: " + section.getTitle())
                for (link in section.getLinks(Link.type.INTERNAL)) {
                    System.out.println("  " + link.getTarget())
                }
            }
            try {
                SimpleWikiHelper.prepareUserAgent(getApplicationContext())
                pageContent = SimpleWikiHelper.getPageContent("" + searchText.text, false)
                Log.w("HadoKen", pageContent)
            } catch (e: ApiException) {
                Log.e("WordWidget", "Couldn't contact API", e)
            } catch (e: ParseException) {
                Log.e("WordWidget", "Couldn't parse API response", e)
            }
            return resultsText.text = pageContent
        }*/

}