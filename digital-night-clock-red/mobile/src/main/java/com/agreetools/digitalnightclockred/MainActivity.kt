package com.agreetools.digitalnightclockred

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.wear.compose.material.Text
import kotlinx.coroutines.delay

import com.agreetools.myandroid.MyTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {
            HideSystemBars()
            MobileApp()
        }



//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

    }
}


@Composable
fun HideSystemBars() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val window = context.findActivity()?.window ?: return@DisposableEffect onDispose {}
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        onDispose {
            insetsController.apply {
                show(WindowInsetsCompat.Type.statusBars())
                show(WindowInsetsCompat.Type.navigationBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}


@Composable
fun MobileApp() {


    val myTime = MyTime()
    val currentTime = myTime.getCurrentTime()

    var text by remember { mutableStateOf(currentTime) }

    // Update the time every second
    LaunchedEffect(key1 = Unit) {
        while (true) {
            text = currentTime
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .fillMaxHeight(),
        contentAlignment = Alignment.CenterStart,


        ) {


        Clock(text)
    }

}

//fun getCurrentTime(): String {
//    val currentTime = LocalTime.now()
//    val formatter = DateTimeFormatter.ofPattern("HH:mm") // Customize the format
//    return currentTime.format(formatter)
//}

@Composable
private fun Clock(text: String) {


    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp
    var orientation = configuration.orientation
    var limitHorizontal = .4F
    var limitVertical = .377F

    var limit =  when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            limitVertical
        }
        else -> {
            limitHorizontal
        }
    }

    var color = Color.Red
    var fontSize by remember {
        mutableStateOf(8.sp)
    }
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = color,
        text = text,
        maxLines = 1,
        fontSize = fontSize,
        onTextLayout = {

                fontSize = screenWidth.sp * limit

        }
    )
}


@Preview(
    device = "spec:parent=pixel_6_pro,orientation=portrait",
    showSystemUi = false,
    showBackground = true
)
@Composable
fun AppMobilePreview() {
    MobileApp()
}




