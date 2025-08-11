package com.rkbapps.makautsgpaygpacalculator.ui.screens.dgpa

data class DgpaScreenState(
    val currentSelectedCourseType: CourseType = CourseType.FOUR_YEAR_DEGREE,
    val firstSemMark: String = "",
    val secondSemMark:String = "",
    val thirdSemMark:String = "",
    val fourthSemMark:String = "",
    val fiveSemMark:String = "",
    val sixSemMark:String = "",
    val sevenSemMark:String = "",
    val eightSemMark:String = "",
    val result:Double? = null,
    val percentage:Double? = null
)
