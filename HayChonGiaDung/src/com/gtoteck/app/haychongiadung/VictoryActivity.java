package com.gtoteck.app.haychongiadung;

import com.gtotech.app.base.Constans;
import com.gtoteck.app.util.PreferenceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class VictoryActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_victory);
		
		PreferenceUtil.removeValue(this, Constans.KEY_INDEX_GIADUNG);
	}

}
