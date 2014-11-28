package com.anhuioss.room.c;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestRoomCActivity extends Activity implements OnClickListener {
    
	private Activity otherActivity;
	
	public TestRoomCActivity() {
		super();
	}
	
	public TestRoomCActivity(Context context) {
		super();
		otherActivity = (Activity) context;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (otherActivity != null) {
        	
			Button button = new Button(otherActivity);
			button.setText("Room C");
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
    	return "Room C";
    }

	@Override
	public void onClick(View v) {
		Toast.makeText(otherActivity, "This is Room C!", Toast.LENGTH_SHORT).show();
	}
    
}
