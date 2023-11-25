package com.sprintsync.ui.components.reportview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.m3.style.m3ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.DefaultAlpha
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.sprintsync.ui.theme.spacing

@Composable
fun MainChart(chartEntryModel: ChartEntryModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(MaterialTheme.spacing.largeDefault)
                .background(color = Color.White)
        ) {
            // in case there is no data
            val datasetLineSPec = remember {
                arrayListOf<LineChart.LineSpec>()
            }
            datasetLineSPec.add(
                LineChart.LineSpec(
                    lineColor = Color(0xFF04BFDA).toArgb(),
                    lineBackgroundShader = DynamicShaders.fromBrush(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color(0xFF04BFDA).copy(DefaultAlpha.LINE_BACKGROUND_SHADER_START),
                                Color(0xFF04BFDA).copy(DefaultAlpha.LINE_BACKGROUND_SHADER_END),
                            )
                        )
                    )
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
}

@Preview(showBackground = true)
@Composable
fun MainChartPreview() {
    MainChart(
        chartEntryModel = entryModelOf(
            entriesOf(4f, 12f, 8f, 16f),
            entriesOf(12f, 16f, 4f, 12f)
        )
    )
}