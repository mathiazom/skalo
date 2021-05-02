package com.mzom.skalo

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.NumberPicker
import java.lang.IllegalArgumentException


/**
 * Wrapper of NumberPicker for picking float values
 */
class FloatPicker(context: Context?, attrs: AttributeSet?) : NumberPicker(context, attrs) {

    private var minFloat: Float = 0.0f
    private var maxFloat: Float = 1.0f

    init {
        this.processAttributeSet(context, attrs)
        // Raw input type to obtain string input while still displaying appropriate keyboard
        InputUtils.extractEditText(this)?.setRawInputType(InputType.TYPE_CLASS_NUMBER)
    }

    private fun processAttributeSet(context: Context?, attrs: AttributeSet?) {
        if (context != null && attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.FloatPicker)

            this.minFloat = attributes.getFloat(R.styleable.FloatPicker_min_float, minFloat)
            this.setMinValue(this.minFloat)
            this.maxFloat = attributes.getFloat(R.styleable.FloatPicker_max_float, maxFloat)
            this.setMaxValue(this.maxFloat)

            attributes.recycle()
        }
    }

    /**
     * Updates list of pickable values based on lower and upper bound
     */
    private fun updateDisplayValues() {
        this.displayedValues = ((minFloat*10).toInt()..(maxFloat*10).toInt())
                .map{String.format("%.1f", it / 10.0f)}
                .toTypedArray()
    }

    /**
     * Picks a float value. Will be adjusted if out of bounds
     */
    fun setFloatValue(value: Float) {

        when {
            value < minFloat -> {
                super.setValue(minValue)
            }
            value > maxFloat -> {
                super.setValue(maxValue)
            }
            else -> {
                super.setValue(indexFromValue(value))
            }
        }

    }

    /**
     * Defines upper bound of pickable values
     */
    fun setMaxValue(maxValue: Float) {
        this.maxFloat = maxValue
        updateDisplayValues()
        // Set max index of pickable list
        super.setMaxValue(this.displayedValues.size - 1)
    }

    /**
     * Defines lower bound of pickable values
     */
    fun setMinValue(minValue: Float) {
        this.minFloat = minValue
        updateDisplayValues()
        // Set min index of pickable list (always 0)
        super.setMinValue(0)
    }

    /**
     * Retrieves picked float value
     */
    fun getFloatValue(): Float {
        return ((super.getValue() + this.minValue).toFloat()) / 10.0f
    }

    /**
     * Converts desired float value to index in pickable values
     */
    private fun indexFromValue(value: Float): Int {

        if (value < minFloat || value > maxFloat) {
            throw IllegalArgumentException("Float value $value out of bounds ($minFloat, $maxFloat)")
        }

        return ((value - this.minFloat)*10).toInt()

    }

}