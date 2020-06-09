package com.gzeinnumer.newmaterialdaterangepickerkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnDateRange.setOnClickListener {
            val builder = MaterialDatePicker.Builder.dateRangePicker()

            //Membatasi Bulan
            val first = Calendar.getInstance()
            first.add(Calendar.MONTH, 0)
            val end = Calendar.getInstance()
            end.add(Calendar.MONTH, 0)
            val constraintBuilder = CalendarConstraints.Builder()
            constraintBuilder.setStart(first.timeInMillis)
            constraintBuilder.setEnd(end.timeInMillis)

            builder.setCalendarConstraints(constraintBuilder.build())
            val picker = builder.build()
            picker.show(childFragmentManager, "date_picker_tag")

            picker.addOnPositiveButtonClickListener {
                val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val awal = formater.format(Date(it.first ?: 0))
                val akhit = formater.format(Date(it.second ?: 0))
                Toast.makeText(requireContext(), "$awal - $akhit", Toast.LENGTH_SHORT).show()
            }

            picker.addOnNegativeButtonClickListener {
                Toast.makeText(requireContext(), "Tidak Jadi Memilih", Toast.LENGTH_SHORT).show()
            }
        }

        btnDate.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(childFragmentManager, "date_picker_tag")

            picker.addOnPositiveButtonClickListener {
                val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = formater.format(Date(it ?: 0))
                Toast.makeText(requireContext(), date, Toast.LENGTH_SHORT).show()
            }

            picker.addOnNegativeButtonClickListener {
                Toast.makeText(requireContext(), "Tidak Jadi Memilih", Toast.LENGTH_SHORT).show()
            }
        }
    }
}