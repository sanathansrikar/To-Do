package com.example.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)  //arrayOf is replaced by [] in Todo::class
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    companion object {


                // Singleton prevents multiple instances of database opening at the
                // same time.
                @Volatile
                private var INSTANCE: TodoDatabase? = null

                fun getDatabase(context: Context): TodoDatabase {
                    // if the INSTANCE is not null, then return it,
                    // if it is, then create the database
                    return INSTANCE ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            TodoDatabase::class.java,
                            "todo_database"
                        ).build()
                        INSTANCE = instance
                        // return instance
                        instance
                    }
                }
            }

    }

