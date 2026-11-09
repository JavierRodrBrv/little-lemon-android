package com.example.littlelemon

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.ButtonBorder
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryCharcoal

@Composable
fun Onboarding() {
    // State holders for user input
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Paso 2: Encabezado con el logotipo
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

        // Paso 4: Banner estático "Let's get to know you"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryGreen)
                .padding(vertical = 40.dp, horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }

        // Paso 4: Sección de información personal y campos de entrada
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text = "Personal information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // Campo: Nombre de pila (First name)
            Text(
                text = "First name",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    cursorColor = PrimaryGreen,
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Campo: Apellidos (Last name)
            Text(
                text = "Last name",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    cursorColor = PrimaryGreen,
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Campo: Correo electrónico (Email)
            Text(
                text = "Email",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = Color(0xFFC0C0C0),
                    cursorColor = PrimaryGreen,
                    textColor = SecondaryCharcoal
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Paso 5: Botón de registro
            Button(
                onClick = {
                    // Acción de registro / validación para ejercicios siguientes
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
                    text = "Register",
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
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding()
    }
}
