package ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendsInteractor
import javax.inject.Inject

class AddFriendViewModelFactory
@AssistedInject constructor(
    private val addFriendsInteractor: AddFriendsInteractor
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == AddFriendViewModel::class.java)
        return AddFriendViewModel(addFriendsInteractor) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(): AddFriendViewModelFactory
    }
}