# 🍋 Little Lemon Android App

Aplicación móvil nativa para Android del restaurante **Little Lemon**, desarrollada con **Kotlin** y **Jetpack Compose** como parte del proyecto final (Capstone) del Certificado Profesional de Meta Android Developer en Coursera.

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.7.20-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.3.2-brightgreen.svg)](https://developer.android.com/jetpack/compose)
[![Figma Design](https://img.shields.io/badge/Figma-Design%20%26%20Wireframe-F24E1E.svg)](https://www.figma.com/design/NzhheFEPmqrplPOf0kEfcD)

---

## 📱 Pantalla de Onboarding (Implementada)

La pantalla de registro inicial permite recopilar los datos del nuevo cliente antes de comenzar a pedir a domicilio:

* **Cabecera**: Logotipo oficial de Little Lemon centrado.
* **Banner de bienvenida**: Superficie en verde corporativo (`#495E57`) con el lema *"Let's get to know you"*.
* **Formulario de información personal**:
  * Nombre de pila (*First name*)
  * Apellidos (*Last name*)
  * Dirección de correo electrónico (*Email*)
  * Gestión reactiva del estado mediante delegados `remember { mutableStateOf("") }`.
* **Botón de registro**: Botón con estilo corporativo en amarillo Little Lemon (`#F4CE14`) con borde (`#C6A600`) y esquinas redondeadas.

---

## 🎨 Guía de Estilo Oficial (Brand Guidelines)

### Colores
* **Primarios**:
  * `PrimaryGreen` (`#495E57`)
  * `PrimaryYellow` (`#F4CE14`)
* **Secundarios**:
  * `SecondarySalmon` (`#EE9972`)
  * `SecondaryPeach` (`#FBDABB`)
  * `SecondaryLightGray` (`#EDEFEE`)
  * `SecondaryCharcoal` (`#333333`)
  * `ButtonBorder` (`#C6A600`)

### Tipografía
* **Markazi Text**: Display Title y Subtítulo de marca.
* **Karla**: Textos de guía, títulos de sección, campos de entrada y botones.

---

## 📂 Estructura del Proyecto

```
LittleLemon/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/littlelemon/
│   │   │   ├── MainActivity.kt        # Punto de entrada de la aplicación
│   │   │   ├── Onboarding.kt          # Composable de la pantalla de Onboarding y Preview
│   │   │   ├── Database.kt            # Base de datos local Room
│   │   │   ├── Network.kt             # Cliente de red Ktor para el menú
│   │   │   └── ui/theme/
│   │   │       ├── Color.kt           # Definición de colores oficiales Little Lemon
│   │   │       ├── Shape.kt           # Formas y bordes redondeados
│   │   │       ├── Theme.kt           # LittleLemonTheme
│   │   │       └── Type.kt            # Tipografías oficiales
│   │   └── res/
│   │       └── drawable/              # Recursos gráficos (logo, fotos de platos)
├── build.gradle
└── settings.gradle
```

---

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/JavierRodrBrv/little-lemon-android.git
   ```
2. Ábrelo en **Android Studio** (Hedgehog, Iguana o superior).
3. Deja que Gradle sincronice las dependencias del proyecto.
4. Selecciona un emulador Android o dispositivo físico (API 24+) y presiona **Run (Shift + F10)**.

---

## 🔗 Recursos de Diseño en Figma

* 🎨 **[Diseño de Alta Fidelidad en Figma](https://www.figma.com/design/NzhheFEPmqrplPOf0kEfcD)**
* 📐 **[Wireframe de la Pantalla de Inicio en Figma](https://www.figma.com/design/NWs23SnRAd51lF20lmLo8s)**
