package com.gtoteck.app.haychongiadung;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.gtoteck.app.dao.GiaDungEntity;
import com.gtoteck.app.dao.GiaDungImpl;
import com.gtoteck.app.util.CaptureLayoutUtil;
import com.gtoteck.app.util.SoundUtil;

public class MainActivity extends Activity {

	private Context mContext = this;

	private int mIndex = 0;

	private int mSize = 0;

	private int mRuby = 0;

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

	// dialog
	private InputDialog mInputDialog;

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
		this.mProgressBar = (ProgressBar) this.findViewById(R.id.progressBar);
		this.mTvName = (TextView) this.findViewById(R.id.tvName);
		this.mTvMadein = (TextView) this.findViewById(R.id.tvMadein);
		this.mTvVendor = (TextView) this.findViewById(R.id.tvVendor);
		this.mTvQuantity = (TextView) this.findViewById(R.id.tvQuantity);
		this.mImgBack = (ImageView) this.findViewById(R.id.imgBack);
		this.mImgShare = (ImageView) this.findViewById(R.id.imgShare);
		this.mTvNumber = (TextView) this.findViewById(R.id.tvNumber);
		this.mTvCoin = (TextView) this.findViewById(R.id.tvCoin);
		this.mImgHelp = (ImageView) this.findViewById(R.id.imgHelp);
		this.mTvSuggestion = (TextView) this.findViewById(R.id.tvSuggestion);

		// create dialog
		mInputDialog = new InputDialog(mContext);
		mInputDialog.setmBtnOkClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float f = (Float) v.getTag();

				if (comparePrice(f)) {
					SoundUtil.hexat(mContext, SoundUtil.SFX_PASS);
					next();
				}else{
					SoundUtil.hexat(mContext, SoundUtil.OVER);
				}
			}
		});

		// setup events
		this.mBtnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mInputDialog.show();
			}
		});

		this.mImgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		this.mImgShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View root = v.getRootView().findViewById(R.id.parent);

				Bitmap bitmap = CaptureLayoutUtil
						.captureLayoutGoodQuality(root);

				Uri imageUri = CaptureLayoutUtil.getImageUri(mContext, bitmap);

				CaptureLayoutUtil.shareToFacebook(mContext, imageUri);

				bitmap.recycle();
				bitmap = null;
			}
		});

		this.mImgHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		// init db
		this.mGiaDungImpl = new GiaDungImpl(mContext);

		mSize = this.mGiaDungImpl.getSize();

		// init aquery
		this.mAQuery = new AQuery(mContext);

		// call question
		this.next();
	}

	private boolean comparePrice(float input) {
		float priceMax = (float) mGiaDungEntity.getPrice();
		float priceMin = (priceMax / 100f) * 90;

		if ((input < priceMax && input > priceMin) || (input == priceMin)
				|| (input == priceMax)

		) {
			return true;
		}

		return false;
	}

	private void next() {
		if (mIndex >= mSize) {
			Toast.makeText(mContext, "You win", Toast.LENGTH_LONG).show();
			return;
		}
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

		this.mTvNumber.setText(String.valueOf((mIndex + 1) + ""));
		this.mTvDesc.setText(text);
		this.mTvName.setText(this.mGiaDungEntity.getName());
		this.mTvVendor.setText("NSX: " + this.mGiaDungEntity.getVendor());
		this.mTvMadein.setText("MADE: " + this.mGiaDungEntity.getMadeIn());
		this.mTvQuantity.setText("SL: " + this.mGiaDungEntity.getQuantity());

		// increment
		mIndex++;
	}

}
