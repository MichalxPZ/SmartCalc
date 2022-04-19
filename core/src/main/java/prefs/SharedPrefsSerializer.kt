package prefs

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object SharedPrefsSerializer : Serializer<SharedPrefs> {
    override val defaultValue: SharedPrefs
        get() = SharedPrefs()

    override suspend fun readFrom(input: InputStream): SharedPrefs {
        return try {
            Json.decodeFromString(
                deserializer = SharedPrefs.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: IOException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: SharedPrefs, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = SharedPrefs.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}