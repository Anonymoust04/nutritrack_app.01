package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.fit2081.Tee_34570403.nutritrack.presentation.UiState
import com.fit2081.Tee_34570403.nutritrack.presentation.activities.ui.theme.NutritrackTheme
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.ClinicianViewModel
import kotlin.math.roundToInt
import kotlin.math.roundToLong

sealed class ClinicianActivityScreen(val route: String) {
    object Login : ClinicianActivityScreen("login_screen")
    object Dashboard : ClinicianActivityScreen("dashboard_screen")
}

// Clinician Activity
class ClinicianActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: ClinicianViewModel = ViewModelProvider(
                this, ClinicianViewModel.ClinicianViewModelFactory(this@ClinicianActivity)
            )[ClinicianViewModel::class.java]

            // Create a navigation controller to handle navigation between different screens
            val navController = rememberNavController()

            NutritrackTheme {
                Scaffold(
                    containerColor = Color.White,
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBarContentClinician(this@ClinicianActivity) }
                    ) { innerPadding ->
                    ClinicianNavHostSection(
                        this@ClinicianActivity, // Pass the local context ("ClinicianScreen" in this case).
                        navController,
                        viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBarContentClinician(context : Context){
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
                                Text("Nutricoach", fontSize = 10.sp)
                            }
                        }
                    }
                )

                IconButton(onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) }, modifier = Modifier
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
fun ClinicianNavHostSection(
    context: Context,
    navController: NavHostController,  // Navigation controller to handle screen transitions
    clinicianViewModel: ClinicianViewModel,  // ViewModel to access student data
    modifier: Modifier = Modifier  // Optional modifier for customizing layout
) {

    // Set up navigation host to manage different login screens
    NavHost(
        navController = navController,  // Controller that handles navigation events
        startDestination = ClinicianActivityScreen.Login.route,  // Initial screen is clinician login
    ) {
        // Define the Clinician Login screen destination
        composable(ClinicianActivityScreen.Login.route) {
            ClinicianLoginScreen(context, modifier, clinicianViewModel, navController)  // Display the clinician login interface
        }
        // Define the Clinician Dashboard screen destination
        composable(ClinicianActivityScreen.Dashboard.route) {
            ClinicianDashboardScreen(context, modifier, clinicianViewModel, navController)  // Display  the clinician dashboard
        }
    }
}

// Clinician Login Screen
@Composable
fun ClinicianLoginScreen(context: Context, modifier: Modifier = Modifier, viewModel: ClinicianViewModel, navController: NavHostController) {
    Column(
        modifier = modifier
            .padding(20.dp, 20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text("Clinician Login", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(50.dp))

        Column(){
            OutlinedTextField(
                value = viewModel.clinicianKey,
                label = {Text("Clinician Key",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )},
                isError = !viewModel.correctClinicianKey,
                placeholder =  { Text(text = "Enter your clinician key", color = Color.LightGray) },
                singleLine = true,
                onValueChange = { viewModel.updateClinicianKey(it) },
                visualTransformation = if (!viewModel.clinicKeyVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.updateClinicKeyVisible(!viewModel.clinicKeyVisibility) },
                        content = {if (!viewModel.clinicKeyVisibility)
                            Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = "Invisible")
                        else
                            Icon(imageVector = Icons.Default.Visibility, contentDescription = "Visible")}
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Check whether the corresponding phone number for the user ID is correct
        if (!viewModel.correctClinicianKey) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(Icons.Default.Info, "Clinician Key Error", tint = Color.Red)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Invalid Clinician Key", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = {
                viewModel.authoriseClinicianLogin()
                if(viewModel.correctClinicianKey){
                    Toast.makeText(context, "Correct Clinician Key", Toast.LENGTH_LONG).show()
                    navController.navigate(ClinicianActivityScreen.Dashboard.route)
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
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Login,
                contentDescription = "Clinician Login",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text("Clinician Login")
        }
    }
}

// Clinician Dashboard Screen
@SuppressLint("CommitPrefEdits")
@Composable
fun ClinicianDashboardScreen(context: Context, modifier: Modifier = Modifier, viewModel: ClinicianViewModel, navController: NavHostController) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var result by rememberSaveable { mutableStateOf("The results will show here") }
        val uiState by viewModel.uiState.collectAsState()

        // Title
        Text(
            text = "Clinician Dashboard",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // HEIFA Scores
        ScoreRow(label = "Average HEIFA (Male)", value = viewModel.avgHEIFAMaleScore)
        ScoreRow(label = "Average HEIFA (Female)", value = viewModel.avgHEIFAFemaleScore)

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // Find Data Pattern Button
        Button(
            onClick = { viewModel.sendPrompt() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(112, 20, 252)),
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(5.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Find Data Pattern")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(0.9f)
        ){
            if (uiState is UiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                var textColor = MaterialTheme.colorScheme.onSurface
                if (uiState is UiState.Error) {
                    textColor = MaterialTheme.colorScheme.error
                    result = (uiState as UiState.Error).errorMessage

                    Text(
                        text = result,
                        textAlign = TextAlign.Start,
                        color = textColor,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp, bottom = 5.dp)
                            .fillMaxHeight(0.8f)
                    )

                } else if (uiState is UiState.Success) {
                    result = (uiState as UiState.Success).outputText

                    viewModel.mutableOutputMap.forEach(){ (title, content) ->
                        InsightCard(title, content)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Done Button
        Button(
            onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(112, 20, 252)),
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .align(AbsoluteAlignment.Right),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text("Done")
        }
    }
}

// Shows the score row
@Composable
fun ScoreRow(label: String, value: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(10.dp))
            .background(Color(247, 247, 247)),
    ){
        BasicTextField(
            value = "$label :  ${((value * 10).roundToInt()).toDouble()/10}",
            onValueChange = {},
            textStyle = TextStyle( fontWeight = FontWeight.Bold, ),
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
            )
    }
    Spacer(modifier = Modifier.height(15.dp))
}

// Shows the insight content inside the insight card.
@Composable
fun InsightCard(title: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(containerColor = Color(247, 247, 247))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "$title:",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
