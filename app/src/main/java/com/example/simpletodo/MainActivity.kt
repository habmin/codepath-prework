package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Console log function for button press
//        findViewById<Button>(R.id.button).setOnClickListener{
//            Log.i("Test", "Button Was Pressed!")
//        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Read")

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
            adapter.notifyDataSetChanged()
            }
        }

        //To Do List set up as RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.toDoList)
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val textInputField = findViewById<EditText>(R.id.addTaskField)

        //button on press listener (submit)
        findViewById<Button>(R.id.button).setOnClickListener {
            val textString = textInputField.text.toString()
            listOfTasks.add(textString)
            //Notify adapter that data is updated
            adapter.notifyItemInserted(listOfTasks.size - 1)
            textInputField.setText("")
        }
    }
}