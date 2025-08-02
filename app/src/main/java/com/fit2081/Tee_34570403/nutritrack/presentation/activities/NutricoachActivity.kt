package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import coil3.compose.AsyncImage
import com.fit2081.Tee_34570403.nutritrack.R
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip
import com.fit2081.Tee_34570403.nutritrack.presentation.UiState
import com.fit2081.Tee_34570403.nutritrack.presentation.activities.ui.theme.NutritrackTheme
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.NutricoachViewModel
import kotlin.random.Random

// NutriCoach Screen Activity
class NutricoachActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: NutricoachViewModel = ViewModelProvider(
                this, NutricoachViewModel.NutricoachViewModelFactory(this@NutricoachActivity)
            )[NutricoachViewModel::class.java]

            NutritrackTheme {
                Scaffold(
                    containerColor = Color.White,
                    bottomBar = { BottomBarContentNutricoach(this@NutricoachActivity) },
                    ) { innerPadding ->
                    NutricoachScreenPage(
                        this@NutricoachActivity,
                        viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        rememberScrollState()
                    )
                }
            }
        }
    }
}

// Bottom Bar for Nutricoach
@Composable
fun BottomBarContentNutricoach(context : Context){
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
                                contentDescription = "Insights icon",
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

                IconButton(onClick = { }, modifier = Modifier
                    .width(82.dp)
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
                    colors = IconButtonColors(Color.White, Color.Gray, Color.White, Color.Gray),
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

// Dialog for Delete Confirmation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTipsDialog(viewModel: NutricoachViewModel){

    BasicAlertDialog(
        onDismissRequest = {},
    ){
        var maxSizeRatio = 0.0f
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
            maxSizeRatio = 0.3f
        } else{
            maxSizeRatio = 0.7f
        }
        Column(
            modifier = Modifier
                .fillMaxSize(maxSizeRatio)
                .padding(horizontal = 20.dp)
                .background(Color.White, RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Are you sure that you want to delete ${viewModel.countNumSelected()} tip(s) ? \n (The Action is Irreversible)", modifier = Modifier.padding(horizontal = 15.dp), fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(50.dp))

            Row() {
                Button(
                    onClick = {
                        viewModel.deleteTips()
                        viewModel.updateClearAllTasksDialog(false)
                              },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .fillMaxWidth(0.4f)
                ) {
                    Text("Yes")
                }

                Button(
                    onClick = { viewModel.updateClearAllTasksDialog(false) },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    ),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Text("No")
                }
            }
        }
    }
}


// Dialog for motivational tips
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MotivationalTipsDialog(context: Context, viewModel: NutricoachViewModel){

    val motivationalTips by viewModel.allTips.collectAsState()

    if (viewModel.showAllTipsDialog) {
        Dialog (
            onDismissRequest = {
                viewModel.updateShowAllTipsDialog(false)
                viewModel.unselectAll()
            }
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
            ){
                item{
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "AI Tips",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(start = 15.dp)
                        )

                        Button(
                            onClick = { viewModel.selectAll() },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .padding(start = 45.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text("Select All")
                        }
                    }
                }

                items(viewModel.allTips.value.count()){ tipNo ->
                    AiTipCard(tipNo, motivationalTips, viewModel)
                }


                item{
                    Row() {
                        Button(
                            onClick = {
                                if (viewModel.countNumSelected() == 0){
                                    Toast.makeText(context, "Select At Least One Tip to Delete", Toast.LENGTH_LONG).show()
                                } else {
                                    viewModel.updateClearAllTasksDialog(true)
                                }
                              },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),
                            modifier = Modifier
                                .padding(start = 30.dp)
                                .fillMaxWidth(0.5f)
                        ) {
                            Text("Delete")
                        }

                        if(viewModel.clearAllTasksDialogExposed){
                            DeleteTipsDialog(viewModel)
                        }

                        Button(
                            onClick = {
                                viewModel.updateShowAllTipsDialog(false)
                                viewModel.unselectAll()
                                      },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    112,
                                    20,
                                    252
                                )
                            ),
                            modifier = Modifier
                                .padding(start = 45.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text("Done")
                        }
                    }
                }
            }
        }
    }
}

