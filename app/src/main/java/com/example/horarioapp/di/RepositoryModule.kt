package com.example.horarioapp.di


import com.example.horarioapp.data.repository.AuthRepositoryImpl
import com.example.horarioapp.data.repository.ScheduleRepositoryImpl
import com.example.horarioapp.domain.repository.AuthRepository
import com.example.horarioapp.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository
}