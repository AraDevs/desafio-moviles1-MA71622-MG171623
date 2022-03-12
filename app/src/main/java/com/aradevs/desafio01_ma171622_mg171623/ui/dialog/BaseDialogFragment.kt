package com.aradevs.desafio01_ma171622_mg171623.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.c3rberuss.androidutils.disableRotation

open class BaseDialogFragment : DialogFragment() {

    private var currentScreenOrientation: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentScreenOrientation = requireActivity().requestedOrientation
        disableRotation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        dialog?.setCancelable(false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun dismiss() {
        if (isAdded) {
            super.dismiss()
        }
    }

    override fun onDestroy() {
        requireActivity().requestedOrientation = currentScreenOrientation
        super.onDestroy()
    }
}