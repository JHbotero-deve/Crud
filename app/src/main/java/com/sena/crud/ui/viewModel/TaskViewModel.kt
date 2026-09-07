package com.sena.crud.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.crud.domain.model.TaskModel
import com.sena.crud.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TaskUiState(
    val tasks: List<TaskModel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        observeTasks()
    }

    private fun observeTasks() {
        viewModelScope.launch {
            repository.observeTasks()
                .catch { e -> _uiState.update { it.copy(error = e.message, isLoading = false) } }
                .collect { tasks ->
                    _uiState.update { it.copy(tasks = tasks, isLoading = false, error = null) }
                }
        }
    }

    fun addTask(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            try {
                repository.addTask(TaskModel(title = title, createdAt = com.google.firebase.Timestamp.now()))
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun toggleCompleted(task: TaskModel) {
        viewModelScope.launch {
            repository.updateTask(task.id, mapOf("completed" to !task.completed))
        }
    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            repository.deleteTask(task.id)
        }
    }
}
