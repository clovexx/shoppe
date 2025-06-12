package com.clovexx.shoppe.utils

import android.util.Base64
import androidx.datastore.core.Serializer
import com.clovexx.shoppe.data.local.model.Settings
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<Settings> {
    private val cryptoData = CryptoData()

    override val defaultValue: Settings
        get() = Settings("")

    override suspend fun readFrom(input: InputStream): Settings {
        val bytes = input.readBytes().decodeToString()
        val decodeStr = cryptoData.decrypt(decodeString(bytes))?.decodeToString()

        val res = Gson().fromJson(
            decodeStr, Settings::class.java
        )
        return res
    }

    override suspend fun writeTo(t: Settings, output: OutputStream) {
        val json = Gson().toJson(t)
        val bytes = json.toByteArray()
        val str = encodeArray(cryptoData.encrypt(bytes))
        withContext(Dispatchers.IO) {
            output.write(str.toByteArray())
        }
    }
}

fun decodeString(str: String): ByteArray = Base64.decode(str, Base64.DEFAULT)

fun encodeArray(bytes: ByteArray): String = Base64.encodeToString(bytes, Base64.DEFAULT)
