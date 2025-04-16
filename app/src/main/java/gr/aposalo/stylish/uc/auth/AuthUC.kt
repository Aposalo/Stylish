package gr.aposalo.stylish.uc.auth

import gr.aposalo.stylish.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthUC @Inject constructor(
    private val authRepository: AuthRepository
)  {
    operator fun invoke(email: String, password: String) = authRepository.login(email, password)
}