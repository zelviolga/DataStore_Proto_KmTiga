package coding.withze.ds_proto

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class UserPreferencesRepository(private val context : Context) {
//    create data store proto
    private val Context.userPreferencesStore: DataStore<UserProto> by dataStore(
        fileName = "userData",
        serializer = UserPreferencesSerializer
    )

//    save data to datastore proto
    suspend fun saveData(name : String) {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().setName(name).build()

        }
    }


//    delete datastore proto

    suspend fun deleteData() {
        context.userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().clearName().build()

        }
    }

//    read data store proto

    val readProto: Flow<UserProto> = context.userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("tag", "Error reading sort order preferences.", exception)
                emit(UserProto.getDefaultInstance())
            } else {
                throw exception
            }
        }
}