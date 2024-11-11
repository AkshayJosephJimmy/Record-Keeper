package com.example.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding
import com.example.recordkeeper.editrecord.EditRecord

class Running : Fragment() {
    lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListener()


    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun setUpClickListener() {
        binding.frame5km.setOnClickListener { launchActivity("5km") }
        binding.frameTenkm.setOnClickListener { launchActivity("tenkm") }
        binding.frameHalfmarathon.setOnClickListener { launchActivity("half marathon") }
        binding.frameMarathon.setOnClickListener { launchActivity("marathon") }

    }

    private fun displayRecords() {
        val runningprefernce =
            requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.fivekmRecord.text = runningprefernce.getString("5km record", null)
        binding.fivekmDate.text = runningprefernce.getString("5km date", null)

        binding.tenkmRecord.text = runningprefernce.getString("tenkm record", null)
        binding.tenkmDate.text = runningprefernce.getString("tenkm date", null)

        binding.halfmarathonRecord.text = runningprefernce.getString("half marathon record", null)
        binding.halfmarathonDate.text = runningprefernce.getString("half marathon date", null)

        binding.marathonRecord.text = runningprefernce.getString("marathon record", null)
        binding.marathonDate.text = runningprefernce.getString("marathon date", null)

    }


    private fun launchActivity(distance: String) {
        val intent = Intent(context, EditRecord::class.java)
        intent.putExtra("Screen data",EditRecord.ScreenData(distance,"running","Time"))
        startActivity(intent)

    }

}




