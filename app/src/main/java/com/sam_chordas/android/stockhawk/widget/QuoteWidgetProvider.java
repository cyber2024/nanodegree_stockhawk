package com.sam_chordas.android.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Russell Elfenbein on 3/8/2016.
 */
public class QuoteWidgetProvider extends AppWidgetProvider {
    public static final String
            ACTION_DATA_UPDATED = "com.sam_chordas.android.stockhawk.rest.QuoteCursorAdapter.ACTION_DATA_UPDATED",
            TAG = QuoteWidgetProvider.class.getSimpleName();
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, QuoteWidgetIntentService.class));
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        context.startService(new Intent(context, QuoteWidgetIntentService.class));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(ACTION_DATA_UPDATED.equals(intent.getAction())){
            context.startService(new Intent(context, QuoteWidgetIntentService.class));
            Log.d(TAG, "Received broadcast to update widgets");
        }
    }
}
