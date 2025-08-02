package com.fit2081.Tee_34570403.nutritrack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.fit2081.Tee_34570403.nutritrack.presentation.activities.*
import com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LoginViewModel = ViewModelProvider(
                this, LoginViewModel.LoginViewModelFactory(this@MainActivity)
            )[LoginViewModel::class.java]

            WelcomePage(this@MainActivity, viewModel)
        }
    }
}

// Landing page of NutriTrack
@Composable
fun WelcomePage(context : Context, viewModel: LoginViewModel) {

    Scaffold(


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Provides Spacing
            Spacer(modifier = Modifier.fillMaxHeight(0.3f))

            Text(text = "NutriTrack", fontSize = 48.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)

            // NutriTrack logo
            Image(
                painter = painterResource(id = R.drawable.nutritrack_logo_modified),
                contentDescription = "Nutritrack Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
            )

            // App description
            Text(text = " This app provides general health and nutrition " +
                    "information for educational purposes only. It is not " +
                    "intended as medical advice, diagnosis, or treatment. Always consult " +
                    "qualified healthcare professional before making any changes to your " +
                    "diet, exercise, or health regimen. Use this app at your own risk.If " +
                    "you’d like to an Accredited Practicing Dietitian (APD),please visit " +
                    "the Monash Nutrition/Dietetics Clinic (discounted rates for students): " +
                    "https://www.monash.edu/medicine/scs/nutrition/clinics/nutrition", fontSize = 16.sp,
                fontStyle = FontStyle.Italic, fontWeight = FontWeight.SemiBold, lineHeight = 20.sp, textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp)
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.4f))

            Button(onClick = {

                if(!viewModel.currentUserPresent()){
                    context.startActivity(Intent(context, LoginActivity::class.java))
                }
                else if (viewModel.currentUserPresent() && !viewModel.filledQuestionnaire()){
                    context.startActivity(Intent(context, QuestionnaireActivity::class.java))
                }
                else if(viewModel.currentUserPresent() && viewModel.filledQuestionnaire()) {
                    context.startActivity(Intent(context, HomeActivity::class.java))
                }
            },
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(112, 20, 252)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .padding(20.dp, 30.dp)
                ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.fillMaxHeight())

            Text("Designed with ❤ by Tee Zhi Hong (34570403)", fontWeight = FontWeight.SemiBold)
        }
    }
}