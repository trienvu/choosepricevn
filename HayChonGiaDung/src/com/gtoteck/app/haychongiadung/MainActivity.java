package com.gtoteck.app.haychongiadung;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.gtotech.app.base.Constans;
import com.gtoteck.app.dao.GiaDungEntity;
import com.gtoteck.app.dao.GiaDungImpl;
import com.gtoteck.app.util.CaptureLayoutUtil;
import com.gtoteck.app.util.PreferenceUtil;
import com.gtoteck.app.util.SoundUtil; 

public class MainActivity extends Activity {
	
	/** Your ad unit id. Replace with your actual ad unit id. */
	private static String AD_UNIT_ID = null;

	/** The interstitial ad. */
	private InterstitialAd interstitialAd;

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
	private ScrollView mScvInfoSum;
	private ScrollView mScvInfoDetails;

	private TextView mTvInfoSum;
	private TextView mTvInfoDetails;
	// dialog
	private InputDialog mInputDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		this.initUI();
		
		setInterstitialAd();
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

		this.mScvInfoSum = (ScrollView) this.findViewById(R.id.scvInfoSum);
		this.mScvInfoDetails = (ScrollView) this
				.findViewById(R.id.scvInfoDetails);
		this.mTvInfoSum = (TextView) this.findViewById(R.id.tvInfoSum);
		this.mTvInfoDetails = (TextView) this.findViewById(R.id.tvInfoDetails);

		// create dialog
		mInputDialog = new InputDialog(mContext);
		mInputDialog.setmBtnOkClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub
				float f = (Float) v.getTag();

				if (comparePrice(f)) {
					// sound
					SoundUtil.hexat(mContext, SoundUtil.SFX_PASS);

					// pass to activity win
					Intent intent = new Intent(mContext, WinActivity.class);
					intent.putExtra(Constans.KEY_GIADUNGENTITY, mGiaDungEntity);
					startActivity(intent);

					// increment
					mRuby += 4;
					;
					// save
					PreferenceUtil.setValue(mContext,
							Constans.KEY_INDEX_GIADUNG, mIndex);
					PreferenceUtil.setValue(mContext, Constans.KEY_RUBY, mRuby);

					next();
				} else {
					SoundUtil.hexat(mContext, SoundUtil.OVER);
				}

			}
		});

		/** setup events **/
		// Set view product info
		viewProductInfo();

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
				help();
			}
		});

		// init db
		this.mGiaDungImpl = new GiaDungImpl(mContext);

		// init var
		mSize = this.mGiaDungImpl.getSize();
		mIndex = PreferenceUtil.getValue(mContext, Constans.KEY_INDEX_GIADUNG,
				0);
		mRuby = PreferenceUtil.getValue(mContext, Constans.KEY_RUBY, 69);

		// init aquery
		this.mAQuery = new AQuery(mContext);

		// call question
		this.next();
	}

	private void viewProductInfo() {
		mTvInfoSum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mScvInfoSum.setVisibility(View.VISIBLE);
				mScvInfoDetails.setVisibility(View.GONE);
				mTvInfoDetails
						.setBackgroundResource(R.drawable.ic_sp_details_normal);
				mTvInfoSum.setBackgroundResource(R.drawable.ic_sp_sum_focused); 
			}
		});
		mTvInfoDetails.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mScvInfoSum.setVisibility(View.GONE);
				mScvInfoDetails.setVisibility(View.VISIBLE);
				mTvInfoDetails
						.setBackgroundResource(R.drawable.ic_sp_details_focused);
				mTvInfoSum.setBackgroundResource(R.drawable.ic_sp_sum_normal);
			}
		});

	}

	private boolean comparePrice(float input) {
		float priceMax = (float) mGiaDungEntity.getPrice();
		float priceMin = (priceMax / 100f) * 80;

		if ((input < priceMax && input > priceMin) || (input == priceMin)
				|| (input == priceMax)

		) {
			return true;
		}

		return false;
	}

	private void help() {

		AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
		mBuilder.setMessage("Bạn có thể mất 20 ruby cho mỗi lần gợi ý ?");
		mBuilder.setPositiveButton("Đồng ý",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						generateSussgesstion();
					}
				});

		mBuilder.setNegativeButton("Hủy bỏ",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		mBuilder.show();
	}

	private void generateSussgesstion() {
		if (mRuby >= 20) {
			int tyLe = (mGiaDungEntity.getPrice() / 100);
			int priceMin = tyLe * 70;

			int rand = new Random().nextInt(50) + 2;

			priceMin = priceMin + (rand * tyLe);

			String opt = (priceMin > mGiaDungEntity.getPrice()) ? "<" : ">";

			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			builder.setMessage("Sản phẩm có giá " + opt + priceMin + " VND");
			builder.setNegativeButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			builder.show();

			//
			mRuby -= 20;
			mTvCoin.setText(mRuby + "");
		} else {
			Toast.makeText(mContext, "Bạn không đủ ruby !!!", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void next() {
		mTvCoin.setText(mRuby + "");
		mTvNumber.setText((mIndex + 1) + "");

		if (mIndex >= mSize) {
			Intent intent = new Intent(mContext, VictoryActivity.class);
			startActivity(intent);
			finish();
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
				.image(image, false, false, 0, R.drawable.ic_launcher,
						ajaxCallback);

		Spanned text = Html.fromHtml(this.mGiaDungEntity.getDesc());
		this.mTvNumber.setText(String.valueOf((mIndex + 1) + ""));
		this.mTvDesc.setText(text);
		this.mTvName.setText(this.mGiaDungEntity.getName().trim());
		this.mTvVendor.setText("NSX: " + this.mGiaDungEntity.getVendor());
		this.mTvMadein.setText("MADE: " + this.mGiaDungEntity.getMadeIn());
		this.mTvQuantity.setText("SL: " + this.mGiaDungEntity.getQuantity());

		// increment
		mIndex++;
	}
	
	private void setInterstitialAd() {
		AD_UNIT_ID = getResources().getString(R.string.id_admob);

		// Create an ad.
		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(AD_UNIT_ID);
		AdRequest adRequest = new AdRequest.Builder().build();

		// Set the AdListener.
		interstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				interstitialAd.show();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {

			}
		});

		// Load the interstitial ad.
		interstitialAd.loadAd(adRequest);
	}

}