package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.LoginViewModel
import com.fit2081.Tee_34570403.nutritrack.ui.theme.NutritrackTheme

sealed class LoginActivityScreen(val route: String) {
    object Login : LoginActivityScreen("login_screen")
    object Register : LoginActivityScreen("register_screen")
    object ForgotPassword : LoginActivityScreen("forgot_password_screen")
}

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutritrackTheme {
                val viewModel: LoginViewModel = ViewModelProvider(
                    this, LoginViewModel.LoginViewModelFactory(this@LoginActivity)
                )[LoginViewModel::class.java]

                // Create a navigation controller to handle navigation between different screens
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    containerColor = Color(112, 20, 252)
                    ) { innerPadding ->
                    LoginNavHostSection(
                        this@LoginActivity, // Pass the local context ("LoginScreen" in this case).
                        navController,
                        viewModel,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginNavHostSection(
    context: Context,
    navController: NavHostController,  // Navigation controller to handle screen transitions
    loginViewModel: LoginViewModel,  // ViewModel to access student data
    modifier: Modifier = Modifier  // Optional modifier for customizing layout
) {

    loginViewModel.clearCurrentUsers()

    // Set up navigation host to manage different login screens
    NavHost(
        navController = navController,  // Controller that handles navigation events
        startDestination = LoginActivityScreen.Login.route,  // Initial screen is staff login
        modifier = modifier.verticalScroll(rememberScrollState())  // Apply any modifiers passed to this composable
    ) {
        // Define the Staff Login screen destination
        composable(LoginActivityScreen.Login.route) {
            LoginScreen(context, modifier, loginViewModel, navController)  // Display the staff login interface
        }
        // Define the Student Login screen destination
        composable(LoginActivityScreen.Register.route) {
            RegisterScreen(context, modifier, loginViewModel, navController)  // Display student login with required ViewModel
        }
        composable(LoginActivityScreen.ForgotPassword.route){
            ForgotPasswordScreen(context, modifier, loginViewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(context: Context, modifier: Modifier = Modifier, viewModel: LoginViewModel, navController: NavHostController) {

    ModalDrawerSheet (
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(top = 60.dp),
        drawerShape = RoundedCornerShape(50.dp, 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.width(50.dp), thickness =  5.dp, color = Color.LightGray)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text("Log in", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(48.dp))

            ExposedDropdownMenuBox(
                expanded = viewModel.loginIDOptionsExpanded,
                onExpandedChange = { viewModel.updateLoginIDOptionsExpanded(!viewModel.loginIDOptionsExpanded) },
            ){
                OutlinedTextField(
                    value = "${viewModel.loginUserId}",
                    onValueChange = {},
                    readOnly = true,
                    isError = !viewModel.correctLoginUserId,
                    label = {
                        Text(
                            text = "My ID (Provided by your Clinician)",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "More options")
                    }
                )

                ExposedDropdownMenu(
                    expanded = viewModel.loginIDOptionsExpanded,
                    onDismissRequest = { viewModel.updateLoginIDOptionsExpanded(false) },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 0.dp
                        )
                        .menuAnchor()
                ) {

                    viewModel.registeredUserIds.value?.forEach(){ value1 ->
                        DropdownMenuItem(
                            text = { Text("$value1") },
                            onClick = {
                                viewModel.updateLoginUserId(value1)
                                viewModel.updateLoginIDOptionsExpanded(false)
                            }
                        )
                    }

                }
            }

            // Check whether the corresponding phone number for the user ID is correct
            if (!viewModel.correctLoginUserId) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid User Id", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(){
                OutlinedTextField(
                    value = viewModel.loginPassword,
                    label = {Text("Password",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctLoginPassword,
                    placeholder =  { Text(text = "Enter your password", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateLoginPassword(it)},
                    visualTransformation = if (!viewModel.loginPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateLoginPassVisible(!viewModel.loginPassVisibility) },
                            content = {if (!viewModel.loginPassVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Check whether the corresponding phone number for the user ID is correct
            if (!viewModel.correctLoginPassword) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid Password", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Text(text = "This app is only for pre-registered users. Please enter "
                    + "your ID and password or Register to claim your account on your first visit.",
                fontWeight = FontWeight.Medium, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {

                    viewModel.authoriseLogin()

                    // Check if the password matches the correct password from the corresponding userID.
                    if (viewModel.correctLoginUserId && viewModel.correctLoginPassword) {
                        viewModel.setCurrentUser(true)
                        viewModel.filledQuestionnaire()
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                        if (viewModel.filledQuestionnaire()) {
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        } else{
                            Toast.makeText(context, "Please Complete The Questionnaire", Toast.LENGTH_LONG).show()
                            context.startActivity(Intent(context, QuestionnaireActivity::class.java))
                        }
                    }

                    if (!viewModel.correctLoginUserId){
                        Toast.makeText(context, "Incorrect User ID", Toast.LENGTH_LONG).show()
                    }

                    if (!viewModel.correctLoginPassword){
                        Toast.makeText(context, "Incorrect Password", Toast.LENGTH_LONG).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Continue")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate(LoginActivityScreen.Register.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Register")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {navController.navigate(LoginActivityScreen.ForgotPassword.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black // Red Color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ) {
                Text("Forgot Password ?", textDecoration = TextDecoration.Underline)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row() {
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
                Spacer(modifier = Modifier.width(70.dp))
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CommitPrefEdits")
@Composable
fun RegisterScreen(context: Context, modifier: Modifier = Modifier, viewModel: LoginViewModel, navController: NavHostController) {

    ModalDrawerSheet (
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(top = 60.dp),
        drawerShape = RoundedCornerShape(50.dp, 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.width(50.dp), thickness =  5.dp, color = Color.LightGray)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text("Register", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(48.dp))

            ExposedDropdownMenuBox(
                expanded = viewModel.registerIDOptionsExpanded,
                onExpandedChange = { viewModel.updateRegisterIDOptionsExpanded(!viewModel.registerIDOptionsExpanded) },
            ){
                OutlinedTextField(
                    value = "${viewModel.registerUserId}",
                    onValueChange = {},
                    isError = !viewModel.correctRegisterUserId,
                    readOnly = true,
                    label = {
                        Text(
                            text = "My ID (Provided by your Clinician)",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "More options")
                    }
                )

                ExposedDropdownMenu(
                    expanded = viewModel.registerIDOptionsExpanded,
                    onDismissRequest = { viewModel.updateRegisterIDOptionsExpanded(false) },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 0.dp
                        ),
                ) {

                    viewModel.loadAllUnRegUserIds()

                    viewModel.unregisteredUserIds.value?.forEach(){ value1 ->
                        DropdownMenuItem(
                            text = { Text("$value1") },
                            onClick = {
                                viewModel.updateRegisterUserId(value1)
                                viewModel.updateRegisterIDOptionsExpanded(false)
                            },
                            modifier = Modifier.menuAnchor()
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!viewModel.correctRegisterUserId) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid User Id", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(){
                OutlinedTextField(
                    value = viewModel.name,
                    label = {Text("Name",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.notEmptyName,
                    placeholder =  { Text(text = "Enter your name", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateName(it)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!viewModel.notEmptyName) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("The name must not left empty", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(){
                OutlinedTextField(
                    value = viewModel.phoneNumber,
                    label = {Text("Phone Number",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctPhoneNumber,
                    placeholder =  { Text(text = "Enter your phone number", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updatePhoneNumber(it)},
                    visualTransformation = if (!viewModel.regPhoneNumVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateRegPhoneNumVisible(!viewModel.regPhoneNumVisibility) },
                            content = {if (!viewModel.regPhoneNumVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Check whether the corresponding phone number for the user ID is correct
            if (!viewModel.correctPhoneNumber) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid Phone Number", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(){
                OutlinedTextField(
                    value = viewModel.registerPassword,
                    label = {Text("Password",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctConfirmRegisterPassword,
                    placeholder =  { Text(text = "Enter your password", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateRegisterPassword(it)},
                    visualTransformation = if (!viewModel.regPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateRegPassVisible(!viewModel.regPassVisibility) },
                            content = {if (!viewModel.regPassVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(){
                OutlinedTextField(
                    value = viewModel.confirmRegisterPassword,
                    label = {Text("Confirm Password",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctConfirmRegisterPassword,
                    placeholder =  { Text(text = "Enter your password again", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateConfirmRegisterPassword(it)},
                    visualTransformation = if (!viewModel.regConPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateRegConPassVisible(!viewModel.regConPassVisibility) },
                            content = {if (!viewModel.regConPassVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Check password or confirm password is left blank
            if (!viewModel.correctConfirmRegisterPassword && (viewModel.registerPassword.isBlank() || viewModel.confirmRegisterPassword.isBlank())) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Confirm Password and Password Must Not Left Blank", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }


        // Check whether the corresponding confirm for the user ID is correct
            else if (!viewModel.correctConfirmRegisterPassword) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Confirm Password does not match with the Password", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Text(text = "This app is only for pre-registered users. Please enter "
                    + "your ID, name, phone number and password to claim your account.",
                fontWeight = FontWeight.Medium, fontSize = 14.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {

                    viewModel.authoriseRegistration()

                    // Check if the phone number matches the correct phone number from the corresponding userID.
                    if (viewModel.correctRegisterUserId && viewModel.correctPhoneNumber && viewModel.correctConfirmRegisterPassword && !viewModel.accountRegistered) {
                        viewModel.insertNewDetails()
                        viewModel.setCurrentUser(false)
                        Toast.makeText(context, "Register Successful", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context, QuestionnaireActivity::class.java))
                    }

                    if (viewModel.accountRegistered){
                        Toast.makeText(context, "The account has been registered.", Toast.LENGTH_LONG).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Register")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate(LoginActivityScreen.Login.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Login")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row() {
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
                Spacer(modifier = Modifier.width(70.dp))
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CommitPrefEdits")
@Composable
fun ForgotPasswordScreen(context: Context, modifier: Modifier = Modifier, viewModel: LoginViewModel, navController: NavHostController) {

    ModalDrawerSheet (
        modifier = Modifier
            .fillMaxWidth()
            .height(1000.dp)
            .padding(top = 60.dp),
        drawerShape = RoundedCornerShape(50.dp, 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.width(50.dp), thickness =  5.dp, color = Color.LightGray)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text("Forgot Password", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(48.dp))

            ExposedDropdownMenuBox(
                expanded = viewModel.forgotPasswordIDOptionsExpanded,
                onExpandedChange = { viewModel.updateForgotPassIDOptionsExpanded(!viewModel.forgotPasswordIDOptionsExpanded) },
            ){
                OutlinedTextField(
                    value = "${viewModel.forgotPasswordUserId}",
                    onValueChange = {},
                    isError = !viewModel.correctForgotPasswordUserId,
                    readOnly = true,
                    label = {
                        Text(
                            text = "My ID (Provided by your Clinician)",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    trailingIcon = {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "More options")
                    }
                )

                ExposedDropdownMenu(
                    expanded = viewModel.forgotPasswordIDOptionsExpanded,
                    onDismissRequest = { viewModel.updateForgotPassIDOptionsExpanded(false) },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 0.dp
                        ),
                ) {
                    viewModel.loadAllRegUserIds()

                    viewModel.registeredUserIds.value?.forEach(){ value1 ->
                        DropdownMenuItem(
                            text = { Text("$value1") },
                            onClick = {
                                viewModel.updateForgotPassUserId(value1)
                                viewModel.updateForgotPassIDOptionsExpanded(false)
                            },
                            modifier = Modifier.menuAnchor()
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!viewModel.correctForgotPasswordUserId) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid User Id", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(){
                OutlinedTextField(
                    value = viewModel.forgotPasswordPhoneNumber,
                    label = {Text("Phone Number",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctForgotPasswordPhoneNumber,
                    placeholder =  { Text(text = "Enter your phone number", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateForgotPassPhoneNumber(it)},
                    visualTransformation = if (!viewModel.forPhoneNumberVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateForPhoneNumVisible(!viewModel.forPhoneNumberVisibility) },
                            content = {if (!viewModel.forPhoneNumberVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Check whether the corresponding phone number for the user ID is correct
            if (!viewModel.correctForgotPasswordPhoneNumber) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Invalid Phone Number", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Column(){
                OutlinedTextField(
                    value = viewModel.forgotPasswordNewPassword,
                    label = {Text("New Password",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctConfirmForgotPassNewPassword,
                    placeholder =  { Text(text = "Enter your new password", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateForgotPassNewPass(it)},
                    visualTransformation = if (!viewModel.forPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateForPassVisible(!viewModel.forPassVisibility) },
                            content = {if (!viewModel.forPassVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(){
                OutlinedTextField(
                    value = viewModel.confirmForgotPasswordNewPassword,
                    label = {Text("Confirm New Password",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )},
                    isError = !viewModel.correctConfirmForgotPassNewPassword,
                    placeholder =  { Text(text = "Enter your new password again", color = Color.LightGray) },
                    singleLine = true,
                    onValueChange = {viewModel.updateForgotPassConfirmNewPass(it)},
                    visualTransformation = if (!viewModel.forConPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.updateForConPassVisible(!viewModel.forConPassVisibility) },
                            content = {if (!viewModel.forConPassVisibility)
                                Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                            else
                                Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Check password or confirm password is left blank
            if (!viewModel.correctConfirmForgotPassNewPassword && (viewModel.forgotPasswordNewPassword.isBlank() || viewModel.confirmForgotPasswordNewPassword.isBlank())) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Confirm New Password and New Password Must Not Left Blank", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }


            // Check whether the corresponding confirm for the user ID is correct
            else if (!viewModel.correctConfirmForgotPassNewPassword) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Confirm New Password does not match with the New Password", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            Text(text = "This app is only for pre-registered users. Please enter "
                    + "your ID, name, phone number and password to claim your account.",
                fontWeight = FontWeight.Medium, fontSize = 14.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {

                    viewModel.authoriseForgotPassword()

                    // Check if the password matches the correct password from the corresponding userID.
                    if (viewModel.correctForgotPasswordUserId && viewModel.correctForgotPasswordPhoneNumber && viewModel.correctConfirmForgotPassNewPassword) {
                        viewModel.setCurrentUserForgotPassword()
                        Toast.makeText(context, "Successfully Changed the Password", Toast.LENGTH_LONG).show()
                        if (viewModel.filledQuestionnaire()) {
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        } else{
                            Toast.makeText(context, "Please Complete The Questionnaire", Toast.LENGTH_LONG).show()
                            context.startActivity(Intent(context, QuestionnaireActivity::class.java))
                        }
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Change Password")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { navController.navigate(LoginActivityScreen.Register.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Register")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate(LoginActivityScreen.Login.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252) // Purple color
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ){
                Text("Login")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row() {
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
                Spacer(modifier = Modifier.width(70.dp))
                HorizontalDivider(modifier = Modifier.width(150.dp), color = Color.Gray)
            }
        }
    }
}