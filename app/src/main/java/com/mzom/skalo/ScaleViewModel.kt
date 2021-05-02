package com.mzom.skalo

import android.util.Log
import androidx.lifecycle.ViewModel

class ScaleViewModel : ViewModel() {

    var weight = 0.0f

    fun recordWeight(weight: Float) {
        println("W $weight")
        this.weight = weight
        println("W2 " + this.weight.toString())
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ScaleViewModel", "ScaleViewModel destroyed!")
    }

}