package com.gtoteck.app.haychongiadung;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.gtotech.app.base.Constans;
import com.gtoteck.app.dao.GiaDungEntity;

public class WinActivity extends Activity {

	private TextView mTvName;
	private TextView mTvPrice; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_win);
		 

		// pass data
		Bundle bundle = getIntent().getExtras();
		GiaDungEntity dungEntity = (GiaDungEntity) bundle
				.getSerializable(Constans.KEY_GIADUNGENTITY);

		// find id
		mTvName = (TextView) this.findViewById(R.id.tvName);
		mTvPrice = (TextView) this.findViewById(R.id.tvPrice);
		
		// set data
		mTvName.setText(dungEntity.getName());
		mTvPrice.setText(dungEntity.getPrice() + " VND");
		
	}

}
