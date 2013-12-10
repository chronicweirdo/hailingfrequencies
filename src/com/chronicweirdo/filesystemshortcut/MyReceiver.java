package com.chronicweirdo.filesystemshortcut;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		
	}



	/*@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		
		throw new UnsupportedOperationException("Not yet implemented");
	}*/
}
