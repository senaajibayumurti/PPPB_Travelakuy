package com.example.ppapb_travelakuy.usermenu

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.ppapb_travelakuy.databinding.UsermenuOrderDetailFragmentBinding
import com.example.ppapb_travelakuy.db.model.TravelForHistory
import com.example.ppapb_travelakuy.listener.HistoryCrudListener
import com.example.ppapb_travelakuy.usersignin.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class OrderDetailFragment : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: UsermenuOrderDetailFragmentBinding
    private var strD: String = ""
    private var strT: String = ""
    private var random: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuOrderDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crud = MainActivity.get() as HistoryCrudListener

        with(binding) {
            val inf = intent.extras

            tvDesstination.text = inf?.getString("stationOne")
            tvOrigin.text = inf?.getString("stationTwo")

            val spinnerItems = arrayOf("Pilih Kelas", "Ekonomi", "Bisnis", "Eksekutif")
            val adapter = ArrayAdapter(this@OrderDetailFragment, android.R.layout.simple_spinner_item, spinnerItems)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            etClass.adapter = adapter

            etClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val item = parent.getItemAtPosition(position).toString()
                    when (item) {
                        "Ekonomi" -> {
                            random = (50000..100000).random() + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = "Rp. ${random.toString()}"
                        }
                        "Bisnis" -> {
                            random = (100000..200000).random()  + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = "Rp. ${random.toString()}"
                        }
                        "Eksekutif" -> {
                            random = (200000..300000).random()  + (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = "Rp. ${random.toString()}"
                        } else -> {
                            random = (inf?.getInt("price") ?: 0)
                            tvTotalPrice.text = "Rp. ${inf?.getInt("price").toString()}"
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Another interface callback
                }
            }

            etDatetime.setOnClickListener {
                showDatePicker()
            }

            btnOrder.setOnClickListener {
                crud.insertHistory(TravelForHistory(
                    userID = MainActivity.get().getID(),
                    station_one = inf?.getString("stationOne") ?: "",
                    station_two = inf?.getString("stationTwo") ?: "",
                    schedule_date = strD,
                    schedule_time = strT,
                    price = random
                ))
            }

        }
    }


    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // Set a minimum date if needed
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(Calendar.YEAR, year)
        selectedDate.set(Calendar.MONTH, month)
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        updateSelectedDate(selectedDate.time)
    }

    private fun updateSelectedDate(date: Date) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(date)
        strD = formattedDate
        showTimePicker()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            this,
            hour,
            minute,
            true // Set to true for 24-hour format, false for 12-hour format
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = Calendar.getInstance()
        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedTime.set(Calendar.MINUTE, minute)
        updateSelectedTime(selectedTime.time)
    }

    private fun updateSelectedTime(time: Date) {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedTime = timeFormat.format(time)
        strT = formattedTime
        binding.etDatetime.setText("$strD $strT")
    }
}