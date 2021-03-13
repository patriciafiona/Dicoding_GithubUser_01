package com.path_studio.githubuser.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.path_studio.githubuser.Activities.MainActivity
import com.path_studio.githubuser.Models.ContributionData
import com.path_studio.githubuser.R
import lecho.lib.hellocharts.gesture.ZoomType
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.view.ColumnChartView


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        //set contribution chart
        setCharts(rootView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Show Search Bar
        (activity as MainActivity).setSearchBarVisibility(1)
        (activity as MainActivity).clearSearchBar()
    }

    private fun setCharts(view: View){
        val contrib_chart: ColumnChartView = view.findViewById(R.id.contributions_chart)
        contrib_chart.isInteractive = true
        contrib_chart.zoomType = ZoomType.HORIZONTAL_AND_VERTICAL

        //Onclick Listener
        contrib_chart.setOnClickListener {
            Toast.makeText(activity, "Your profile contributions graph is a record of contributions you've made to GitHub repositories.", Toast.LENGTH_LONG).show()
        }

        //In most cased you can call data model methods in builder-pattern-like manner.
        val column: Column = Column().setValues(ContributionData.contributionData())
        column.setHasLabels(true)
        val columns: MutableList<Column> = ArrayList()
        columns.add(column)

        val data = ColumnChartData()
        data.columns = columns
        data.axisXBottom = Axis.generateAxisFromRange(1f,12f, 1f)

        contrib_chart.columnChartData = data
    }

}