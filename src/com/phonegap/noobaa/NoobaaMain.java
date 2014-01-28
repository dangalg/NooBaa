/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.phonegap.noobaa;

import java.util.concurrent.ExecutorService;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.apache.cordova.*;
import com.phonegap.noobaa.R;

public class NoobaaMain extends Activity implements CordovaInterface {
    CordovaWebView cwv;
    boolean leave;
    Context context;
    AlarmManager am;
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        BroadcastNotification bn = new BroadcastNotification();
//        bn.onReceive(context, new Intent(this, BroadcastNotification.class));
        setContentView(R.layout.main);
        
        cwv = (CordovaWebView) findViewById(R.id.tutorialView);
        Config.init(this);
        cwv.loadUrl(Config.getStartUrl());
    }
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SetAlarm();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CancelAlarms();
	}



	private void CancelAlarms() {
		Intent intent = new Intent(this, BroadcastNotification.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);

		// Cancel alarms
		try {
			am.cancel(pendingIntent);
		} catch (Exception e) {
			Log.e("AlarmNotCanceled", "AlarmManager update was not canceled. "
					+ e.toString());
		}
	}

	private void SetAlarm() {
		Intent intent = new Intent(this, BroadcastNotification.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 30000, pendingIntent);
	}
    
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}



	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public ExecutorService getThreadPool() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object onMessage(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		String onPageStarted = "onPageStarted";
		String homepage = "/android_asset/www/index.html";
		String noobaa = "/home/feed/";
		Uri uri = Uri.parse(arg1.toString());
		String startPage = uri.getPath();
		if(startPage.matches(homepage) && arg0 == onPageStarted && leave)
		{
			finish();
		}
		if(startPage.matches(noobaa))
		{
			leave = true;
		}
		return null;
	}
	@Override
	public void setActivityResultCallback(CordovaPlugin arg0) {
		// TODO Auto-generated method stub
		String bill = "1";
	}
	@Override
	public void startActivityForResult(CordovaPlugin arg0, Intent arg1, int arg2) {
		// TODO Auto-generated method stub
		String bill = "1";
	}
	
}

