package com.android.fundamentals.workshop04.task

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.fundamentals.R

class WS04AssignmentDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_ws04, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val okBtn = view.findViewById<Button>(R.id.btn_ok)
        okBtn.setOnClickListener {
            Toast.makeText(context, "ok dialog", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        val cancelBtn = view.findViewById<Button>(R.id.btn_cancel)
        cancelBtn.setOnClickListener {
            Toast.makeText(context, "cancel dialog", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        Toast.makeText(context, "outside dialog", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog?.window?.setLayout(width, height)
    }
}