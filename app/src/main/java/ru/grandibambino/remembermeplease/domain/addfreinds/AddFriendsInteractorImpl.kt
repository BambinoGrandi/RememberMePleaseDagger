package ru.grandibambino.remembermeplease.domain.addfreinds

import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.Friend
import javax.inject.Inject

class AddFriendsInteractorImpl
@Inject constructor(
    private val addFriendRepository: AddFriendRepository
) : AddFriendsInteractor {

    override suspend fun postFriend(friend: Friend) {

    }
}