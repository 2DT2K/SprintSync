package com.sprintsync.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.boardview_components.BoardViewCategory
import com.sprintsync.ui.components.boardview_components.BoardViewTopBar
import com.sprintsync.ui.components.boardview_components.fakedata
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BoardView() {
    var pageCount = 4

    @OptIn(ExperimentalFoundationApi::class)
    val pagerState = rememberPagerState(initialPage = 1, initialPageOffsetFraction = 0f)

    Scaffold(
        topBar = {
            BoardViewTopBar()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            HorizontalPager(state = pagerState, pageCount = pageCount) {
                Row(modifier = Modifier.fillMaxWidth()
//                    .graphicsLayer {
//                    // Calculate the absolute offset for the current page from the
//                    // scroll position. We use the absolute value which allows us to mirror
//                    // any effects for both directions
//                    val pageOffset = (
//                            (pagerState.currentPage - 20) + pagerState
//                                .currentPageOffsetFraction
//                            ).absoluteValue
//
//                    // We animate the alpha, between 50% and 100%
//                    alpha = lerp(
//                        start = 0.5f,
//                        stop = 1f,
//                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                    )
//                }
                ) {
                    BoardViewCategory(boardviewCategory = fakedata)
                }

            }
            Row(
                Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun BoardViewPreview() {
    BoardView()
}