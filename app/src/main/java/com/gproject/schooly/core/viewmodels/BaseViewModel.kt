package com.gproject.schooly.core.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

abstract class BaseViewModel<UiState : ViewState, Event : ViewEvent, Effect : ViewSideEffect> :
    ViewModel() {


    abstract fun setInitialState(): UiState
    abstract fun handleEvents(event: Event)

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val viewState: StateFlow<UiState> = _viewState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvent()
    }

    private fun subscribeToEvent() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    fun setEventWithSideEffect(event: Event, builder: () -> Effect) {
        setEvent(event)
        setEffect(builder)
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    protected fun getState() = _viewState.value
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }


    fun <T> Flow<T>.launchAndCollect(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onStart: () -> Unit = {},
        onComplete: (Throwable?) -> Unit = {},
        skipAfterInitial: Boolean = true
    ): Job? {
        return if (initialState == _viewState && skipAfterInitial) {
            null
        } else {
            viewModelScope.launch {
                this@launchAndCollect.flowOn(
                    Dispatchers.IO
                ).onStart {
                    onStart()
                }.onCompletion { cause ->
                    onComplete(cause)
                }.catch { exception ->
                    onError(exception)
                }.safeCollect { result ->
                    onSuccess(result)
                }
            }
        }
    }


    private suspend inline fun <T> Flow<T>.safeCollect(crossinline action: suspend (T) -> Unit) {
        collect {
            coroutineContext.ensureActive()
            action(it)
        }
    }
}