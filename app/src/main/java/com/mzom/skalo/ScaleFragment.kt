package com.mzom.skalo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScaleFragment : Fragment() {

    companion object {
        fun newInstance() = ScaleFragment()
    }

    private lateinit var viewModel: ScaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scale_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScaleViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weightEditText = view.findViewById<EditText>(R.id.scale_edit_weight)
        weightEditText.onDone { recordWeight() }

        val recordButton = view.findViewById<FloatingActionButton>(R.id.scale_record_button)
        recordButton.setOnClickListener { recordWeight() }
    }

    private fun recordWeight(){
        findNavController().navigate(
            ScaleFragmentDirections.actionScaleFragmentToRecordFragment()
        )
    }

    private fun EditText.onDone(callback: () -> Unit) {
        // These lines optional if you don't want to set in Xml
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.invoke()
                return@setOnEditorActionListener true
            }
            false
        }
    }

}