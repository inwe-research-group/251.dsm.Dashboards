package com.example.dashboards.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
    val total=datos.sumOf { it.value.toDouble() }.toFloat()

    datos.mapIndexed { index, datos ->
        slices.add(
            PieChartData.Slice(
            value=datos.value,
            color= Utils().colorAleatorio()
        ))
    }
    Column(
        modifier = Modifier
            .padding(30.dp,80.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
        Spacer(modifier=Modifier.height(1.dp))
        //leyenda con % y valores
        datos.forEach{
            val porcentaje=(it.value/total*100).toInt()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier=Modifier.padding(vertical=4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Utils().colorAleatorio(), shape = CircleShape)
                )
                Spacer(modifier=Modifier.width(8.dp))
                Text("${it.label}: ${it.value} (${porcentaje}%)")
            }
        }
    }

}