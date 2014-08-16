package com.gtoteck.app.haychongiadung;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InputDialog extends Dialog implements OnClickListener {

	// define context
	private Context mContext;

	// define button
	private Button mBtnKey9;
	private Button mBtnKey8;
	private Button mBtnKey7;
	private Button mBtnKey6;
	private Button mBtnKey5;
	private Button mBtnKey4;
	private Button mBtnKey3;
	private Button mBtnKey2;
	private Button mBtnKey1;
	private Button mBtnKey0; 
	
	private ImageButton mImbDelete;
	private Button mBtnClear;
	private Button mBtnComma;

	// defind textview
	private TextView mTvNumber;

	// default contructor
	public InputDialog(Context context) {
		super(context);
		this.mContext = context;
		setContentView(R.layout.dialog_input);
		getWindow().setBackgroundDrawableResource(
				R.drawable.dialog_full_holo_light);

		setTitle("FFFFF");
		init();
		
		Float f = new Float(".1");
	}

	// inits componets
	private void init() {
		// find id
		mTvNumber = (TextView)this.findViewById(R.id.tvNumber);
		mBtnComma = (Button) this.findViewById(R.id.btnComma);
		mBtnClear = (Button) this.findViewById(R.id.btnClear); 
		mImbDelete = (ImageButton) this.findViewById(R.id.imbDelete);
		
		mBtnKey9 = (Button) this.findViewById(R.id.btnKey9); 
		mBtnKey8 = (Button) this.findViewById(R.id.btnKey8); 
		mBtnKey7 = (Button) this.findViewById(R.id.btnKey7); 
		mBtnKey6 = (Button) this.findViewById(R.id.btnKey6); 
		mBtnKey5 = (Button) this.findViewById(R.id.btnKey5); 
		mBtnKey4 = (Button) this.findViewById(R.id.btnKey4); 
		mBtnKey3 = (Button) this.findViewById(R.id.btnKey3); 
		mBtnKey2 = (Button) this.findViewById(R.id.btnKey2); 
		mBtnKey1 = (Button) this.findViewById(R.id.btnKey1); 
		mBtnKey0 = (Button) this.findViewById(R.id.btnKey0); 
		
		
		//register event handing
		mBtnKey9.setOnClickListener(this);
		mBtnKey8.setOnClickListener(this);
		mBtnKey7.setOnClickListener(this);
		mBtnKey6.setOnClickListener(this);
		mBtnKey5.setOnClickListener(this);
		mBtnKey4.setOnClickListener(this);
		mBtnKey3.setOnClickListener(this);
		mBtnKey2.setOnClickListener(this);
		mBtnKey1.setOnClickListener(this);
		mBtnKey0.setOnClickListener(this);
		
		mBtnClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button btn = (Button)v;
		Toast.makeText(mContext, btn.getText(), Toast.LENGTH_LONG).show();

	}

}
