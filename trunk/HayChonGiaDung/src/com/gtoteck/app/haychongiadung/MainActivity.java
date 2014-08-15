package com.gtoteck.app.haychongiadung;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.callback.ImageOptions;
import com.gtoteck.app.dao.GiaDungEntity;
import com.gtoteck.app.dao.GiaDungImpl; 

public class MainActivity extends Activity {

	private Context mContext = this;

	private int mIndex = 0;

	private AQuery mAQuery;

	private GiaDungEntity mGiaDungEntity;

	private GiaDungImpl mGiaDungImpl;

	// componets ui
	private Button mBtnSubmit;
	private TextView mTvDesc;
	private TextView mTvSuggestion;
	private ImageView mImgProduct;
	private ImageView mImgBack;
	private ProgressBar mProgressBar;
	private ImageView mImgHelp;
	private ImageView mImgShare;
	private TextView mTvNumber;
	private TextView mTvCoin;
	private TextView mTvName;
	private TextView mTvMadein;
	private TextView mTvVendor;
	private TextView mTvQuantity;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		this.initUI();
	}

	private void initUI() {
		// find id
		this.mImgProduct = (ImageView) this.findViewById(R.id.imgProduct);
		this.mTvDesc = (TextView) this.findViewById(R.id.tvDesc);
		this.mBtnSubmit = (Button) this.findViewById(R.id.btnSubmit);
		this.mProgressBar = (ProgressBar)this.findViewById(R.id.progressBar);
		this.mTvName = (TextView)this.findViewById(R.id.tvName);
		this.mTvMadein = (TextView)this.findViewById(R.id.tvMadein);
		this.mTvVendor = (TextView)this.findViewById(R.id.tvVendor);
		this.mTvQuantity= (TextView)this.findViewById(R.id.tvQuantity);

		// setup events
		this.mBtnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				next();
			}
		});

		// init db
		this.mGiaDungImpl = new GiaDungImpl(mContext);

		// init aquery
		this.mAQuery = new AQuery(mContext);

		// call question
		this.next();
	}
	
	

	private void next() {
		this.mGiaDungEntity = this.mGiaDungImpl
				.getGiaDungEntityByPosition(mIndex);

		String image = this.mGiaDungEntity.getImage();
		
		BitmapAjaxCallback ajaxCallback = new BitmapAjaxCallback() {
			@Override
			public void async(Context context) {
				// TODO Auto-generated method stub
				super.async(context);
				mProgressBar.setVisibility(View.VISIBLE);
			}

			@Override
			protected void callback(String url, ImageView iv, Bitmap bm,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				super.callback(url, iv, bm, status);
				mProgressBar.setVisibility(View.GONE);
			}
		};
	  
		this.mAQuery
				.id(this.mImgProduct)
				.progress(this.mProgressBar)
				.image(image, true, true, 0, R.drawable.ic_launcher,
						ajaxCallback);

		Spanned text = Html.fromHtml(this.mGiaDungEntity.getDesc());

		this.mTvDesc.setText(text);
		this.mTvName.setText(this.mGiaDungEntity.getName());
		this.mTvVendor.setText("NSX: " + this.mGiaDungEntity.getVendor());
		this.mTvMadein.setText("MADE: " + this.mGiaDungEntity.getMadeIn());
		this.mTvQuantity.setText("SL: " + this.mGiaDungEntity.getQuantity());

		// increment
		mIndex++;
	}
	
	
}
