package com.example.medicinecontrolsystem

import com.example.medicinecontrolsystem.customFunctions.CompletedTaskViewModel
import com.example.medicinecontrolsystem.customFunctions.MedicineTakingStateViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideCompletedTaskViewModel(): CompletedTaskViewModel{
        return CompletedTaskViewModel()
    }

    @Provides
    @Singleton
    fun provideMedicineTakingStateViewModel(): MedicineTakingStateViewModel{
        return MedicineTakingStateViewModel()
    }
}