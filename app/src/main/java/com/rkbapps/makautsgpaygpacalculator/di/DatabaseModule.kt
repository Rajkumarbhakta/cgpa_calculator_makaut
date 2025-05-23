package com.rkbapps.makautsgpaygpacalculator.di

import android.content.Context
import androidx.room.Room
import com.rkbapps.makautsgpaygpacalculator.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDgpaMidSemMarksDao(appDatabase: AppDatabase) = appDatabase.dgpaMidSemMarksDao()

    @Provides
    @Singleton
    fun providesGpaPercentageDao(appDatabase: AppDatabase) = appDatabase.gpaPercentageDao()

    @Provides
    @Singleton
    fun providesYearMarksDao(appDatabase: AppDatabase) = appDatabase.yearlyMarksDao()


}