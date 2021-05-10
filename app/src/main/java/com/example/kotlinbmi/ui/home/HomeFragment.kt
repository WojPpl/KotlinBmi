package com.example.kotlinbmi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbmi.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val button: Button = root.findViewById(R.id.calculate)
        val weightEdit: EditText = root.findViewById(R.id.weight)
        val heightEdit: EditText = root.findViewById(R.id.height)
        val result: TextView = root.findViewById(R.id.textBmi)
        val male: RadioButton = root.findViewById(R.id.radioMale)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val weight : Float = weightEdit.text.toString().toFloat()
                val height : Float = heightEdit.text.toString().toFloat()
                val bmi : Float = (weight / ((height*height) / 100))*100;
                

                result.text = "Your BMI: " + "%.1f".format(bmi).toDouble().toString() + "\nYou can eat " + male.isChecked.toString()
            }
        })

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}