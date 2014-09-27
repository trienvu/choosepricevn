package com.gtoteck.app.haychongiadung;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends Activity {

	private Context mContext = this;

	private Button mBtnPlay;

	private ImageView mImgWheel;
	private ImageView mImgGuessBall;
	private ImageView mImgDHBC;
	private ImageView mImgTivi;

	private ImageView mImgInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

		this.mBtnPlay = (Button) this.findViewById(R.id.btnPlay);
		this.mImgInfo = (ImageView) this.findViewById(R.id.ivInfo);

		this.mImgWheel = (ImageView) this.findViewById(R.id.ivCnkd);
		this.mImgGuessBall = (ImageView) this.findViewById(R.id.ivFootballQuiz);
		this.mImgDHBC = (ImageView) this.findViewById(R.id.ivDhbc);
		this.mImgTivi = (ImageView) this.findViewById(R.id.ivXemTivi);

		init();
	}

	private void init() {
		mBtnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, MainActivity.class);
				startActivity(intent);
			}
		});

		mImgInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setTitle("Thông tin");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setMessage(getResources()
						.getText(R.string.version_info).toString());
				builder.setNegativeButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
				builder.show();
			}
		});

		mImgWheel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String appPackageName = "com.gtotek.wheeloffortune"; 
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});

		mImgGuessBall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String appPackageName = "com.gtotek.footballquiz";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});

		mImgDHBC.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String appPackageName = "com.gtotek.tumchu";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});

		mImgTivi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String appPackageName = "com.gtotek.imedia";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});
	}
}