// Card which stores each AI Tip
@Composable
fun AiTipCard(tipNo :Int, tipLst :List<NutriCoachTip>, viewModel: NutricoachViewModel) {
    val motivationTipObj = tipLst[tipNo]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 15.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Slightly off-white for distinction
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.toggleSelection(motivationTipObj.userId, motivationTipObj.motivationTip) },
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White, contentColor = Color.Black),
                modifier = Modifier.border(5.dp, Color.Black, RoundedCornerShape(5.dp)),
                content = {
                    if (tipLst[tipNo].isSelected){
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Selected")
                    }
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = tipLst[tipNo].motivationTip,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp // Improve readability
            )
        }
    }
}

// Nutricoach Screen Page
@Composable
fun NutricoachScreenPage(context: Context,
                         viewModel: NutricoachViewModel,  // NutricoachViewModel
                         modifier: Modifier = Modifier,
                         scrollState: ScrollState
){

    val placeholderResult = stringResource(R.string.results_placeholder)
    var result by rememberSaveable { mutableStateOf(placeholderResult) }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "NutriCoach",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
        )

        if(viewModel.optimalFruitIntake){
            Text("Congrats! You have an optimal fruit intake. Here is a random image generated.", fontWeight = FontWeight.SemiBold)
            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
                AsyncImage(
                    model = "https://picsum.photos/500/310?random=${Random.nextInt()}",
                    contentDescription = "Random Image",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth())
            } else{
                AsyncImage(
                    model = "https://picsum.photos/950/310?random=${Random.nextInt()}",
                    contentDescription = "Random Image",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth())
            }
        }
        else {

            // Nutritional Info
            Text("As your fruit intake is below optimal, feel free to find information of each fruit to improve your health.",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium,
            )

            // Fruit Name Input and Button
            Text(
                text = "Fruit Name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .align(AbsoluteAlignment.Left)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(35.dp)
                        .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp))
                        .padding(vertical = 7.dp, horizontal = 10.dp),
                    content = {
                        BasicTextField(
                            value = viewModel.fruitName,
                            onValueChange = { viewModel.updateFruitName(it) },
                            singleLine = true,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )

                        if (viewModel.emptyFruitName){
                            Text(
                                text = "banana",
                                fontWeight = FontWeight.Bold,
                                color = Color.LightGray
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = { viewModel.obtainFruitInfo() },
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(112, 20, 252)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Details")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Details")
                }
            }

            // Checks the existence of the fruit.
            if (viewModel.fruitExist) {

                Spacer(modifier = Modifier.height(10.dp))

                viewModel.nutritionData.forEach { (label, value) ->
                    InfoRowBar(label, value)
                }

            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(Icons.Default.Info, "Error", tint = Color.Red)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Fruit does not exist. Please search again.", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Motivational Message Section
        Button(
            onClick = { viewModel.sendPrompt() },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(112, 20, 252)),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Motivational Message (AI)")
        }

        // Checks the UI State for the AI Prompt
        if (uiState is UiState.Loading) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp))
        } else {
            var textColor = MaterialTheme.colorScheme.onSurface
            if (uiState is UiState.Error) {
                textColor = MaterialTheme.colorScheme.error
                result = (uiState as UiState.Error).errorMessage
            } else if (uiState is UiState.Success) {
                textColor = MaterialTheme.colorScheme.onSurface
                result = (uiState as UiState.Success).outputText
            }
            Column() {
                Text(
                    text = result,
                    textAlign = TextAlign.Start,
                    color = textColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp, bottom = 5.dp)
                        .fillMaxHeight(0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Button
        Button(
            onClick = { viewModel.updateShowAllTipsDialog(true) },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(112, 20, 252)),
            modifier = Modifier
                .width(150.dp)
                .padding(bottom = 16.dp)
                .align(AbsoluteAlignment.Right)
        ) {
            Text("Shows All Tips")
        }

        MotivationalTipsDialog(context, viewModel)
    }
}


// Fruit Information Row Bar
@Composable
fun InfoRowBar(label :String, value :String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 5.dp, horizontal = 10.dp),
        content = {
            Text(label, fontWeight = FontWeight.SemiBold)
            Text(": ${value}", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 150.dp))
        }
    )
    Spacer(modifier = Modifier.height(5.dp))
}
