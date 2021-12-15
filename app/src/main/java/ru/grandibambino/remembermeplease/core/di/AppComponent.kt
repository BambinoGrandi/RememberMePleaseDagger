package ru.grandibambino.remembermeplease.core.di

import dagger.Component
import ru.grandibambino.remembermeplease.core.di.module.DomainModule
import ru.grandibambino.remembermeplease.core.di.module.FirebaseModule
import ru.grandibambino.remembermeplease.core.di.module.RepositoryModule
import ru.grandibambino.remembermeplease.presentation.add_friend.AddFriendFragment

@Component(
    modules = [
        FirebaseModule::class,
        RepositoryModule::class,
        DomainModule::class
    ]
)
interface AppComponent {

    fun inject(addFriendFragment: AddFriendFragment)

}