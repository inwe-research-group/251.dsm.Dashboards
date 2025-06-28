package com.example.dashboards.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashboards.data.DatosDashboard
import com.example.dashboards.utils.Utils
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer


@Composable
fun PieScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text="Grafico de tipo Pie"
        )
        Pie()
    }
}
@Composable
fun Pie(){
    val datos= DatosDashboard.datos
    var slices=ArrayList<PieChartData.Slice>()

    datos.mapIndexed { index, datos ->
        slices.add(
            PieChartData.Slice(
            value=datos.value,
            color= Utils().colorAleatorio()
        ))
    }
    PieChart(
        modifier= Modifier
            .padding(30.dp,80.dp)
            .height(300.dp),
        sliceDrawer= SimpleSliceDrawer(
            sliceThickness=100f
        ),
        pieChartData=PieChartData(
            slices=slices
        )
    )
}