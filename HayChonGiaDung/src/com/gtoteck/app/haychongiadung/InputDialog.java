package com.gtoteck.app.haychongiadung;

import com.gtoteck.app.util.MonneyT;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class InputDialog extends Dialog implements OnClickListener {

	// define context
	// private Context mContext;

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

	private Button mBtnOk;
	private Button mBtnCancel;

	// defind textview
	private TextView mTvNumber;

	// defind value
	private String mValue = "";

	// defind constanc
	private final static String DEFAULT_VALUE = "0";

	private final static int LENGHT_VALUE = 12;

	private final static int NOT_FOUND = -1;

	private final static String COMMA = ".";

	// defind event
	private View.OnClickListener mBtnOkClickListener;

	// default contructor
	public InputDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.mContext = context;

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		setContentView(R.layout.dialog_input);
		setCancelable(false);
		// setTitle("FFFFF");
		init();

	}

	// inits componets
	private void init() {
		// find id
		mTvNumber = (TextView) this.findViewById(R.id.tvNumber);
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

		mBtnOk = (Button) this.findViewById(R.id.btnOk);
		mBtnOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Float f = getValue();
				v.setTag(f);

				if (mBtnOkClickListener != null) {
					mBtnOkClickListener.onClick(v);
				}

				dismiss();
			}
		});

		mBtnCancel = (Button) this.findViewById(R.id.btnCancel);
		mBtnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});

		// register event handing
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
				clear();
			}
		});

		mImbDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				backSpace();
			}
		});

		mBtnComma.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addComma();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button btn = (Button) v;

		if (mValue.length() < LENGHT_VALUE) {
			// remove defaul number for first
			if (mValue.equals(DEFAULT_VALUE)) {
				mValue = "";
			}

			// add number
			String val = btn.getText().toString();
			addValue(val);
		}

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		clear();
		super.show();
	}

	public void setmBtnOkClickListener(View.OnClickListener mBtnOkClickListener) {
		this.mBtnOkClickListener = mBtnOkClickListener;
	}

	@SuppressLint("UseValueOf")
	private float getValue() {
		if (mValue.equals("") || mValue.equals(COMMA)) {
			setValue(DEFAULT_VALUE);
		}
		Float float1 = new Float(mValue);
		return float1;
	}

	private void backSpace() {
		String value = mValue;
		if (value.equals("")) {
			clear();
		} else {
			value = value.substring(0, value.length() - 1);
			setValue(value);
		}

		if (value.equals("")) {
			clear();
		}
	}

	private void addComma() {
		if (mValue.equals(DEFAULT_VALUE)) {
			mValue = "";
		}
		if (mValue.indexOf(COMMA) == NOT_FOUND) {
			addValue(COMMA);
		}
	}

	private void clear() {
		setValue(DEFAULT_VALUE);
	}

	private void setValue(String value) {
		mValue = value;
		this.mTvNumber.setText(mValue);
	}

	private void addValue(String value) {
		mValue += value;
		this.mTvNumber.setText(mValue);
		
		double d = Double.parseDouble(mValue);
		this.mTvNumber.setText(MonneyT.priceWithoutDecimal( d)  );
	}

}
