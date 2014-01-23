package com.compagerd.easy_finance.personal.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class Font {

	public static TextView useHandelGotic(Context context, TextView textview){	
		Typeface font = Typeface.createFromAsset(context.getAssets(), "MYRIADPRO-REGULAR.OTF");  
		textview.setTypeface(font); 
		return textview;
	}
}
