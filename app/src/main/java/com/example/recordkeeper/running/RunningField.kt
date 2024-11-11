package com.example.recordkeeper.running

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityRunningFieldBinding

class RunningField : AppCompatActivity() {
    lateinit var binding: ActivityRunningFieldBinding
    private val runningPrefences by lazy { getSharedPreferences("running", Context.MODE_PRIVATE) }
    private val distance by lazy { intent.getStringExtra("title") }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRunningFieldBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpUi()


        displayRecord()
    }

    private fun setUpUi() {
        title = "$distance Record"
        binding.saveButton.setOnClickListener {

            saveRecord()
            finish()
        }
        binding.clearButton.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        runningPrefences.edit {
            remove("$distance record")
            remove("$distance date")
        }

    }

    private fun displayRecord() {


        binding.editTextLayout.setText(runningPrefences.getString("$distance record", null))
        binding.editDateLayout.setText(runningPrefences.getString("$distance date", null))

    }

    private fun saveRecord() {
        val record = binding.editTextLayout.text.toString()
        val date = binding.editDateLayout.text.toString()


        runningPrefences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }
}