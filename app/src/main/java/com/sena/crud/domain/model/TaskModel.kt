package com.sena.crud.domain.model

import com.google.firebase.Timestamp

data class TaskModel(
    val id: String = "",
    val title: String = "",
    val completed: Boolean = false,
    val createdAt: Timestamp? = null
)
