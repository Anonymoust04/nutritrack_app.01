package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.*
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fit2081.Tee_34570403.nutritrack.R
import com.fit2081.Tee_34570403.nutritrack.presentation.activities.ui.theme.NutritrackTheme
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.SettingsViewModel

sealed class SettingsActivityScreen(val route: String) {
    object Main :SettingsActivityScreen("main_screen")
    object EditDetails :SettingsActivityScreen("edit_details_screen")
    object ChangePassword :SettingsActivityScreen("change_password_screen")
}

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutritrackTheme {
                val viewModel: SettingsViewModel = ViewModelProvider(
                    this, SettingsViewModel.SettingsViewModelFactory(this@SettingsActivity)
                )[SettingsViewModel::class.java]

                // Create a navigation controller to handle navigation between different screens
                val navController = rememberNavController()

                Scaffold(

                    containerColor = Color.White,

                    bottomBar = { BottomBarContentSettings(this@SettingsActivity) },
                    ) { innerPadding ->
                    SettingsNavHostSection(
                        this@SettingsActivity, // Pass the local context ("LoginScreen" in this case).
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
fun SettingsNavHostSection(
    context: Context,
    navController: NavHostController,  // Navigation controller to handle screen transitions
    settingsViewModel: SettingsViewModel,  // ViewModel to access student data
    modifier: Modifier = Modifier  // Optional modifier for customizing layout
) {

    // Set up navigation host to manage different login screens
    NavHost(
        navController = navController,  // Controller that handles navigation events
        startDestination = SettingsActivityScreen.Main.route,
        modifier = modifier.verticalScroll(rememberScrollState())  // Apply any modifiers passed to this composable
    ) {
        composable(SettingsActivityScreen.Main.route) {
            SettingsScreenPage(context, navController, settingsViewModel, modifier)  // Display the staff login interface
        }
        composable(SettingsActivityScreen.EditDetails.route) {
            EditDetailsScreenPage(context, navController, settingsViewModel, modifier)  // Display student login with required ViewModel
        }
        composable(SettingsActivityScreen.ChangePassword.route) {
            ChangePasswordScreenPage(context, navController, settingsViewModel, modifier)  // Display student login with required ViewModel
        }
    }
}

@Composable
fun BottomBarContentSettings(context : Context){
    BottomAppBar(
        modifier = Modifier
            .height(85.dp)
            .border(border = BorderStroke(1.dp, Color.Gray)),
        containerColor = Color.White,
        contentPadding = PaddingValues(10.dp, 0.dp),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 1.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = { context.startActivity(Intent(context, HomeActivity::class.java))  }, modifier = Modifier
                    .width(50.dp)
                    .align(Alignment.Top)
                    .fillMaxHeight(),
                    colors = IconButtonColors(Color.White, Color.Gray, Color.White, Color.Gray),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Check icon",
                                modifier = Modifier.size(40.dp),
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text("Home", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                )

                IconButton(onClick = { context.startActivity(Intent(context, InsightsActivity::class.java)) }, modifier = Modifier
                    .width(65.dp)
                    .align(Alignment.Top)
                    .fillMaxHeight(),
                    colors = IconButtonColors(Color.White, Color.Gray, Color.White, Color.Gray),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Filled.Insights,
                                contentDescription = "Check icon",
                                modifier = Modifier.size(40.dp)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text("Insights", fontSize = 10.sp)
                            }
                        }
                    }
                )

                IconButton(onClick = { context.startActivity(Intent(context, NutricoachActivity::class.java)) }, modifier = Modifier
                    .width(82.dp)
                    .align(Alignment.Top)
                    .fillMaxHeight(),
                    colors = IconButtonColors(Color.White, Color.Gray, Color.White, Color.Gray),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.dietician),
                                contentDescription = "Check icon",
                                modifier = Modifier.size(40.dp)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
//                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Nutricoach", fontSize = 10.sp)
                            }
                        }
                    }
                )

                IconButton(onClick = { }, modifier = Modifier
                    .width(67.dp)
                    .align(Alignment.Top)
                    .fillMaxHeight(),
                    colors = IconButtonColors(Color.White, Color(112, 20, 252), Color.White, Color(112, 20, 252)),
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Check icon",
                                modifier = Modifier.size(40.dp),
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text("Settings", fontSize = 10.sp)
                            }
                        }
                    }
                )
            }

        }
    )
}

