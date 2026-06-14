package com.example.myapplication_2.utilits

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

object ThemeManager {
    private const val PREF_NAME = "theme_prefs"
    private const val KEY_THEME = "selected_theme"

    // Сохранить и применить тему
    fun setTheme(context: Context, mode: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit { putInt(KEY_THEME, mode) }

        AppCompatDelegate.setDefaultNightMode(mode)
    }

    // Применить сохраненную тему при старте приложения
    fun applySavedTheme(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        // По умолчанию ставим системную тему (MODE_NIGHT_FOLLOW_SYSTEM)
        val savedMode = prefs.getInt(KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        AppCompatDelegate.setDefaultNightMode(savedMode)
    }
}