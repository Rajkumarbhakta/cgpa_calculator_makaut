package com.rkbapps.makautsgpaygpacalculator.ui.screens.ygpa

import com.rkbapps.makautsgpaygpacalculator.db.dao.GpaPercentageDao
import com.rkbapps.makautsgpaygpacalculator.db.entity.GpaPercentage
import javax.inject.Inject

class SgpaYgpaPercentageRepository @Inject constructor(private val gpaPercentageDao: GpaPercentageDao) {

    suspend fun insert(gpaPercentage: GpaPercentage) = gpaPercentageDao.insert(gpaPercentage)

}