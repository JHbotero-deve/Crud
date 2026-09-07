package com.sena.crud.domain.repository

import com.sena.crud.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeTasks(): Flow<List<TaskModel>>
    suspend fun addTask(task: TaskModel): String
    suspend fun updateTask(taskId: String, changes: Map<String, Any>)
    suspend fun deleteTask(taskId: String)
}
