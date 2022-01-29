package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Console log function for button press
//        findViewById<Button>(R.id.button).setOnClickListener{
//            Log.i("Test", "Button Was Pressed!")
//        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Read")

        val recyclerView = findViewById<RecyclerView>(R.id.toDoList)

        val adapter = TaskItemAdapter(listOfTasks)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}