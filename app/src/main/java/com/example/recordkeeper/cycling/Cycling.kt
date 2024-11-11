package com.example.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.editrecord.EditRecord

class Cycling:Fragment() {
    lateinit var binding : FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCyclingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        displayRecord()

    }

    private fun displayRecord() {
       val cyclingpreferences = requireContext().getSharedPreferences("cycling",Context.MODE_PRIVATE)

        binding.recordLongesrride.text=cyclingpreferences.getString("Longest Distance record",null)
        binding.longestrideDate.text=cyclingpreferences.getString("Longest Distance date",null)
        binding.hihestclimbRecord.text=cyclingpreferences.getString("Highest Climb record",null)
        binding.hihhestclimbDate.text=cyclingpreferences.getString("Highest Climb date",null)
        binding.averagespeedRecod.text=cyclingpreferences.getString("Average Speed record",null)
        binding.averagespeedDate.text=cyclingpreferences.getString("Average Speed date",null)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUponClicked()
    }
    private fun setUponClicked(){
        binding.frame5km.setOnClickListener { launchActicvity("Longest Distance","distance") }
        binding.frameTenkm.setOnClickListener { launchActicvity("Highest Climb","Heigth") }
        binding.frameHalfmarathon.setOnClickListener { launchActicvity("Average Speed","Speed") }
       // binding.frameMarathon.setOnClickListener { launchActicvity() }
    }


    private fun launchActicvity(record :String, hint:String){
        val intent=Intent(context, EditRecord::class.java)
        intent.putExtra("Screen data",EditRecord.ScreenData(record,"cycling",hint))
        startActivity(intent)

    }

}

