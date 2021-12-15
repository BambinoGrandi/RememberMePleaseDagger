package ru.grandibambino.remembermeplease.repository.addfriend

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendRepository
import ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel.Friend
import javax.inject.Inject

class AddFriendRepositoryImpl
@Inject constructor(
    private val firebaseFirestore: Firebase
) : AddFriendRepository {

    private val firebase = firebaseFirestore.firestore

    override suspend fun addFriend(friend: Friend) {
        firebase.collection("friend")
            .add(friend)
            .addOnSuccessListener {
                Log.d("firebase add", it.id)
            }
            .addOnFailureListener {
                Log.e("firebase error", it.stackTrace.toString())
            }
        Log.e("TAG", friend.toString())
    }

}