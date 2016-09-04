package com.mianlabs.pokeluv.utilities;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mianlabs.pokeluv.R;

public class TypefaceUtils {
    private static final String RELATIVE_PATH_TO_TYPEFACE = "Pokemon GB.ttf";

    public static void setActionBarTitle(AppCompatActivity context, String text) {
        // Applies the custom typeface to the Action Bar.
        SpannableString s = new SpannableString(text);
        // Don't use the full path of the text font, only the font filename.
        s.setSpan(new TypefaceSpan(context, RELATIVE_PATH_TO_TYPEFACE), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ActionBar actionBar = context.getSupportActionBar();
        actionBar.setTitle(s);
    }

    public static void setActionBarOptionsText(AppCompatActivity context, Menu menu) {
        // Implements custom fonts for all Poke Luv menu items.

        SpannableStringBuilder pokedexTitle =
                new SpannableStringBuilder(context.getString(R.string.menu_more_pokemon));
        pokedexTitle
                .setSpan(new TypefaceSpan(context, RELATIVE_PATH_TO_TYPEFACE), 0, pokedexTitle.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableStringBuilder pokemonOfTheDayTitle =
                new SpannableStringBuilder(context.getString(R.string.menu_pokemon_of_the_day));
        pokemonOfTheDayTitle
                .setSpan(new TypefaceSpan(context, RELATIVE_PATH_TO_TYPEFACE), 0, pokemonOfTheDayTitle.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        MenuItem dexItem = menu.findItem(R.id.menu_more_pokemon);
        dexItem.setTitle(pokedexTitle);

        MenuItem dailyItem = menu.findItem(R.id.menu_pokemon_of_the_day);
        dailyItem.setTitle(pokemonOfTheDayTitle);
    }

    private static void runCustomToast(final Toast toast,
                                       int toastDurationInMilliSeconds,
                                       int toastRefreshRateInMilliSeconds) {
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, toastRefreshRateInMilliSeconds) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            public void onFinish() {
                toast.cancel();
            }
        };
        toast.show();
        toastCountDown.start();
    }

    /**
     * Displays a toast msg for a specified amount of time (seconds).
     * Implemented using code from: http://blog.cindypotvin.com/toast-specific-duration-android/
     */
    public static void displayToast(Context context, String msg, int durationInSeconds) {
        int toastDurationInMilliSeconds = durationInSeconds * 1000;
        int toastRefreshRateInMilliSeconds = 2 * 1000;

        SpannableString s = new SpannableString(msg);
        s.setSpan(new TypefaceSpan(context, RELATIVE_PATH_TO_TYPEFACE), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        final Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);

        runCustomToast(toast, toastDurationInMilliSeconds, toastRefreshRateInMilliSeconds);
    }

    /**
     * Displays a toast msg for a specified amount of time (seconds) in the TOP | CENTER_HORIZONTAL
     * position.
     * Implemented using code from: http://blog.cindypotvin.com/toast-specific-duration-android/
     */
    public static void displayToastTop(Context context, String msg, int durationInSeconds, int yOffset) {
        int toastDurationInMilliSeconds = durationInSeconds * 1000;
        int toastRefreshRateInMilliSeconds = 2 * 1000;

        SpannableString s = new SpannableString(msg);
        s.setSpan(new TypefaceSpan(context, RELATIVE_PATH_TO_TYPEFACE), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        final Toast toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, yOffset);

        runCustomToast(toast, toastDurationInMilliSeconds, toastRefreshRateInMilliSeconds);
    }
}
