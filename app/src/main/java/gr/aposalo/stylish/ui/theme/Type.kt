package gr.aposalo.stylish.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import gr.aposalo.stylish.R

val fontBody = FontFamily(
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium)
//    Font(R.font.roboto_medium, weight = FontWeight.Medium),
//    Font(R.font.roboto_black, weight = FontWeight.Black),
)


// Set of Material typography styles to start with
val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )

    headlineMedium = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = fontBody,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)