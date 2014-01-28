package com.phonegap.noobaa;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastNotification extends BroadcastReceiver {

	
	private static final int HELLO_ID = 1;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

	    
	    int icon = R.drawable.notification_icon;        
	    CharSequence tickerText = "NooBaa"; // ticker-text
	    long when = System.currentTimeMillis();             
	    CharSequence contentTitle = "NooBaa";  
	    CharSequence contentText = "Your friends at NooBaa miss you";      
	    Intent notificationIntent = new Intent(context, NoobaaMain.class);
	    notificationIntent.setAction("android.intent.action.MAIN");
	    notificationIntent.addCategory("android.intent.category.LAUNCHER"); 
	    
	    PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
	    Notification notification = new Notification(icon, tickerText, when);
	    notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	    notification.defaults = Notification.DEFAULT_ALL;
	    notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;
	    notification.defaults |= Notification.DEFAULT_SOUND;
	    notification.defaults |= Notification.DEFAULT_VIBRATE;

	    // and this
	    
	    mNotificationManager.notify(HELLO_ID, notification);
	}

}
