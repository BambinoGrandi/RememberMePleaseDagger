package ru.grandibambino.remembermeplease.core.di.module

import dagger.Binds
import dagger.Module
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendRepository
import ru.grandibambino.remembermeplease.repository.addfriend.AddFriendRepositoryImpl

@Module
interface RepositoryModule {

    @Binds
    fun addFriendRepository(addFriendRepositoryImpl: AddFriendRepositoryImpl): AddFriendRepository

}