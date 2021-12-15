package ru.grandibambino.remembermeplease.presentation.add_friend.viewmodel

import java.time.LocalDate

data class Friend(
    var id: String? = null,
    var firstName: String? = null,
    var secondName: String? = null,
    var dataBirthday: LocalDate? = null,
    var gender: String? = null,
    var kinship: String? = null
)