package com.gtoteck.app.haychongiadung;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.gtoteck.app.util.PreferenceUtil;

public class VictoryActivity extends Activity {

	private Context mContext = this;

	private TextView mTvBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.acitivity_victory);

		PreferenceUtil.removeValue(mContext,
				com.gtotech.app.base.Constans.KEY_INDEX_GIADUNG);

		mTvBack = (TextView) this.findViewById(R.id.tvBack);
		mTvBack.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("InlinedApi")
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(VictoryActivity.this,
						MenuActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TASK
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}
}
