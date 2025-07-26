package app.wallet_monitor.shared

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import java.io.File
import java.nio.file.Paths // Add this import for Path

actual fun createDataStore(producePath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = null,
        migrations = emptyList(),
        produceFile = { producePath().toPath() }
    )
}

// Aux function to get the path of the data store file
fun provideDataStorePath(context: Context): String {
    return context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
}