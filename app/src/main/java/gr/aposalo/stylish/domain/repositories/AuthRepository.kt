package gr.aposalo.stylish.domain.repositories

import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.domain.models.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(email: String, password: String): Flow<Resource<AuthResult>>
}