package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.ButtonBorder
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryCharcoal

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    // Retrieve user details from SharedPreferences
    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabecera con logotipo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Personal information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // First name (Read-only display)
            Text(
                text = "First name",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Last name (Read-only display)
            Text(
                text = "Last name",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Email (Read-only display)
            Text(
                text = "Email",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Botón Log out (Cerrar sesión)
            Button(
                onClick = {
                    // Limpiar SharedPreferences al cerrar sesión
                    sharedPreferences.edit().clear().apply()

                    // Navegar a Onboarding y limpiar backstack
                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = PrimaryYellow,
                    contentColor = SecondaryCharcoal
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, ButtonBorder)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryCharcoal
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun ProfilePreview() {
    LittleLemonTheme {
        Profile(navController = rememberNavController())
    }
}
