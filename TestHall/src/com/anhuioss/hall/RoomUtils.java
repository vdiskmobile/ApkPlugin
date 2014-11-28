package com.anhuioss.hall;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dalvik.system.DexClassLoader;

public class RoomUtils implements OnClickListener {
	
	private Context mContext;
	
	/**
	 * 目标Activity的Class
	 */
	private Class<?> localClass;
	
	/**
	 * 目标Activity的实例
	 */
	private Object localInstance;
	
	/**
	 * 
	 * @param context
	 * @param dexPath
	 * @param dexOutputPath
	 */
	public RoomUtils(Context context, String dexPath, String dexOutputPath) {
		mContext = context;
		File dexOutPathFile = context.getDir("dex", 0);
		loadAPK(dexPath, dexOutPathFile.getAbsolutePath());
	}
	
	/**
	 * 获得相关的View
	 * @return 返回一个可点击的按钮
	 */
	public View getView() {
		Button button = new Button(mContext);
		String message = null;
		try {
			message = getRemoteMessage();
			button.setText(message);
			button.setOnClickListener(this);
		} catch (Exception e) {
			e.printStackTrace();
			message = null;
		}
		if (message == null) {
			button.setVisibility(View.GONE);
		}
		return button;
	}
	
	/**
	 * 通过反射，获得描述性信息
	 * @return
	 * @throws Exception
	 */
	private String getRemoteMessage() throws Exception {
		Method remoteMessageMethod = localClass.getDeclaredMethod("getRoomMessage", null);
		remoteMessageMethod.setAccessible(true);
		return (String) remoteMessageMethod.invoke(localInstance, null);
	}

	/**
	 * 通过反射，获得Activity的实例和Class
	 * @param dexPath
	 * @param dexOutputPath
	 */
	private void loadAPK(String dexPath, String dexOutputPath) {
		
		try {
			
			ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
			DexClassLoader localDexClassLoader = new DexClassLoader(dexPath, dexOutputPath, null, localClassLoader);
			
			PackageInfo localPackageInfo = mContext.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);

			if ((localPackageInfo.activities != null) && (localPackageInfo.activities.length > 0)) {
				
				String activityName = localPackageInfo.activities[0].name;

				localClass = localDexClassLoader.loadClass(activityName);
				
				Constructor<?> localConstructor = localClass.getConstructor(new Class[] {Context.class});
				localInstance = localConstructor.newInstance(new Object[] {mContext});
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 通过反射，启动Activity
	 * @throws Exception
	 */
	private void startRemoteActivity() throws Exception {
		Method onCreateMethod = localClass.getDeclaredMethod("onCreate", new Class[] {Bundle.class});
		onCreateMethod.setAccessible(true);
		onCreateMethod.invoke(localInstance, new Object[] {null});
	}

	@Override
	public void onClick(View v) {
		try {
			startRemoteActivity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
