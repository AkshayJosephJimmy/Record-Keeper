package com.example.recordkeeper

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.Cycling
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.Running

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        onRunningClicked()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_running -> {
                    onRunningClicked()

                    true


                }
                R.id.nav_cycling -> {
                    onCyclingClicked()
                    true

                }
                else -> false
            }

            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val optionsClicked = when (item.itemId) {
            R.id.reset_running -> {

                showConfirmationDialoge("running")
                
                true

            }

            R.id.reset_cycling -> {
                showConfirmationDialoge("cycling")
                true

            }

            R.id.reset_all_records -> {
               showConfirmationDialoge("all")
                true


            }

            else -> return super.onOptionsItemSelected(item)

        }




        return optionsClicked
    }

    private fun showConfirmationDialoge(selection:String) {
        AlertDialog.Builder(this)
            .setTitle("reset $selection records")
            .setMessage("Are you sure you want to reset records")
            .setPositiveButton(
                "Yes"
            ) { _, _ ->

                when(selection){
                    "all"-> {
                        getSharedPreferences("running", MODE_PRIVATE).edit {clear() }
                        getSharedPreferences("cycling", MODE_PRIVATE).edit { clear() }
                    }else->
                getSharedPreferences(selection, MODE_PRIVATE).edit { clear() }

                }
                refreshFragment()

            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

    }

    private fun refreshFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
        }
    }


    fun onRunningClicked(){
        supportFragmentManager.commit {
            replace(R.id.frame_layout, Running())}

    }
    fun onCyclingClicked(){
        supportFragmentManager.commit {
            replace(R.id.frame_layout, Cycling())}

    }

    }


