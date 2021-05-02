package com.mzom.skalo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewRecordFragment : Fragment() {

    companion object {
        fun newInstance() = ViewRecordFragment()
    }

    private lateinit var viewModel: ScaleViewModel

    private lateinit var recordValue: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScaleViewModel::class.java)

        recordValue = view.findViewById(R.id.scale_value)
        println(viewModel.weight)
        recordValue.text = viewModel.weight.toString()

        val editButton = view.findViewById<FloatingActionButton>(R.id.record_edit_button)
        editButton.setOnClickListener {
            findNavController().navigate(
                ViewRecordFragmentDirections.actionRecordFragmentToScaleFragment()
            )
        }

    }

}