package com.android.fundamentals.workshop04.task

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop04.SampleBottomSheet
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class WS04AssignmentActivity : AppCompatActivity() {
    private fun showToastForAlertDialog(context: Context, message: String) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws04)

        findViewById<Button>(R.id.btn_show_alert_dialog)?.apply {
            setOnClickListener {
                // TODO(WS4:1) set title, positive, negative and neutral buttons for AlertDialog.
                //  Make it show Toast when pressing button and show in toast what button was pressed
                //  * Make it show Toast about cancelling dialog only when it is closed by tapping outside
                //  * Make dialog not to be closed when tapped outside of fragment
                AlertDialog.Builder(context)
                    .setMessage("Alert !")
                    .setPositiveButton("OK") { _, _ ->
                        showToastForAlertDialog(context, "OK")
                    }
                    .setNegativeButton("CANCEL") { _, _ ->
                        showToastForAlertDialog(context, "CANCEL")
                    }
                    .setNeutralButton("RETRY") { _, _ ->
                        showToastForAlertDialog(context, "RETRY")
                    }
                    .setOnDismissListener {
                        showToastForAlertDialog(context, "dialog closed")
                    }
                    .show()
            }
        }

        findViewById<Button>(R.id.btn_show_dialog_fragment)?.apply {
            setOnClickListener {
                // TODO(WS4:2) show dialog fragment SampleDialogFragment.
                //  Change SampleDialogFragment to make it show Toasts as in alert dialog (previous task)
                val dialog = WS04AssignmentDialogFragment()
                dialog.show(supportFragmentManager, "dialogFragment")
            }
        }

        findViewById<Button>(R.id.btn_show_time_picker)?.apply {
            setOnClickListener {
                // TODO(WS4:3) make timePickerDialog to start with current time
                //  Show Snackbar with selected time
                val calendarInstance = Calendar.getInstance()
                val currentHour = calendarInstance.get(Calendar.HOUR_OF_DAY)
                val currentMinute = calendarInstance.get(Calendar.MINUTE)
                val timePickerDialog = TimePickerDialog(
                    this@WS04AssignmentActivity,
                    { _, hourOfDay, minute ->
                        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                        Snackbar.make(rootView, "Selected time: $selectedTime", Snackbar.LENGTH_SHORT).show()
                    },
                    currentHour,
                    currentMinute,
                    true
                )

                timePickerDialog.show()
            }
        }

        findViewById<Button>(R.id.btn_show_date_picker)?.apply {
            setOnClickListener {
                // TODO(WS4:4) make timePickerDialog to start with today date
                //  Show Snackbar with selected datek
                val calendarInstance = Calendar.getInstance()
                val currentYear = calendarInstance.get(Calendar.YEAR)
                val currentMonth = calendarInstance.get(Calendar.MONTH)
                val currentDay = calendarInstance.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this@WS04AssignmentActivity,
                    { p0, p1, p2, p3 ->
                        Snackbar.make(
                            rootView,
                            "you choosed $p3/${p2 + 1}/$p1",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    },
                    currentYear,
                    currentMonth,
                    currentDay
                )

                datePickerDialog.show()
            }
        }

        findViewById<Button>(R.id.btn_show_bottom_sheet_dialog)?.apply {
            setOnClickListener {
                // TODO(WS4:5) show dialog fragment SampleBottomSheet
                //  Look at difference between dialogFragment and BottomSheetFragment layouts drawing  and change dialog_fragment to show buttons under textview
                val bottomSheet = SampleBottomSheet()
                bottomSheet.show(supportFragmentManager, "sampleBottomSheet")
            }
        }
    }
}