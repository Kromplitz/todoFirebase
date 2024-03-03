package com.example.todofirebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskViewModel: ViewModel() {
    //private val repo = MyApplication.getApp().repo

    private val _uiState = MutableLiveData<UIState>(UIState.EmptyList)
    val uiState: LiveData<UIState> = _uiState
    private val observer = Observer<List<Tasks>> {
        _uiState.postValue(UIState.UpdatedList(list = it))
    }

    fun getData(){
        _uiState.value = UIState.EmptyList
        viewModelScope.launch{
            withContext(Dispatchers.IO) {

                val response = repo.getHeroByName()
                if ((response != null) && response.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _uiState.postValue(
                            UIState.Result (response))}
                } else {
                    _uiState.postValue(UIState.Error("Error"))
                }
            }
        }
        _
    }

    fun loadDatagetData
    //init {
      //  repo.getAll().observeForever(observer)}
    //fun addEmployee(name:String, position:String){
     //   repo.add(Employee(name = name, position = position)) }
   // fun removeEmployee(employee: Employee){
     //   repo.remove(employee)}
    // fun onCleared() {
      //  repo.getAll().removeObserver(observer)
      //  super.onCleared()}
    sealed class UIState {
        object EmptyList:UIState()
        class UpdatedList(val list:List<Tasks>):UIState()
        class Error(val description: String) : UIState()
    }
}
