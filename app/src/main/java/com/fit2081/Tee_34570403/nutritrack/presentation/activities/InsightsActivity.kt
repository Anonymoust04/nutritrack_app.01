package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderDefaults.Thumb
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.fit2081.Tee_34570403.nutritrack.R
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.InsightsViewModel

class InsightsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: InsightsViewModel = ViewModelProvider(
                this, InsightsViewModel.InsightsViewModelFactory(this@InsightsActivity)
            )[InsightsViewModel::class.java]

            Scaffold(

                containerColor = Color.White,

                // Bottom Bar Section of the Screen
                bottomBar = { BottomBarContentInsight(this@InsightsActivity) }
            ){ innerPadding ->
                InsightsScreenPage(
                    this@InsightsActivity,
                    viewModel, Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()))
            }
        }
    }
}

@Composable
fun BottomBarContentInsight(context :Context){
    BottomAppBar(
        modifier = Modifier
            .height(80.dp)
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

                IconButton(onClick = { }, modifier = Modifier
                    .width(60.dp)
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
                    .width(80.dp)
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
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Nutricoach", fontSize = 10.sp)
                            }
                        }
                    }
                )

                IconButton(onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) }, modifier = Modifier
                    .width(60.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreenPage(
    context: Context,
    viewModel: InsightsViewModel,  // ViewModel
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        // Page title
        Text("Insights: Food Score", fontWeight = FontWeight.Bold, fontSize = 25.sp)

        Spacer(modifier = Modifier.height(30.dp))

        // Scoreboard for All Food Groups or Nutrients Section
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ){
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
            ) {
                viewModel.getFoodCategoryMap().keys.forEach { category ->
                    Text(category, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 10.dp))
                }
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
            ) {

                viewModel.getFoodCategoryMap().forEach { (category, value) ->
                    var maxVal = viewModel.checkCategoryForMaxVal(category).toFloat()

                    Slider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        value = value.toFloat(),
                        onValueChange = {},
                        valueRange = 0f..maxVal,
                        steps = 3,
                        thumb = {
                            Thumb(
                                interactionSource = remember { MutableInteractionSource() },
                                modifier = Modifier.border(
                                    1.dp,
                                    color = Color(112, 20, 252),
                                    shape = RoundedCornerShape(50)
                                ),
                                colors = SliderDefaults.colors(Color.White),
                            )
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = Color.White,
                            activeTrackColor = Color(112, 20, 252),
                            activeTickColor = Color(255, 255, 255),
                            inactiveTickColor = Color(0,0,0)
                        )
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                .fillMaxWidth(1f)
            ) {
                var outputText = ""
                viewModel.getFoodCategoryMap().forEach { (category, value) ->
                    val maxVal = viewModel.checkCategoryForMaxVal(category)
                    outputText = "${value.toFloat()}/${maxVal}"
                    Text(text = outputText, modifier = Modifier.padding(vertical = 10.dp, horizontal = 3.dp), fontWeight =  FontWeight.SemiBold)
                }
            }

        }

        Spacer(modifier = Modifier.height(25.dp))

        // Total Food Quality Score Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
        ){
            Text("Total Food Quality Score", color = Color.Gray, fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Slider(
                    modifier = Modifier
                        .fillMaxWidth(0.80f)
                        .height(36.dp),
                    value = viewModel.totalScore.toFloat(),
                    onValueChange = { },
                    valueRange = 0f..100f,
                    steps = 3,
                    thumb = {
                        Thumb(
                            interactionSource = remember { MutableInteractionSource() },
                            modifier = Modifier.border(
                                1.dp,
                                color = Color(112, 20, 252),
                                shape = RoundedCornerShape(50)
                            ),
                            colors = SliderDefaults.colors(Color.White),
                            enabled = true,
                        )
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color(112, 20, 252),
                        activeTickColor = Color(255, 255, 255),
                        inactiveTickColor = Color(0,0,0)
                    )
                )

                Text(text = "${viewModel.totalScore.toFloat()}/100", modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(), fontWeight = FontWeight.SemiBold)

            }

        }

        Spacer(modifier = Modifier.height(5.dp))

        // Share Button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    // Create an intent to share the text
                    val shareIntent = Intent(ACTION_SEND)
                    // Set the type of data to share
                    shareIntent.type = "text/plain"
                    // Set the data to share, in this case, the text
                    shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hello, I have got a total HEIFA Score of ${viewModel.totalScore.toFloat()}!"
                    )
                    // Start the activity to share the text, with a chooser to select the app
                    context.startActivity(Intent.createChooser(shareIntent, "Share total food score via"))
                },
                modifier = Modifier
                    .width(220.dp)
                    .height(40.dp),
                colors = ButtonColors(
                    Color(112, 20, 252),
                    contentColor = Color.White,
                    disabledContainerColor = Color(240, 240, 201),
                    disabledContentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ) {
                Icon(painter = painterResource(R.drawable.share), contentDescription = "share")
                Spacer(modifier = Modifier.width(15.dp))
                Text("Share with someone")
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Diet Improvement Button
            Button(
                onClick = { context.startActivity(Intent(context, NutricoachActivity::class.java)) },
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp),
                colors = ButtonColors(
                    Color(112, 20, 252),
                    contentColor = Color.White,
                    disabledContainerColor = Color(240, 240, 201),
                    disabledContentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            ) {
                Icon(painter = painterResource(R.drawable.rocket), contentDescription = "rocket")
                Spacer(modifier = Modifier.width(15.dp))
                Text("Improve My Diet!")
            }
        }
    }
}