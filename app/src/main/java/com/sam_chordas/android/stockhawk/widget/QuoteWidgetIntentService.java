package com.sam_chordas.android.stockhawk.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * Created by Russell Elfenbein on 3/15/2016.
 */
public class QuoteWidgetIntentService extends IntentService {
    private static final String[] COLUMN_PROJECTION = {
            QuoteColumns.SYMBOL,
            QuoteColumns.BIDPRICE,
            QuoteColumns.PERCENT_CHANGE
    };
    private static final int
            INDEX_COL_SYMBOL = 0,
        INDEX_COL_BID = 1,
        INDEX_COL_PERCENT_CHAGE =2;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public QuoteWidgetIntentService() {
        super("QuoteWidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(this, QuoteWidgetProvider.class));


        Cursor c = getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                COLUMN_PROJECTION,
                null, null, null);

        for(int appWidgetId : appWidgetIds){
            RemoteViews views = new RemoteViews(
                    getPackageName(),
                    R.layout.widget_large
            );

            if(c.moveToFirst()){
                views.setTextViewText(R.id.stock_symbol, c.getString(INDEX_COL_SYMBOL));
                views.setTextViewText(R.id.bid_price, c.getString(INDEX_COL_BID));
                views.setTextViewText(R.id.change, c.getString(INDEX_COL_PERCENT_CHAGE));
            } else {
                views.setTextViewText(R.id.stock_symbol, "No stocks found");
            }

            Intent launchIntent = new Intent(this, MyStocksActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
        c.close();
    }
}
