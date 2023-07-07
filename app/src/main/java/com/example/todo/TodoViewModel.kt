package com.example.todo

import  android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel( application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Todo>>
     private val repository: TodoRepository

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDao()
         repository = TodoRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Todo) = viewModelScope.launch(Dispatchers.IO) {
      repository.delete(note)
    }

    fun insertNote(note: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}