package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf


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
		ChartInfor(
			remaining = 10, completed = 20, remainingColor = Color(0xFF04BFDA),
			completedColor = Color(0xFF04BFDA)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun ReportChartPreview() {
	ReportChart()
}

