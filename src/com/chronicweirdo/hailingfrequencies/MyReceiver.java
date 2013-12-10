package com.chronicweirdo.hailingfrequencies;

import java.io.File;
import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class MyReceiver extends AppWidgetProvider {
	
	private static final String ACTION_CLICK = "ACTION_CLICK";
	
	public MyReceiver() {
		
	}
	
	

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// get all ids
		ComponentName thisWidget = new ComponentName(context, MyReceiver.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId: allWidgetIds) {
			// create some random data
			int number = (new Random().nextInt(100));
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			Log.w("widgetExample", String.valueOf(number));
			
			// set the text
			remoteViews.setTextViewText(R.id.update, String.valueOf(number));
			
			// register an onClickListener
			Intent intent = new Intent(context, MyReceiver.class);
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
			
			Intent openIntent = new Intent(context, MyReceiver.class);
			openIntent.setAction(Intent.ACTION_VIEW);
			openIntent.setType("application/txt");
			//openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			Uri uri = Uri.fromFile(new File("/storage/sdcard0/mydata/cumparaturi.txt"));
			Log.w("widgetExample", uri.toString());
			openIntent.setData(uri);
			
			//PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openIntent, 0);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			//remoteViews.setOnClickFillInIntent(R.id.update, openIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
			
			
			
			//context.startActivity(openIntent);
		}
		
	}
	
	/*@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		
		throw new UnsupportedOperationException("Not yet implemented");
	}*/
}
