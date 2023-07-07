package com.example.todo

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val allNotes: LiveData<List<Todo>> = todoDao.getAll()

    suspend fun insert(note: Todo) {
        todoDao.insert(note)
    }

    suspend fun delete(note: Todo) {
        todoDao.delete(note)
    }
}