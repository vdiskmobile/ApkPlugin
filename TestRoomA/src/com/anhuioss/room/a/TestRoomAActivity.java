package com.anhuioss.room.a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestRoomAActivity extends Activity implements OnClickListener {
    
	/**
	 * TestHallActivity的上下文引用
	 */
	private Activity otherActivity;
	
	/**
	 * 无参构造函数
	 */
	public TestRoomAActivity() {
		super();
	}
	
	/**
	 * 有参构造函数
	 * @param context
	 */
	public TestRoomAActivity(Context context) {
		super();
		otherActivity = (Activity) context;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (otherActivity != null) {
        	// 使用TestHallActivity的上下文引用创建View并添加到根视图
			Button button = new Button(otherActivity);
			button.setText("Room A");
			button.setOnClickListener(this);
			
			LinearLayout root = new LinearLayout(otherActivity);
			
			root.addView(button);
			
			// setContentView(root);使用的上下文是当前Activity的，而不是指定的TestHallActivity
			otherActivity.setContentView(root);
			
		} else {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
		}
    }
    
    /**
     * 返回当前Activity的描述信息
     * @return
     */
    private String getRoomMessage() {
    	return "Room A";
    }

	@Override
	public void onClick(View v) {
		
		if (otherActivity != null) {
			// Toast.makeText(this, "This is Room A!", Toast.LENGTH_SHORT).show();是不合适的调用
			Toast.makeText(otherActivity, "This is Room A!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "This is Room A!", Toast.LENGTH_SHORT).show();
		}
		
	}
    
}
