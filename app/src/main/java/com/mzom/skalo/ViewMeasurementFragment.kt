package com.mzom.skalo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzom.skalo.databinding.ViewMeasurementFragmentBinding
import java.text.DateFormat
import java.util.*

class ViewMeasurementFragment : Fragment() {

    private val viewModel: ScaleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewMeasurementFragmentBinding>(
            inflater,
            R.layout.view_measurement_fragment, container, false
        )

        binding.scaleViewModel = viewModel

        val measurement: Measurement? = viewModel.measurement
        measurement?.let {
            val calendar: Calendar = GregorianCalendar.getInstance()
            calendar.time = measurement.date

            val dateString: String =
                DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
                    .format(calendar.time)
            binding.measurementDate.text = dateString

            val dayString: String? =
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
            binding.measurementWeekDay.text = dayString

            binding.measurementEditButton.setOnClickListener {
                findNavController().navigate(
                    ViewMeasurementFragmentDirections.actionRecordFragmentToScaleFragment()
                )
            }
        }

        return binding.root
    }

}