package gr.aposalo.stylish.ui.composables.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import gr.aposalo.stylish.R
import gr.aposalo.stylish.ui.composables.shared.LoadedView
import gr.aposalo.stylish.ui.theme.Typography

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClicked: () -> Unit,
){
    val email = viewModel.email.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is LoginEvents.LoginSuccess -> {
                    onLoginClicked()
                }

                is LoginEvents.LoginError -> {
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoadedView(
        loading = isLoading,
    ){
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 28.dp)
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(
                        text = stringResource(R.string.welcome_back),
                        color = Color.Black,
                        style = Typography.headlineMedium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    label = {
                        Text(text = stringResource(R.string.username_or_email))
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = colorScheme.onPrimaryContainer,
                        unfocusedTextColor = colorScheme.onPrimaryContainer,
                        unfocusedLabelColor = colorScheme.onPrimaryContainer,
                        cursorColor = colorScheme.onPrimaryContainer,
                        focusedContainerColor = colorScheme.primaryContainer,
                        unfocusedContainerColor = colorScheme.primaryContainer,
                        unfocusedBorderColor = colorScheme.outline,
                        focusedBorderColor = colorScheme.outline,
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = null
                        )
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                    },
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = stringResource(R.string.password))
                    },
                    visualTransformation = if (passwordVisible.value)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = colorScheme.onPrimaryContainer,
                        unfocusedTextColor = colorScheme.onPrimaryContainer,
                        unfocusedLabelColor = colorScheme.onPrimaryContainer,
                        cursorColor = colorScheme.onPrimaryContainer,
                        focusedContainerColor = colorScheme.primaryContainer,
                        unfocusedContainerColor = colorScheme.primaryContainer,
                        unfocusedBorderColor = colorScheme.outline,
                        focusedBorderColor = colorScheme.outline,
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        val color = if (passwordVisible.value)
                            colorScheme.secondary
                        else
                            colorScheme.onPrimaryContainer

                        Icon(
                            modifier = Modifier.clickable{
                                passwordVisible.value = !passwordVisible.value
                            },
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = null,
                            tint = color,
                        )
                    }
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    onClick = {
                        viewModel.login()
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.secondary,
                        contentColor = colorScheme.onSecondary,
                        disabledContainerColor = colorScheme.secondary,
                        disabledContentColor = colorScheme.onSecondary
                    )
                ) {
                    Text(
                        text = stringResource(R.string.login),
                        style = Typography.headlineSmall
                    )
                }
            }
        }
    }



}