package com.example.recordkeeper.editrecord

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.example.recordkeeper.databinding.AcitivityEditFiledBinding
import com.example.recordkeeper.databinding.ActivityRunningFieldBinding
import java.io.Serializable

class EditRecord : AppCompatActivity() {
    lateinit var binding: AcitivityEditFiledBinding

    private val screendata : ScreenData? by lazy { intent?.getSerializableExtra("Screen data")  as ScreenData}
    private val recordPreferences by lazy { getSharedPreferences(screendata?.SharedPreferncename, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = AcitivityEditFiledBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpUi()


        displayRecord()
    }

    private fun setUpUi() {
        title = "${screendata?.record} Record"
        binding.saveButton.setOnClickListener {

            saveRecord()
            finish()
        }
        binding.textInputRecord.hint="${screendata?.recordfireldHint}"
        binding.clearButton.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screendata?.record} record")
            remove("${screendata?.record} date")
        }

    }

    private fun displayRecord() {


        binding.editTextLayout.setText(recordPreferences.getString("${screendata?.record} record", null))
        binding.editDateLayout.setText(recordPreferences.getString("${screendata?.record} date", null))

    }

    private fun saveRecord() {
        val record = binding.editTextLayout.text.toString()
        val date = binding.editDateLayout.text.toString()


        recordPreferences.edit {
            putString("${screendata?.record} record", record)
            putString("${screendata?.record} date", date)
        }
    }
    data class ScreenData(val record :String,
                          val SharedPreferncename:String,
                          val recordfireldHint:String):Serializable
}