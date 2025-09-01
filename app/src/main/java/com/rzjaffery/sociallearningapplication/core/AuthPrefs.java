package com.rzjaffery.sociallearningapplication.core;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPrefs {
    private static final String PREF_NAME = "auth_prefs";
    private static final String KEY_KEEP_LOGGED = "keep_logged";

    public static void setKeepLoggedIn(Context ctx, boolean keep) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_KEEP_LOGGED, keep).apply();
    }

    public static boolean isKeepLoggedIn(Context ctx) {
        return ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_KEEP_LOGGED, false);
    }
}
