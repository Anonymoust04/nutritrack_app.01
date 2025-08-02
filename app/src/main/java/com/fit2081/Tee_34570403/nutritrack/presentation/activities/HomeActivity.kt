package com.fit2081.Tee_34570403.nutritrack.presentation.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.fit2081.Tee_34570403.nutritrack.R
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.HomeViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: HomeViewModel = ViewModelProvider(
                this, HomeViewModel.HomeViewModelFactory(this@HomeActivity)
            )[HomeViewModel::class.java]

            Scaffold(
                containerColor = Color.White,
                // Bottom Bar Section of the Screen
                bottomBar = { BottomBarContentHome(this@HomeActivity) }
            ){ innerPadding ->
                    HomeScreenPage(
                        this@HomeActivity,
                        viewModel = viewModel,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()))
            }
        }
    }
}

@Composable
fun BottomBarContentHome(context: Context){
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
                // Home Button
                IconButton(onClick = {  }, modifier = Modifier
                    .width(50.dp)
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
                                Icons.Filled.Home,
                                contentDescription = "Check icon",
                                modifier = Modifier.size(40.dp)
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

                // Insights Button
                IconButton(onClick = { context.startActivity(Intent(context, InsightsActivity::class.java)) }, modifier = Modifier
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

                // Nutricoach Button
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

                // Settings Button
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

@Composable
fun HomeScreenPage(context: Context,
                   viewModel: HomeViewModel,  // ViewModel
                   modifier: Modifier = Modifier){

    Column(
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        Text("Hello,", modifier = Modifier.padding(horizontal = 20.dp), color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Text(viewModel.patientName, modifier = Modifier.padding(horizontal = 20.dp), fontSize = 45.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("You've already filled in your Food Intake Questionnaire, " +
                    "but you can change details here:", modifier = Modifier.fillMaxWidth(0.70f).align(Alignment.CenterVertically),
                fontSize = 12.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp)

            // Edit Button
            Button(
                onClick = {
                    context.startActivity(Intent(context, QuestionnaireActivity::class.java))
                },
                shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Icon(Icons.Default.Edit, contentDescription = "edit", modifier = Modifier.size(15.dp))

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Edit", fontSize = 15.sp)

                }
            }

        }



        Spacer(modifier = Modifier.height(20.dp))

        // Balanced Meal Image
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(R.drawable.dietplate), contentDescription = "diet plate")

        }

        Spacer(modifier = Modifier.height(10.dp))

        // Food Quality Score Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("My Score", fontWeight = FontWeight.Bold,
                fontSize = 20.sp)

            IconButton(
                modifier = Modifier.width(115.dp),
                onClick = { context.startActivity(Intent(context, InsightsActivity::class.java))},
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("See all scores", color = Color.Gray, fontWeight = FontWeight.SemiBold)
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "right arrow",
                            tint = Color.Gray
                        )
                    }
                }
            )

        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(painter = painterResource(R.drawable.arrow_up),
                contentDescription = "increase",
                modifier = Modifier.background(Color.LightGray, CircleShape))

            Spacer(modifier = Modifier.fillMaxWidth(0.03f))

            Text("Your Food Quality Score", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)

            if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
                Spacer(modifier = Modifier.fillMaxWidth(0.60f))
            } else{
                Spacer(modifier = Modifier.fillMaxWidth(0.85f))
            }


            Text("${(viewModel.totalScore.toString())}/100", color = Color(0, 153, 0), fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
        }

        HorizontalDivider(Modifier.padding(20.dp))

        // Definition of Food Quality Score
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(text = "What is the Food Quality Score", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Text(text = "Your Food Quality score provides " +
                    "a snapshot of how well your eating patterns align with " +
                    "established food guidelines, helping you identify both strengths" +
                    "and opportunities for improvement in your diet\n", fontSize = 13.4.sp, lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 5.dp))

            Text(text = "This personalized measurement " +
                    "considers various food groups including " +
                    "vegetables, fruits, whole grains, and " +
                    "proteins to give you practical insights " +
                    "for making healthier food choices", fontSize = 13.4.sp, lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold)

        }

    }
}