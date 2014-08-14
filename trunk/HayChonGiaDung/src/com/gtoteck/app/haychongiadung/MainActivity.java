package com.gtoteck.app.haychongiadung;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtoteck.app.dao.GiaDungEntity;
import com.gtoteck.app.dao.GiaDungImpl;

public class MainActivity extends Activity {

	private Context mContext = this;

	private int mIndex = 0;

	private AQuery mAQuery;

	private GiaDungEntity mGiaDungEntity;

	private GiaDungImpl mGiaDungImpl;
	
	//componets ui
	private Button mBtnSubmit;
	private TextView mTvDesc;
	private ImageView mImgProduct;
	private ImageView mImgBack;
	private ProgressBar mProgressBar;
	private ImageView mImgHelp;
	private ImageView mImgShare;
	private TextView mTvNumber; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		this.initUI();
	}

	private void initUI() {
		//init db
		this.mGiaDungImpl = new GiaDungImpl(mContext);
		
		//init aquery
		this.mAQuery = new AQuery(mContext);
		
		//call question
		this.next();
	}

	private void next() {
		this.mGiaDungEntity = this.mGiaDungImpl
				.getGiaDungEntityByPosition(mIndex);
		
		//increment
		mIndex++;
	}
}
