package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.sprintsync.R
import kotlin.random.Random


@Composable
fun ReportChart() {
//    var refreshDataSet by remember {
//        mutableIntStateOf(0)
//    }
//    val modelProducer = remember {
//        ChartEntryModelProducer()
//    }
//    // we will rebuild this one
//    val datasetForModel = remember {
//        mutableStateListOf(listOf<FloatEntry>())
//    }
//    val datasetLineSPec = remember {
//        arrayListOf<LineChart.LineSpec>()
//    }
//    val scrollState = rememberChartScrollState()
//    LaunchedEffect(key1 = refreshDataSet) {
//        datasetForModel.clear()
//        datasetLineSPec.clear()
//        var xPos = 0f
//        val dataPoints = arrayListOf<FloatEntry>()
//        datasetLineSPec.add(
//            LineChart.LineSpec(
//                lineColor = Color(0xFF04BFDA).toArgb(),
//                lineBackgroundShader = DynamicShaders.fromBrush(
//                    brush = Brush.verticalGradient(
//                        listOf(
//                            Color(0xFF04BFDA).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
//                            Color(0xFF04BFDA).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END),
//                        )
//                    )
//                )
//            )
//        )
//        for (i in 1..100) {
//            val randomYFloat = (1..1000).random().toFloat()
//            dataPoints.add(FloatEntry(x = xPos, y = randomYFloat))
//            xPos += 1f
//        }
//        datasetForModel.add(dataPoints)
//
//        modelProducer.setEntries(datasetForModel)
//    }

    val chartEntryModel = entryModelOf(entriesOf(4f, 12f, 8f, 16f), entriesOf(12f, 16f, 4f, 12f))
//    val chartEntryModelProducer = ChartEntryModelProducer(getRandomEntries(), getRandomEntries())

    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Title",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF243465),
                    letterSpacing = 0.28.sp,
                )
            )
            Box {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF848A9C),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(start = 8.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = "Sprint 2",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF848A9C),
                            letterSpacing = 0.18.sp,
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.mini_down_arrow),
                        contentDescription = "image description",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .padding(1.dp)
                            .width(16.dp)
                            .height(16.dp)
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded }) {

                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(10.dp)
                    .background(color = Color.White)
            ) {
                // in case there is no data
                val datasetLineSPec = remember {
                    arrayListOf<LineChart.LineSpec>()
                }
                datasetLineSPec.add(
                    LineChart.LineSpec(
                        lineColor = Color(0xFF04BFDA).toArgb(),
//                        lineBackgroundShader = DynamicShaders.fromBrush(
//                            brush = Brush.verticalGradient(
//                                listOf(
//                                    Color(0xFF04BFDA).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_START),
//                                    Color(0xFF04BFDA).copy(com.patrykandpatrick.vico.core.DefaultAlpha.LINE_BACKGROUND_SHADER_END),
//                                )
//                            )
//                        )
                    )

                )
                datasetLineSPec.add(
                    LineChart.LineSpec(
                        lineColor = Color.Gray.toArgb(),
                    )

                )
                ProvideChartStyle(
                    chartStyle = m3ChartStyle(
                        axisLabelColor = MaterialTheme.colorScheme.onBackground,
                        axisGuidelineColor = MaterialTheme.colorScheme.outline,
                        axisLineColor = MaterialTheme.colorScheme.outline,
                        entityColors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.tertiary,
                        ), elevationOverlayColor = Color.Red
                    )
                ) {
                    Chart(
                        modifier = Modifier.background(color = Color.White),
                        chart = lineChart(
                            lines = datasetLineSPec,
                        ),
                        model = chartEntryModel,
                        startAxis = rememberStartAxis(),
                        bottomAxis = rememberBottomAxis(),
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(
                            color = Color(0xFF04BFDA),
                            shape = RoundedCornerShape(size = 3.dp)
                        )
                ) {
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Remaining work",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF7B7B7B),
                        )
                    )
                    Text(
                        text = "8",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7B7B7B),
                            textAlign = TextAlign.Right,
                        )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(
                            color = Color(0xFFE1E3E8),
                            shape = RoundedCornerShape(size = 3.dp)
                        )
                ) {
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Completed work",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 15.6.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF7B7B7B),
                        )
                    )
                    Text(
                        text = "4",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF7B7B7B),
                            textAlign = TextAlign.Right,
                        )
                    )
                }
            }
        }
    }
}

fun getRandomEntries() = List(4) { entryOf(it, Random.nextFloat() * 16f) }

@Preview(showBackground = true)
@Composable
fun ReportChartPreview() {
    ReportChart()
}

