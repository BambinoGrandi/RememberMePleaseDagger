package ru.grandibambino.remembermeplease.core.di.module

import dagger.Binds
import dagger.Module
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendsInteractor
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendsInteractorImpl

@Module
interface DomainModule {

    @Binds
    fun addFriendInteractor(addFriendsInteractorImpl: AddFriendsInteractorImpl): AddFriendsInteractor

}