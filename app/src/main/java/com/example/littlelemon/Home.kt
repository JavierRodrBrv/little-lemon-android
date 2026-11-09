package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val databaseMenuItems by (database?.menuItemDao()?.getAll()?.observeAsState(emptyList())
        ?: remember { mutableStateOf(emptyList()) })

    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

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

        // Sección Hero con descripción, imagen y barra de búsqueda
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
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
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

            // Paso 1: Campo de texto TextField para búsqueda
            TextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text("Enter search phrase", color = SecondaryCharcoal.copy(alpha = 0.6f)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = SecondaryCharcoal
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = SecondaryLightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = SecondaryCharcoal,
                    cursorColor = PrimaryGreen
                ),
                singleLine = true
            )
        }

        // Paso 2: Desglose del menú por categorías (Order for Delivery!)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            Text(
                text = "ORDER FOR DELIVERY!",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = SecondaryCharcoal,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Categorías fijas estándar (o agrupadas a partir de la base de datos)
            val categories = listOf("Starters", "Mains", "Desserts", "Drinks")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                for (cat in categories) {
                    val isSelected = selectedCategory.equals(cat, ignoreCase = true)
                    Button(
                        onClick = {
                            selectedCategory = if (isSelected) "" else cat
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isSelected) PrimaryGreen else SecondaryLightGray,
                            contentColor = if (isSelected) Color.White else PrimaryGreen
                        ),
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp)
                    ) {
                        Text(
                            text = cat,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Divider(
            color = SecondaryLightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )

        // Paso 1 & 2: Filtrado por texto de búsqueda y por categoría seleccionada
        var filteredMenuItems = if (searchPhrase.isNotBlank()) {
            databaseMenuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        } else {
            databaseMenuItems
        }

        if (selectedCategory.isNotBlank()) {
            filteredMenuItems = filteredMenuItems.filter {
                it.category.equals(selectedCategory, ignoreCase = true)
            }
        }

        // Despliegue de los elementos del menú filtrados
        MenuItems(menuItems = filteredMenuItems)
    }
}

// Composable MenuItems que recibe la lista de elementos filtrada
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

// Composable representando un único elemento de menú con soporte de fallback si la URL remota falla
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    val fallbackDrawable = when {
        menuItem.title.contains("Lemon", ignoreCase = true) -> R.drawable.lemon_dessert
        menuItem.title.contains("Fish", ignoreCase = true) -> R.drawable.grilled_fish
        menuItem.title.contains("Salad", ignoreCase = true) -> R.drawable.greek_salad
        menuItem.title.contains("Bruschetta", ignoreCase = true) -> R.drawable.bruschetta
        else -> null
    }

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
        ) {
            if (fallbackDrawable != null) {
                it.placeholder(fallbackDrawable)
                    .error(fallbackDrawable)
                    .fallback(fallbackDrawable)
            } else {
                it
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun HomePreview() {
    LittleLemonTheme {
        Home(navController = rememberNavController())
    }
}
