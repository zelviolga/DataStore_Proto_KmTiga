package coding.withze.ds_proto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repo = UserPreferencesRepository(application)
    val dataUser = repo.readProto.asLiveData()

    fun editData(name : String) = viewModelScope.launch{
        repo.saveData(name)
    }

    fun clearData() = viewModelScope.launch{
        repo.deleteData()
    }

}