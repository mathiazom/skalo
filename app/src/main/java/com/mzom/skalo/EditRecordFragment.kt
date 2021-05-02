package com.mzom.skalo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.NumberPicker
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Screen for creating or editing weight measurement record
 */
class EditRecordFragment : Fragment() {

    companion object {
        fun newInstance() = EditRecordFragment()
    }

    private lateinit var viewModel: ScaleViewModel

    private lateinit var weightEditPicker: FloatPicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScaleViewModel::class.java)

        weightEditPicker = view.findViewById(R.id.scale_edit_weight)
        weightEditPicker.wrapSelectorWheel = false
        // TODO Set current weight if editing
        // TODO Set previous record value if creating new record (with fallback)
        weightEditPicker.setFloatValue(64.5f)

        val recordButton = view.findViewById<FloatingActionButton>(R.id.scale_record_button)
        recordButton.setOnClickListener { recordWeight() }
    }

    private fun recordWeight(){
//        viewModel.recordWeight(weightEditPicker.value)
//        println(weightEditPicker.getFloatValue())
        viewModel.recordWeight(weightEditPicker.getFloatValue())
        findNavController().navigate(
            EditRecordFragmentDirections.actionScaleFragmentToRecordFragment()
        )
    }

}