package com.anhuioss.room.b;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestRoomBActivity extends Activity implements OnClickListener {
    
	private Activity otherActivity;
	
	public TestRoomBActivity() {
		super();
	}
	
	public TestRoomBActivity(Context context) {
		super();
		otherActivity = (Activity) context;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (otherActivity != null) {
        	
			Button button = new Button(otherActivity);
			button.setText("Room B");
			button.setOnClickListener(this);
			
			LinearLayout root = new LinearLayout(otherActivity);
			
			root.addView(button);
			
			otherActivity.setContentView(root);
			
		} else {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
		}
    }
    
    private String getRoomMessage() {
    	return "Room B";
    }

	@Override
	public void onClick(View v) {
		Toast.makeText(otherActivity, "This is Room B!", Toast.LENGTH_SHORT).show();
	}
    
}
