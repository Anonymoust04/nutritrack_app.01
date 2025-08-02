package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fit2081.Tee_34570403.nutritrack.R
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.QuestionnaireViewModel
import com.fit2081.Tee_34570403.nutritrack.ui.theme.NutritrackTheme
import java.util.Calendar


class QuestionnaireActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutritrackTheme {
                val viewModel: QuestionnaireViewModel = ViewModelProvider(
                    this, QuestionnaireViewModel.QuestionnaireViewModelFactory(this@QuestionnaireActivity)
                )[QuestionnaireViewModel::class.java]

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBarPage(this@QuestionnaireActivity, viewModel) }
                ) { innerPadding ->
                    FoodIntakeQuestionnairePage(
                        this@QuestionnaireActivity, // Pass the local context ("LoginScreen" in this case).
                        viewModel,
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarPage(context: Context, viewModel :QuestionnaireViewModel){

    CenterAlignedTopAppBar(

        title = {
            Text(
                "Food Intake Questionnaire",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier
            .border(1.dp, color = Color.LightGray)
            .padding(vertical = 10.dp),

        // Back button
        navigationIcon = {
            IconButton(onClick = {
                viewModel.removeCurrentUser()
                context.startActivity(Intent(context, LoginActivity::class.java))
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to login page"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
    )
}

// Function for Health Devotee Dialog
@Composable
fun HealthDevoteeButton(viewModel: QuestionnaireViewModel) {

    // Button that, when clicked, sets the 'showDialog'
    // state to true, which opens the dialog.
    // The dialog is hidden initially
    Button(
        onClick = { viewModel.updateDevoteeShowDialog(true) },
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.3f)
    ){
        Text(text = "Health Devotee", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.devoteeShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateDevoteeShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_1),
                    contentDescription = "Persona 1",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Health Devotee", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I’m passionate about healthy eating " +
                                "& health plays a big part in my life. I " +
                                "use social media to follow active lifestyle " +
                                "personalities or get new recipes/exercise " +
                                "ideas. I may even buy superfoods or follow " +
                                "a particular type of diet. I like to think " +
                                "I am super healthy.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }

                Button(
                    onClick = { viewModel.updateDevoteeShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}

// Function for Mindful Eater Dialog
@Composable
fun MindfulEaterButton(viewModel: QuestionnaireViewModel) {

    // Button that, when clicked, sets the 'showDialog'
    // state to true, which opens the dialog.
    // The dialog is hidden initially
    Button(
        onClick = { viewModel.updateMindfulShowDialog(true) },
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.5f)
    ){
        Text(text = "Mindful Eater", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.mindfulShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateMindfulShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_2),
                    contentDescription = "Persona 2",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Mindful Eater", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I’m health-conscious and being " +
                                "healthy and eating healthy is important to " +
                                "me. Although health means different things " +
                                "to different people, I make conscious lifestyle " +
                                "decisions about eating based on what I believe " +
                                "healthy means. I look for new recipes and " +
                                "healthy eating information on social media.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }

                Button(
                    onClick = { viewModel.updateMindfulShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}

// Function for Wellness Striver Dialog
@Composable
fun WellnessStriverButton(viewModel: QuestionnaireViewModel) {

    // Button that, when clicked, sets the 'showDialog'
    // state to true, which opens the dialog.
    // The dialog is hidden initially
    Button(
        onClick = { viewModel.updateWellnessShowDialog(true) },
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.83f)
    ){
        Text(text = "Wellness Striver", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.wellnessShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateWellnessShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_3),
                    contentDescription = "Persona 3",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Wellness Striver", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I aspire to be healthy (but struggle sometimes). " +
                                "Healthy eating is hard work! I’ve tried to improve " +
                                "my diet, but always find things that make it difficult " +
                                "to stick with the changes. Sometimes I notice recipe ideas" +
                                " or healthy eating hacks, and if it seems easy enough, " +
                                "I’ll give it a go.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }

                Button(
                    onClick = { viewModel.updateWellnessShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }

    }
}

// Function for Balance Seeker Dialog
@Composable
fun BalanceSeekerButton(viewModel: QuestionnaireViewModel) {
    // State to control the visibility of the AlertDialog.
    var showDialog by remember { mutableStateOf(false) }

    // Button that, when clicked, sets the 'showDialog'
    // state to true, which opens the dialog.
    // The dialog is hidden initially
    Button(
        onClick = {viewModel.updateBalanceShowDialog(true)},
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.3f)
    ){
        Text(text = "Balance Seeker", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.balanceShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateBalanceShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_4),
                    contentDescription = "Persona 4",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Balance Seeker", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I try and live a balanced lifestyle, " +
                                "and I think that all foods are okay in moderation. " +
                                "I shouldn’t have to feel guilty about eating a " +
                                "piece of cake now and again. I get all sorts of " +
                                "inspiration from social media like finding out " +
                                "about new restaurants, fun recipes and sometimes " +
                                "healthy eating tips.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }

                Button(
                    onClick = { viewModel.updateBalanceShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}

// Function for Health Procrastinator Dialog
@Composable
fun HealthProcrastinatorButton(viewModel: QuestionnaireViewModel) {

    Button(
        onClick = {viewModel.updateProcrastinatorShowDialog(true)},
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.55f)
    ){
        Text(text = "Health Procrastinator", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.procrastinatorShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateProcrastinatorShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_5),
                    contentDescription = "Persona 5",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Health Procrastinator", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I’m contemplating healthy eating but it’s " +
                                "not a priority for me right now. I know the " +
                                "basics about what it means to be healthy, but " +
                                "it doesn’t seem relevant to me right now. I " +
                                "have taken a few steps to be healthier but I " +
                                "am not motivated to make it a high priority " +
                                "because I have too many other things going on " +
                                "in my life.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }
                Button(
                    onClick = { viewModel.updateProcrastinatorShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}


// Function for Food Carefree Dialog
@Composable
fun FoodCarefreeButton(viewModel: QuestionnaireViewModel) {

    // Button that, when clicked, sets the 'showDialog'
    // state to true, which opens the dialog.
    // The dialog is hidden initially
    Button(
        onClick = {viewModel.updateCarefreeShowDialog(true)},
        colors = ButtonColors(
            Color(112, 20, 252),
            Color(255, 255, 255),
            Color(112, 20, 252),
            Color(255, 255, 255)),
        shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxWidth(0.9f)
    ){
        Text(text = "Food Carefree", fontSize = 12.sp)
    }

    // Check if the user want to open the dialog.
    if (viewModel.carefreeShowDialog.value) {
        Dialog(
            // Switch the visibility of the dialog to false when the user dismisses it
            onDismissRequest = { viewModel.updateCarefreeShowDialog(false) },
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.575f)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White, RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(R.drawable.persona_6),
                    contentDescription = "Persona 6",
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 10.dp)
                )

                Text("Food Carefree", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleSmall, fontSize = 30.sp)

                Column {
                    Text(
                        text = "I’m not bothered about healthy eating. " +
                                "I don’t really see the point and I don’t " +
                                "think about it. I don’t really notice healthy " +
                                "eating tips or recipes and I don’t care what I " +
                                "eat.",
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    )

                }

                Button(
                    onClick = { viewModel.updateCarefreeShowDialog(false) },
                    modifier = Modifier.width(130.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        Color(255, 255, 255),
                        Color(112, 20, 252),
                        Color(255, 255, 255)
                    ),
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Dismiss")
                }
            }
        }
    }
}

// Main Food Intake Questionnaire Page
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodIntakeQuestionnairePage(context: Context,
                                viewModel: QuestionnaireViewModel,  // ViewModel
                                modifier: Modifier = Modifier){

    Spacer(modifier = Modifier.height(70.dp))

    HorizontalDivider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    Column(
        modifier = modifier,
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // Food Category Section
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Tick all food categories you can eat",
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ){

                    Row(
                        modifier = Modifier.fillMaxWidth(0.32f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.fruits.value,
                            onCheckedChange = { viewModel.updateFruits(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Fruits", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.vegetables.value,
                            onCheckedChange = { viewModel.updateVegetables(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Vegetables", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.grains.value,
                            onCheckedChange = { viewModel.updateGrains(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Fruits", fontWeight = FontWeight.Medium
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ){

                    Row(
                        modifier = Modifier.fillMaxWidth(0.32f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.redMeat.value,
                            onCheckedChange = { viewModel.updateRedMeat(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Red Meat", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.seafood.value,
                            onCheckedChange = { viewModel.updateSeafood(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Seafood", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.poultry.value,
                            onCheckedChange = { viewModel.updatePoultry(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Poultry", fontWeight = FontWeight.Medium
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ){

                    Row(
                        modifier = Modifier.fillMaxWidth(0.32f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.fish.value,
                            onCheckedChange = { viewModel.updateFish(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Fish", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.eggs.value,
                            onCheckedChange = { viewModel.updateEggs(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Eggs", fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Left,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Checkbox(
                            modifier = Modifier.width(10.dp),
                            checked = viewModel.nutsNSeeds.value,
                            onCheckedChange = { viewModel.updateNutsNSeeds(it) })
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Nuts/Seeds", fontWeight = FontWeight.Medium
                        )
                    }
                }

            }


            // Persona Section
            Text(
                text = "Your Persona",
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "People can be broadly classified into 6 different " +
                        "types based on their eating preferences. Click on each button " +
                        "below to find out the different type, and select the type that " +
                        "best fits you!", textAlign = TextAlign.Justify, fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HealthDevoteeButton(viewModel)

                    MindfulEaterButton(viewModel)

                    WellnessStriverButton(viewModel)

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BalanceSeekerButton(viewModel)

                    HealthProcrastinatorButton(viewModel)

                    FoodCarefreeButton(viewModel)
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        HorizontalDivider(color = Color.LightGray)

        var expanded by remember { mutableStateOf(viewModel.personaExpansion) }

        val options = listOf(
            "Health Devotee",
            "Mindful Eater",
            "Wellness Striver",
            "Balance Seeker",
            "Health Procrastinator",
            "Food Carefree"
        )

        Column() {
            Text(
                text = "Which persona best fits you?",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp, 10.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value },
            ) {
                OutlinedTextField(
                    value = viewModel.persona.value,
                    onValueChange = {},
                    readOnly = true,
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Dropdown icon")
                    },
                    placeholder = {Text("Select option", color = Color.LightGray)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { viewModel.updatePersonaExpansion(false) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                viewModel.updatePersona(option)
                                viewModel.updatePersonaExpansion(false)
                            },
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Timings Section
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Timings", fontWeight = FontWeight.Bold)


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "What is of day approx. do you normally eat your biggest meal?",
                            modifier = Modifier
                                .fillMaxWidth(0.65f),
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Button(
                                onClick = { } ,
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                OutlinedTextField(
                                    value = viewModel.biggestMealTime.value,
                                    onValueChange = { inputVal ->
                                        viewModel.updateBiggestMealTime(inputVal)
                                    },
                                    isError = viewModel.biggestMealTimeError,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    singleLine = true,
                                    textStyle =  TextStyle(lineHeight = 50.sp),
                                    leadingIcon = {
                                        IconButton(
                                            onClick = { viewModel.updateBiggestMealTimeExpose(true) },//biggestMealTimePickerDialog.show()},
                                            content = {
                                                Icon(
                                                    painter = painterResource(R.drawable.clock),
                                                    contentDescription = "Clock Icon",
                                                    tint = Color.Black,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        )
                                    },
                                    placeholder = { Text("00:00", color = Color.LightGray, fontWeight = FontWeight.SemiBold) },
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Gray,
                                        errorBorderColor = Color.Red, unfocusedBorderColor = Color.Gray,
                                        errorPlaceholderColor = Color.Red,
                                        errorLeadingIconColor = Color.Red,),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(0.dp),
                                    shape = RoundedCornerShape(10.dp),
                                )
                            }
                        }
                    }

                    if (viewModel.biggestMealTimeDialogExpose.value){
                        val time = if (viewModel.biggestMealTime.value.isEmpty()) listOf("00", "00") else viewModel.biggestMealTime.value.trim().split(':')
                        val biggestMealDialog = TimePickerDialog(
                            context,
                            { _, selectedHour, selectedMinute ->
                                viewModel.updateBiggestMealTimeExpose(false)
                                viewModel.updateBiggestMealTime(String.format("%02d:%02d", selectedHour, selectedMinute))
                            },
                            time[0].toInt(),
                            time[1].toInt(),
                            false
                        )

                        biggestMealDialog.setOnDismissListener{ viewModel.updateBiggestMealTimeExpose(false) }
                        biggestMealDialog.setOnCancelListener{ viewModel.updateBiggestMealTimeExpose(false) }
                        biggestMealDialog.show()
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "What is of day approx. do you go to sleep at night?",
                            modifier = Modifier
                                .fillMaxWidth(0.65f),
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                OutlinedTextField(
                                    value = viewModel.sleepTime.value,
                                    onValueChange = { inputVal ->
                                        viewModel.updateSleepTime(inputVal)
                                    },
                                    isError = viewModel.sleepTimeError,
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    textStyle =  TextStyle(lineHeight = 50.sp),
                                    leadingIcon = {
                                        IconButton(
                                            onClick = { viewModel.updateSleepTimeExpose(true) },
                                            content = {
                                                Icon(
                                                    painter = painterResource(R.drawable.clock),
                                                    contentDescription = "Clock Icon",
                                                    tint = Color.Black,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        )
                                    },
                                    placeholder = { Text("00:00", color = Color.LightGray, fontWeight = FontWeight.SemiBold) },
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Gray,
                                        errorBorderColor = Color.Red, unfocusedBorderColor = Color.Gray,
                                        errorPlaceholderColor = Color.Red,
                                        errorLeadingIconColor = Color.Red,),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(0.dp),
                                    shape = RoundedCornerShape(10.dp),
                                )
                            }
                        }
                    }

                    if (viewModel.sleepTimeDialogExpose.value) {
                        val time = if (viewModel.sleepTime.value.isEmpty()) listOf("00", "00") else viewModel.sleepTime.value.trim().split(':')
                        val sleepDialog = TimePickerDialog(
                            context,
                            { _, selectedHour, selectedMinute ->
                                viewModel.updateSleepTimeExpose(false)
                                viewModel.updateSleepTime(String.format("%02d:%02d", selectedHour, selectedMinute))
                            },
                            time[0].toInt(),
                            time[1].toInt(),
                            false
                        )

                        sleepDialog.setOnDismissListener{viewModel.updateSleepTimeExpose(false)}
                        sleepDialog.setOnCancelListener{viewModel.updateSleepTimeExpose(false)}
                        sleepDialog.show()
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "What is of day approx. do you wake up in the morning?",
                            modifier = Modifier
                                .fillMaxWidth(0.65f),
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                OutlinedTextField(
                                    value = viewModel.wakeUpTime.value,
                                    onValueChange = { inputVal ->
                                        viewModel.updateWakeUpTime(inputVal)
                                    },
                                    singleLine = true,
                                    isError = viewModel.wakeUpTimeError,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    textStyle =  TextStyle(lineHeight = 50.sp),
                                    leadingIcon = {
                                        IconButton(
                                            onClick = { viewModel.updateWakeUpTimeExpose(true) },
                                            content = {
                                                Icon(
                                                    painter = painterResource(R.drawable.clock),
                                                    contentDescription = "Clock Icon",
                                                    tint = Color.Black,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        )
                                    },
                                    placeholder = { Text("00:00", color = Color.LightGray, fontWeight = FontWeight.SemiBold) },
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Gray,
                                        errorBorderColor = Color.Red, unfocusedBorderColor = Color.Gray,
                                        errorPlaceholderColor = Color.Red,
                                        errorLeadingIconColor = Color.Red,),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(0.dp),
                                    shape = RoundedCornerShape(10.dp),
                                )
                            }
                        }
                    }

                    if (viewModel.wakeTimeDialogExpose.value) {
                        val time = if (viewModel.wakeUpTime.value.isEmpty()) listOf("00", "00") else viewModel.wakeUpTime.value.trim().split(':')
                        val wakeUpDialog = TimePickerDialog(
                            context,
                            { _, selectedHour, selectedMinute ->
                                viewModel.updateWakeUpTimeExpose(false)
                                viewModel.updateWakeUpTime(String.format("%02d:%02d", selectedHour, selectedMinute))
                            },
                            time[0].toInt(),
                            time[1].toInt(),
                            false
                        )

                        wakeUpDialog.setOnDismissListener{viewModel.updateWakeUpTimeExpose(false)}
                        wakeUpDialog.setOnCancelListener{viewModel.updateWakeUpTimeExpose(false)}
                        wakeUpDialog.show()
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                }

                if (viewModel.biggestMealTimeError || viewModel.wakeUpTimeError || viewModel.wakeUpTimeError) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(Icons.Default.Info, "Error", tint = Color.Red)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Invalid Time Format", color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

            }

            HorizontalDivider(color = Color.LightGray)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                        viewModel.updateTimeFormatErr()

                        if (viewModel.biggestMealTimeError || viewModel.wakeUpTimeError || viewModel.wakeUpTimeError) {
                            Toast.makeText(context, "Please Fill All Approximation Meal Timing Details Correctly", Toast.LENGTH_LONG).show()
                        } else{
                            viewModel.saveFoodIntakeData()
                            Toast.makeText(context, "Successfully Saved The Food Intake Questionnaire Details", Toast.LENGTH_LONG).show()
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        }
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp),
                    colors = ButtonColors(
                        Color(112, 20, 252),
                        contentColor = Color.White,
                        disabledContainerColor = Color(240, 240, 201),
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Save Icon",
                        modifier = Modifier.size(25.dp, 25.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text("Save")
                }
            }
        }
    }
}
