package com.sprintsync.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sprintsync.ui.components.BottomNavigation
import com.sprintsync.ui.components.TwoIconTopAppBar
import com.sprintsync.ui.components.boardview.BoardViewCategory
import com.sprintsync.ui.components.boardview.fakedata

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BoardView() {
	var pageCount = 4

	@OptIn(ExperimentalFoundationApi::class)
	val pagerState = rememberPagerState(pageCount = {
		4
	})

	Scaffold(
		topBar = {
			TwoIconTopAppBar(title = "BoardView", backgroundColor = Color(0xFFEADDFF))
		},
		bottomBar = {
			BottomNavigation()
		}
	) { innerPadding ->
		Box(
			Modifier
				.padding(
					innerPadding
				)
				.fillMaxHeight()
				.padding(top = 20.dp, start = 0.dp, end = 0.dp, bottom = 20.dp)
		) {
			HorizontalPager(
				state = pagerState,
				contentPadding = PaddingValues(20.dp),
			) {
				Row(

					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.Center
				) {
					BoardViewCategory(boardviewCategory = fakedata)
				}

			}
			Row(
				Modifier
					.fillMaxWidth()
					.align(Alignment.BottomCenter),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
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