package gr.aposalo.stylish.data.repositories

import gr.aposalo.stylish.common.Resource
import gr.aposalo.stylish.data.network.ApiCalls
import gr.aposalo.stylish.data.network.dto.login.LoginRequest
import gr.aposalo.stylish.domain.models.AuthResult
import gr.aposalo.stylish.domain.preferences.Preferences
import gr.aposalo.stylish.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val apiCalls : ApiCalls,
    private val preferences: Preferences
):AuthRepository {
    override fun login(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            try {
                emit(Resource.Loading())
                val loginRequest = LoginRequest(username = email, password = password)
                val response = apiCalls.login(loginRequest = loginRequest)
                preferences.saveAccessToken(response.token)
                emit(
                    Resource.Success(
                        AuthResult(
                            success = true
                        )
                    )
                )
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()!!
                emit(Resource.Error(errorBody, e.code()))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server!", 0))
            }
        }
    }
}