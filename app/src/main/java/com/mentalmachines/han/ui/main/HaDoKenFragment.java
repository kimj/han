package com.mentalmachines.cjkdroid;

import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HaDoKenFragment  extends Fragment {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_ha_do_ken);

		// String sResult = new String();
		final Button searchButton = (Button) findViewById(R.id.search);
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// grab @+id/searchString and send this to the xml request handler
				final EditText searchText = (EditText) findViewById(R.id.searchText);
				final TextView resultsText = (TextView) findViewById(R.id.resultsText);
				String pageContent = null;
				
				// get a ParsedPage object
				MediaWikiParserFactory pf = new MediaWikiParserFactory();
				MediaWikiParser parser = pf.createParser();
				ParsedPage pp = parser.parse(pageContent);
				                
				// only the links to other Wikipedia language editions
				for (Link language : pp.getLanguages()) {
				    System.out.println(language.getTarget());
				}
				    
				//get the internal links of each section
				for (Section section : pp.getSections()){
				    System.out.println("Section: " + section.getTitle());

				    for (Link link : section.getLinks(Link.type.INTERNAL)) {
				        System.out.println("  " + link.getTarget());
				    }
				}

				try {
	                SimpleWikiHelper.prepareUserAgent(getApplicationContext());
	                pageContent = SimpleWikiHelper.getPageContent("" + searchText.getText(), false);
	                Log.w("HadoKen", pageContent);
	            } catch (ApiException e) {
	                Log.e("WordWidget", "Couldn't contact API", e);
	            } catch (ParseException e) {
	                Log.e("WordWidget", "Couldn't parse API response", e);
	            }
	            
				resultsText.setText(pageContent);
			}
		});
	}
}