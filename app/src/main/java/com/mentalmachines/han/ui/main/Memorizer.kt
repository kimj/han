package com.mentalmachines.cjkdroid;

import jinn.memorizer.com.R;
import jinn.memorizer.com.R.id;
import jinn.memorizer.com.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Memorizer extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	final ImageButton button_yes = (ImageButton) findViewById(R.id.button_yes);
    	final ImageButton button_no = (ImageButton) findViewById(R.id.button_no);
    	final ImageButton button_maybe = (ImageButton) findViewById(R.id.button_maybe);
    	
        setContentView(R.layout.main);
    }
    
    class buttonClickedListener implements Button.OnClickListener
	{
    	String[] testdeck = new String[4];
    	testdeck[0] = "item1, item2";
    	testdeck[1] = "item3, item4";
    	testdeck[2] = "item5, item6";
    	testdeck[3] = "item7, item8";
    	
    	int x;
    	
    	TextView text1 = (TextView) findViewById(R.id.textView1);
    	TextView text2 = (TextView) findViewById(R.id.textView2);
    	
		public void onClick(View v){
			text1.setText(testdeck[x])
		}
	}
}