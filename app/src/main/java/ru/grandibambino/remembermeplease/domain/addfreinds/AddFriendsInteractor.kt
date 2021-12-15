package ru.grandibambino.remembermeplease.domain.addfreinds

import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.Friend

interface AddFriendsInteractor {

    suspend fun postFriend(friend: Friend)

}