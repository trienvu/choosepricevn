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

	private ImageView mImgDHBC;

	private ImageView mImgInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

		this.mBtnPlay = (Button) this.findViewById(R.id.btnPlay);
		this.mBtnPlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, MainActivity.class);
				startActivity(intent);
			}
		});

		this.mImgDHBC = (ImageView) this.findViewById(R.id.ivDhbc);
		this.mImgDHBC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String appPackageName = "com.gtotek.tumchu"; // Can
				// also
				// use
				// getPackageName(),
				// as below
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}
		});

		this.mImgInfo = (ImageView) this.findViewById(R.id.ivInfo);

		this.mImgInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setTitle("Th�ng tin");
				builder.setIcon(android.R.drawable.ic_dialog_info);
				builder.setMessage(getResources().getText(R.string.version_info)
						.toString());
				builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				builder.show();
			}
		});
	}
}
