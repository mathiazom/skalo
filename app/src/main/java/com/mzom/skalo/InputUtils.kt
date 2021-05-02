package com.mzom.skalo

import android.view.ViewGroup
import android.widget.EditText

class InputUtils {

    companion object {
        /**
         * Traverse children tree and return first EditText, or null if none is found
         */
        fun extractEditText(vg: ViewGroup) : EditText? {
            (0..vg.childCount).map { vg.getChildAt(it) }.forEach {
                when (it) {
                    is ViewGroup -> return extractEditText(it)
                    is EditText -> return it
                }
            }
            return null
        }
    }

}