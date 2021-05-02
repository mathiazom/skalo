package com.mzom.skalo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzom.skalo.databinding.EditMeasurementFragmentBinding
import java.text.DateFormat
import java.util.*

/**
 * Screen for creating or editing weight measurement
 */
class EditMeasurementFragment : Fragment() {

    private val viewModel: ScaleViewModel by activityViewModels()

    private lateinit var binding: EditMeasurementFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.edit_measurement_fragment, container, false
        )

        binding.editMeasurementRegister.setOnClickListener { onRegisterMeasurement() }

        val calendar: Calendar = GregorianCalendar.getInstance()

        val dateString: String = DateFormat.getDateInstance(DateFormat.MEDIUM)
            .format(calendar.time)

        binding.editMeasurementDate.text = dateString

        val dayString: String? =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        binding.editMeasurementWeekDay.text = dayString

        binding.editMeasurementWeightPicker.apply {
            wrapSelectorWheel = true
            // TODO Set previous measurement value if registering new measurement (with fallback)
            val measurement: Measurement? = viewModel.measurement
            when {
                measurement != null -> {
                    setFloatValue(measurement.weight)
                }
                else -> {
                    setFloatValue(80.0f)
                }
            }
        }

        return binding.root
    }

    /**
     * Called when registering a new measurement with picked weight value and current datetime
     */
    private fun onRegisterMeasurement() {
        viewModel.measurement = Measurement(
            binding.editMeasurementWeightPicker.getFloatValue(),
            GregorianCalendar.getInstance().time
        )
        findNavController().navigate(
            EditMeasurementFragmentDirections.actionScaleFragmentToRecordFragment()
        )
    }

}