package com.tcm.core.session

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.securepreferences.SecurePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SessionManager(context: Context): ISessionManager {

    @SuppressLint("ObsoleteSdkInt")
    private var pref: SharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
        EncryptedSharedPreferences
            .create(
                context,
                "Session",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    } else {
        SecurePreferences(context, "tcm", "Session")
    }

    override fun saveTheme(theme: Int) {
        pref.edit().putInt("theme", theme).apply()
    }

    override fun getTheme(): StateFlow<Int> = MutableStateFlow(pref.getInt("theme", 0))

}
