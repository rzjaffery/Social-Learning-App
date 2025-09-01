package com.rzjaffery.sociallearningapplication.core;

import android.content.Context;
import android.content.SharedPreferences;

public class OnboardingPrefs {
    private static final String P = "onb_prefs";
    private static final String K = "done";

    public static boolean isDone(Context c) {
        return c.getSharedPreferences(P, Context.MODE_PRIVATE).getBoolean(K, false);
    }

    public static void setDone(Context c) {
        SharedPreferences sp = c.getSharedPreferences(P, Context.MODE_PRIVATE);
        sp.edit().putBoolean(K, true).apply();
    }
}