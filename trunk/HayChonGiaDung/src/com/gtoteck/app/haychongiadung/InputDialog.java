package com.gtoteck.app.haychongiadung;
 

import android.app.Dialog;
import android.content.Context; 

public class InputDialog extends Dialog{
	
	private Context mContext;

	public InputDialog(Context context) {
		super(context);
		this.mContext = context;
		setContentView(R.layout.dialog_input);
		getWindow().setBackgroundDrawableResource(
				R.drawable.dialog_full_holo_light);
	 
		setTitle("FFFFF");
		init();
	}
	
	private void init(){
		
	}

}
