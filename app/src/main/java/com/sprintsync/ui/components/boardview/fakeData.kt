package com.sprintsync.ui.components.boardview

import com.sprintsync.ui.components.boardview.boardview_category_item.IBoardviewCategoryItem

var faketask1: IBoardviewCategoryItem = IBoardviewCategoryItem(
    "Code Homepage",
    listOf("Homepage", "FE"),
    "Scrummer123",
    null,
    90,
    null,
)
var faketask2: IBoardviewCategoryItem = IBoardviewCategoryItem(
    "Play Dota 2",
    listOf("Game", "FE"),
    "Scrummer290",
    null,
    70,
    null,
)
var faketask3: IBoardviewCategoryItem = IBoardviewCategoryItem(
    "Lmao Task",
    listOf("Lmao", "Nothing"),
    "Scrummer13",
    null,
    1000,
    null,
)
var taskFakeData: List<IBoardviewCategoryItem> = listOf(
    faketask1, faketask2, faketask3
)
var fakedata: IBoardViewCategory = IBoardViewCategory(
    "IN PROGRESS",
    3,
    taskFakeData,
)