package com.android.fundamentals.workshop04

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.fundamentals.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SampleBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_ws04, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val okBtn = view.findViewById<Button>(R.id.btn_ok)
        okBtn.setOnClickListener {
            Toast.makeText(context, "ok bottomSheet", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val cancelBtn = view.findViewById<Button>(R.id.btn_cancel)
        cancelBtn.setOnClickListener {
            Toast.makeText(context, "cancel bottomSheet", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Toast.makeText(context, "outside bottomSheet", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(
        dialog: Dialog,
        style: Int
    ) {
        val contentView =
            View.inflate(context, R.layout.dialog_fragment_ws04, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                contentView.context,
                android.R.color.transparent
            )
        )
    }
}