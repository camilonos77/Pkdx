/*
 * Copyright (C) 2016 Damián Adams
 */
package com.mianlabs.pkdx.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.mianlabs.pkdx.R;
import com.mianlabs.pkdx.ui.main.MainActivity;
import com.mianlabs.pkdx.utilities.PokePicker;

/**
 * Widget "catches" a different Pokemon every time it is updated,
 * sets the widget layout with that Pokemon and sets it to launch
 * a PokeFragment for that Pokemon when clicked.
 */
public class PokeWidget extends AppWidgetProvider {
    private static final String TAG = PokeWidget.class.getSimpleName();
    public static final String POKE_WIDGET_KEY = "PokeWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "Widget onUpdate called.");
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            int caughtPokemon = PokePicker.catchRandomPokemon();

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(POKE_WIDGET_KEY, caughtPokemon);
            intent.setAction(Long.toString(System.currentTimeMillis())); // Timestamp for generating a unique Pending Intent.
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_CANCEL_CURRENT);

            Drawable drawable = ResourcesCompat.getDrawable(context.getResources(),
                    PokePicker.GenerationNumbers.getDrawableResourceFromNumber(context, caughtPokemon),
                    null);
            if (drawable != null) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pokemon_list_item);
                views.setImageViewBitmap(R.id.pokemon_list_img, bitmap);
                views.setOnClickPendingIntent(R.id.pokemon_list_img, pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId, views);
            } else {
                Log.e(TAG, "Widget drawable is null");
            }
        }
    }
}
