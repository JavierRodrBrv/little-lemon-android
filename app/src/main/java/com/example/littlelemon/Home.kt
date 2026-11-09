package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.PrimaryYellow
import com.example.littlelemon.ui.theme.SecondaryCharcoal
import com.example.littlelemon.ui.theme.SecondaryLightGray

@Composable
fun Home(navController: NavHostController, database: AppDatabase? = null) {
    val databaseMenuItems = database?.menuItemDao()?.getAll()?.observeAsState(emptyList())?.value ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Cabecera superior con logotipo y botón de acceso al Perfil
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(44.dp)) // Balance visual con el avatar

            // Logotipo Little Lemon centrado
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(40.dp)
                    .weight(1f),
                contentScale = ContentScale.Fit
            )

            // Avatar de perfil (navega al destino Perfil al hacer clic)
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate(Profile.route)
                    },
                contentScale = ContentScale.Crop
            )
        }

        // Paso 4: Sección Hero
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryGreen)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryYellow
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Somos un restaurante mediterráneo familiar, centrado en recetas tradicionales servidas con un toque moderno",
                    color = Color.White,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // Paso 5: Despliegue de los elementos del menú obtenidos de la base de datos
        MenuItems(menuItems = databaseMenuItems)
    }
}

// Paso 5: Composable MenuItems que recibe la lista de elementos y los muestra debajo de la sección hero
@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItem(menuItem = menuItem)
            Divider(color = SecondaryLightGray, thickness = 1.dp)
        }
    }
}

// Paso 5: Composable representando un único elemento de menú con título, precio, descripción e imagen remota con Glide
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = menuItem.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryCharcoal
            )
            Text(
                text = menuItem.description,
                fontSize = 13.sp,
                color = PrimaryGreen,
                lineHeight = 17.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "$${String.format("%.2f", menuItem.price)}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        GlideImage(
            model = menuItem.image,
            contentDescription = menuItem.title,
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home(navController = rememberNavController())
    }
}
