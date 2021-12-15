package ru.grandibambino.remembermeplease.domain.addfreinds

import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.Friend

interface AddFriendRepository {

    suspend fun addFriend(friend: Friend)

}