package com.raystatic.expensemanagercompose.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class User(
    val avatar: String,
    val createdAt: String,
    val email: String,

    @PrimaryKey
    val id: Int,
    val name: String,
    val updatedAt: String
)