@Composable
fun SettingsScreenPage(
    context: Context,
    navController: NavHostController,
    viewModel: SettingsViewModel,  // ViewModel
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)
        )

        // Account Section
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "ACCOUNT",
                color = Color.Gray,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 15.dp)
                    .fillMaxWidth(0.3f)
            )

            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
                Spacer(modifier = Modifier.fillMaxWidth(0.10f))
            } else{
                Spacer(modifier = Modifier.fillMaxWidth(0.60f))
            }

            Button(
                onClick = {
                    navController.navigate(SettingsActivityScreen.EditDetails.route)
                },
                shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(35.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Icon(Icons.Default.Edit, contentDescription = "edit", modifier = Modifier.size(15.dp))

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Edit User Details", fontSize = 15.sp)

                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        AccDetailsItem(icon = Icons.Default.Person, label = viewModel.patientName)
        AccDetailsItem(icon = Icons.Default.Call, label = viewModel.phoneNumber)
        AccDetailsItem(icon = Icons.Default.Badge, label = "${viewModel.userID}")

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // Other Settings Section
        Text(
            text = "OTHER SETTINGS",
            color = Color.Gray,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 15.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        val logOutOnClick = {
            viewModel.removeCurrentUser()
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

        SettingRow(icon = Icons.AutoMirrored.Outlined.Logout, label = "Logout", logOutOnClick)
        SettingRow(icon = Icons.Default.Person, label = "Clinician Login", { context.startActivity(Intent(context, ClinicianActivity::class.java)) })
        SettingRow(icon = Icons.Default.Password, label = "Change Password", { navController.navigate(SettingsActivityScreen.ChangePassword.route) })
    }
}

@Composable
fun EditDetailsScreenPage(
    context: Context,
    navController: NavHostController,
    viewModel: SettingsViewModel,  // ViewModel
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Edit User Account Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Column(){
            OutlinedTextField(
                value = "${viewModel.userID}",
                label = {Text("User ID",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                placeholder =  { Text(text = "Error in Obtaining User ID", color = Color.LightGray) },
                singleLine = true,
                readOnly = true,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
            )
            Text("The User ID is assigned by a clinician so it can not be changed.", fontWeight = FontWeight.Normal)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.newPatientName,
                label = {Text("Name",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctNewPatientName,
                placeholder =  { Text(text = "Change your username", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateNewPatientName(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (!viewModel.correctNewPatientName) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, "Error", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text("The patient name must not left empty", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.newPhoneNumber,
                label = {Text("Phone Number",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctNewPhoneNumber,
                placeholder =  { Text(text = "Change your phone number", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateNewPhoneNumber(it) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (!viewModel.correctNewPhoneNumber && viewModel.newPhoneNumber.isEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, "Error", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Phone number must not left empty", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        else if (!viewModel.correctNewPhoneNumber) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, "Error", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Phone number must only contain 10-15 digits", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                viewModel.changePatientDetailsValidation()

                if(viewModel.correctNewPatientName && viewModel.correctNewPhoneNumber) {
                    viewModel.setNewPatientDetails()
                    Toast.makeText(context, "Successfully Changed Details", Toast.LENGTH_LONG).show()
                    navController.navigate(SettingsActivityScreen.Main.route)
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
            Icon(imageVector = Icons.Default.Check, contentDescription = "Confirm Changes")
            Spacer(modifier = Modifier.width(20.dp))
            Text("Confirm Changes")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(SettingsActivityScreen.Main.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // White color
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        ){
            Icon(imageVector = Icons.Default.Cancel, contentDescription = "Cancel Changes")
            Spacer(modifier = Modifier.width(40.dp))
            Text("Cancel", color = Color.Black)
        }
    }
}

@Composable
fun ChangePasswordScreenPage(
    context: Context,
    navController: NavHostController,
    viewModel: SettingsViewModel,  // ViewModel
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {navController.navigate(SettingsActivityScreen.Main.route)},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Blue),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .height(50.dp)
            ) {

                Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "Back button", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(2.dp))
                Text("Back", fontSize = 18.sp)

            }

            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))
                Text(
                    text = "Change Password",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(0.8f)
                )
            } else{
                Spacer(modifier = Modifier.fillMaxWidth(0.25f))
                Text(
                    text = "Change Password",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(0.5f)
                )
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.oldPassword,
                label = {Text("Previous Password",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctOldPassword,
                placeholder =  { Text(text = "Enter your previous password", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateOldPassword(it) },
                visualTransformation = if (!viewModel.oldPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.updateOldPasswordVisible(!viewModel.oldPassVisibility) },
                        content = {if (!viewModel.oldPassVisibility)
                            Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                        else
                            Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                    )
                }
            )
        }

        // Check password or confirm password is left blank
        if (!viewModel.correctOldPassword) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, "Error", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Incorrect Previous Password", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.newPassword,
                label = {Text("New Password",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctConfirmNewPassword,
                placeholder =  { Text(text = "Enter your new password", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateNewPassword(it) },
                visualTransformation = if (!viewModel.newPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.updateNewPasswordVisible(!viewModel.newPassVisibility) },
                        content = {if (!viewModel.newPassVisibility)
                            Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                        else
                            Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.confirmNewPassword,
                label = {Text("Confirm New Password",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctConfirmNewPassword,
                placeholder =  { Text(text = "Enter your new password again", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateConfirmNewPassword(it) },
                visualTransformation = if (!viewModel.confirmNewPassVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.updateConfirmNewPasswordVisible(!viewModel.confirmNewPassVisibility) },
                        content = {if (!viewModel.confirmNewPassVisibility)
                            Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                        else
                            Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                    )
                }
            )
        }

        // Check password or confirm password is left blank
        if (!viewModel.correctConfirmNewPassword && (viewModel.newPassword.isBlank() || viewModel.confirmNewPassword.isBlank())) {
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

        else if (!viewModel.correctConfirmNewPassword) {
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

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                viewModel.changePasswordValidation()

                if(viewModel.correctOldPassword && viewModel.correctConfirmNewPassword) {
                    viewModel.setNewPassword()
                    Toast.makeText(context, "Successfully Changed the Password", Toast.LENGTH_LONG).show()
                    navController.navigate(SettingsActivityScreen.Main.route)
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
            Icon(imageVector = Icons.Default.Check, contentDescription = "Confirm Changes")
            Spacer(modifier = Modifier.width(20.dp))
            Text("Change Password")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(SettingsActivityScreen.Main.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White, // White color
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        ){
            Icon(imageVector = Icons.Default.Cancel, contentDescription = "Cancel Changes")
            Spacer(modifier = Modifier.width(40.dp))
            Text("Cancel", color = Color.Black)
        }

    }
}

@Composable
fun AccDetailsItem(icon: ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun SettingRow(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "To the " +  label
            )
        }
    }
}
