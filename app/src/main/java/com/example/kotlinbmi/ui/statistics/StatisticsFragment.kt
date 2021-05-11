package com.example.kotlinbmi.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinbmi.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class StatisticsFragment : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        statisticsViewModel =
                ViewModelProvider(this).get(StatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)
        val textView: TextView = root.findViewById(R.id.text_statistics)
        textView.text = "COVID DEATHS % BY BMI\n \nsrc: https://mexicobariatriccenter.com/obesity-increases-risk-covid-19-coronavirus"

        val pieChart: PieChart = root.findViewById(R.id.pieChart)

        pieChart.setUsePercentValues(true)
        pieChart.setExtraOffsets(5F, 10F, 5F, 5F)
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 61f

        val underWeight : PieEntry = PieEntry(0.6.toFloat(), "< 18.5 BMI")
        val normal : PieEntry = PieEntry(27.7.toFloat(), "18.5-24.9 BMI")
        val overWeight : PieEntry = PieEntry(31.6.toFloat(), "25-29.9 BMI")
        val obesity : PieEntry = PieEntry(32.8.toFloat(), "30-39.9 BMI")
        val morbidObese : PieEntry = PieEntry(7.3.toFloat(), "40+ BMI")

        val values = listOf<PieEntry>(underWeight, normal, overWeight, obesity, morbidObese)

        val dataSet: PieDataSet = PieDataSet(values, "% deaths")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toMutableList()

        val pieData: PieData = PieData(dataSet)

        pieData.setValueTextSize(15f)
        pieData.setValueTextColor(Color.WHITE)

        pieChart.data = pieData

        return root
    }
}

