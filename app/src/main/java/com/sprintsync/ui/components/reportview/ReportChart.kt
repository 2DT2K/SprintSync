package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
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
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
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

    var sprintName by remember {
        mutableStateOf("Sprint 2")
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
    ) {
        ChartTitle(
            sprintName = sprintName,
            onSprintNameChange = {
                sprintName = it
            }
        )
        MainChart(chartEntryModel = chartEntryModel)
        ChartInfor(remaining = 10, completed = 20, remainingColor = Color(0xFF04BFDA), completedColor = Color(0xFF04BFDA))
    }
}

@Preview(showBackground = true)
@Composable
fun ReportChartPreview() {
	ReportChart()
}

