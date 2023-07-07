package com.example.todo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ITodoRVAdapter {

    lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TodoRVAdapter(this, this)
        recyclerView.adapter = adapter



        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModel::class.java) // for the app to not crash
        viewModel.allNotes.observe(this@MainActivity, Observer {list->
            list?.let {

                adapter.updateList(it)
            }
        })

      }

    override fun onItemClicked(note: Todo) {
          viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text}Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Todo(noteText))
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()

        }
    }
}