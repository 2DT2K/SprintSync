package com.sprintsync.ui_tests.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.sprintsync.ui.components.BottomNavigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BottomNavigationTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Before
	fun setUp() {
		val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
		composeTestRule.setContent {
			BottomNavigation(navController = navController)
		}
	}

	@Test
	fun testBottomNavigation_InitialRouteExists() {
		composeTestRule.onNodeWithText("Home").assertIsDisplayed()
		composeTestRule.onNodeWithText("Project").assertIsDisplayed()
		composeTestRule.onNodeWithText("Calendar").assertIsDisplayed()
		composeTestRule.onNodeWithText("Profile").assertIsDisplayed()
	}

	@Test
	fun testBottomNavigation_NavigateToProject() {
		composeTestRule.onNodeWithText("Project").performClick()
		composeTestRule.onNodeWithText("Project").assertIsSelected()
	}

	@Test
	fun testBottomNavigation_NavigateToCalendar() {
		composeTestRule.onNodeWithText("Calendar").performClick()
		composeTestRule.onNodeWithText("Calendar").assertIsSelected()
	}

	@Test
	fun testBottomNavigation_NavigateToProfile() {
		composeTestRule.onNodeWithText("Profile").performClick()
		composeTestRule.onNodeWithText("Profile").assertIsSelected()
	}

	@Test
	fun testBottomNavigation_NavigateToHome() {
		composeTestRule.onNodeWithText("Home").performClick()
		composeTestRule.onNodeWithText("Home").assertIsSelected()
	}
}