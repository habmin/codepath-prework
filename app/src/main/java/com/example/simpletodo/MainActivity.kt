package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Console log function for button press
//        findViewById<Button>(R.id.button).setOnClickListener{
//            Log.i("Test", "Button Was Pressed!")
//        }

        val onLongClickListener = object: TaskItemAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }
        }
        loadItems()
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
            saveItems()
        }
    }
    //Reading and writing to file
    fun getDataFile(): File {
        return File(filesDir, "todolist.txt")
    }

    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}