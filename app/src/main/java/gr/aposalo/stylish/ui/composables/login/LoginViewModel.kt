package gr.aposalo.stylish.ui.composables.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.uc.auth.AuthUC
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUC: AuthUC
) : ViewModel() {

    private val _uiEvent = Channel<LoginEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun login() {
        authUC(
            email = email.value,
            password = password.value
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    _isLoading.value = false
                    _uiEvent.send(LoginEvents.LoginSuccess)
                }

                is Resource.Error -> {
                    _isLoading.value = false
                    _uiEvent.send(LoginEvents.LoginError)
                }

                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }

        }.launchIn(viewModelScope)
    }
}

sealed class LoginEvents{
    data object LoginSuccess: LoginEvents()
    data object LoginError: LoginEvents()
}

