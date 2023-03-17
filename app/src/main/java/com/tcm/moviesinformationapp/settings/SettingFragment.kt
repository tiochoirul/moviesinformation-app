package com.tcm.moviesinformationapp.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.tcm.moviesinformationapp.MainActivity
import com.tcm.moviesinformationapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    private val settingViewModel: SettingViewModel by viewModel()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themePreference: ListPreference? = findPreference("theme")
        themePreference?.onPreferenceChangeListener = this

        settingViewModel.theme.observe(this) { isDarkModeActive ->
            when (isDarkModeActive) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                else -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        if (preference is ListPreference) {
            val themeMode = (newValue as String).toInt()
            settingViewModel.saveTheme(themeMode)

            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            activity?.finish()
        }
        return true
    }

}