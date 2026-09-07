package com.sena.crud.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sena.crud.domain.model.TaskModel
import com.sena.crud.domain.repository.TaskRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : TaskRepository {
    private val tasksRef = db.collection("tasks")

    override fun observeTasks(): Flow<List<TaskModel>> = callbackFlow {
        val listener = tasksRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val tasks = snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(TaskModel::class.java)?.copy(id = doc.id)
            } ?: emptyList()
            trySend(tasks)
        }
        awaitClose { listener.remove() }
    }

    override suspend fun addTask(task: TaskModel): String {
        val ref = tasksRef.add(task).await()
        return ref.id
    }

    override suspend fun updateTask(taskId: String, changes: Map<String, Any>) {
        tasksRef.document(taskId).update(changes).await()
    }

    override suspend fun deleteTask(taskId: String) {
        tasksRef.document(taskId).delete().await()
    }
}
