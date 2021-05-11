package com.example.kotlinbmi.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbmi.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        super.onCreate(savedInstanceState)
        val dietText: TextView = root.findViewById(R.id.diet)
        val thinDiet = "Breakfest\nGranola x1\n\nLunch\nCelery & Hummus x1\n\nDiner\nEasy Grilled Lemon Chicken x1\nCrispy Garlic Edamame x1"
        val normalDiet = "Breakfest\nSausage and Egg Breakfast Sandwich x1\nOranges x2\n\nLunch\nTuna Stuffed Tomato\nCottage Cheese & Strawberries x2\n\nDinner\nChicken and Ranch Wrap x2\nSkinny Garlic Parmesan Zoodles x2"
        val fatDiet = "Breakfest\nOatmeal Cottage Cheese Pancakes x3\nButtered Toast x3\n\nLunch\nTuna Stuffed Pepper x3\nAlmonds x2\nLemon Avocado Salad x1\n\nDinner\nFried Egg and Ham Sandwich x3\nToast with Tomato and Hummus x1"
        val photo: ImageView = root.findViewById(R.id.diet_photo)

        setFragmentResultListener("requestKey") { key, bundle ->
            // Any type can be passed via to the bundle
            val bmiData = bundle.getString("data")

            if(bmiData != null) {
                val bmi = bmiData.toDouble()
                if(bmi > 18.5 && bmi < 25) {
                    dietText.text = normalDiet
                    photo.setImageResource(R.drawable.normal)
                }
                else if (bmi > 25) {
                    dietText.text = thinDiet
                    photo.setImageResource(R.drawable.thin)
                }
                else {
                    dietText.text = fatDiet
                    photo.setImageResource(R.drawable.fat)
                }
            }

        }
        return root
    }
}