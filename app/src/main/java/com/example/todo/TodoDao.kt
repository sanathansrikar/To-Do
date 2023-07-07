package com.example.todo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE )
        suspend fun insert(note: Todo)
        @Delete
       suspend  fun delete(note: Todo)
        @Query("Select * from todo_table order by id ASC")
        fun getAll(): LiveData<List<Todo>>
}