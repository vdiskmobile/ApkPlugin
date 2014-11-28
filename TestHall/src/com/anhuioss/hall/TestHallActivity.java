package com.anhuioss.hall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class TestHallActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// 获得根容器
		LinearLayout root = (LinearLayout) findViewById(R.id.root);
		
		// 分别获得SDCARD下的APK并添加相关View到根容器
		RoomUtils roomA = new RoomUtils(this, "/mnt/sdcard/test/TestRoomA.apk", "/mnt/sdcard/test/");
		root.addView(roomA.getView());
		
		RoomUtils roomB = new RoomUtils(this, "/mnt/sdcard/test/TestRoomB.apk", "/mnt/sdcard/test/");
		root.addView(roomB.getView());
		
		RoomUtils roomC = new RoomUtils(this, "/mnt/sdcard/test/TestRoomC.apk", "/mnt/sdcard/test/");
		root.addView(roomC.getView());

	}

}