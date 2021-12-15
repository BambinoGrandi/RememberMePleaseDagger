package ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel

import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.grandibambino.remembermeplease.domain.addfreinds.AddFriendsInteractor
import java.time.LocalDate
import java.util.*

class AddFriendViewModel(
    private val addFriendsInteractor: AddFriendsInteractor
) : ViewModel() {

    private val edit: MutableLiveData<Boolean> = MutableLiveData()

    private var firstName: String? = null
    private var secondName: String? = null
    private var dataBirthday: LocalDate? = null
    private var gender: String? = null
    private var kinship: String? = null

    private val _friend: MutableStateFlow<Friend> = MutableStateFlow(Friend())
    val friend: StateFlow<Friend> = _friend

    fun clickButtonAddFriend() {
        _friend.value = createFriend()
        viewModelScope.launch {
            addFriendsInteractor.postFriend(_friend.value)
            Log.e("TAG", friend.value.toString())
        }
    }

    private fun createFriend(): Friend {
        _friend.value = Friend(
            id = UUID.randomUUID().toString(),
            firstName,
            secondName,
            dataBirthday,
            gender,
            kinship
        )
        return _friend.value
    }

    fun setFirstName(name: String) {
        firstName = name
        createFriend()
        edit.postValue(true)
    }

    fun setLastName(secondName: String) {
        this.secondName = secondName
        createFriend()
        edit.postValue(true)
    }


    fun setBirthday(day: Int, month: Int, year: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataBirthday = LocalDate.of(year, month, day)
            createFriend()
            edit.postValue(true)
        }
    }

    fun setGender(gender: String) {
        this.gender = gender
        createFriend()
        edit.postValue(true)
    }

    fun setKinship(kinship: String) {
        this.kinship = kinship
        createFriend()
        edit.postValue(true)
    }

}