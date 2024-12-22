/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.agreetools.digitalnightclockred.presentation


import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.agreetools.digitalnightclockred.R
import com.agreetools.myandroid.MyTime
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()

        super.onCreate(savedInstanceState)

//         val lp = this.window.attributes
////        lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF
//        lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL

        setTheme(android.R.style.Theme_DeviceDefault)

//        val dd = window.attributes.screenBrightness
//        window.attributes.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL




        setContent {

            val MyWindow = window
            val winParams = MyWindow.attributes
//            winParams.screenBrightness = LayoutParams.BRIGHTNESS_OVERRIDE_FULL
//            MyWindow.attributes = winParams

//            val lp = this.window.attributes
//            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FUL
//            WearApp("Android")
            WearApp( MyWindow  )
        }



    }
}


fun LightOn(window: android.view.Window) {
    val MyWindow = window
    val winParams = MyWindow.attributes
    winParams.screenBrightness = LayoutParams.BRIGHTNESS_OVERRIDE_FULL
    MyWindow.attributes = winParams
}

fun LightOff(window: android.view.Window) {
    val MyWindow = window
    val winParams = MyWindow.attributes
    winParams.screenBrightness = LayoutParams.BRIGHTNESS_OVERRIDE_OFF
    MyWindow.attributes = winParams
}

@Composable
fun WearApp( window: android.view.Window)   {




    var light by remember { mutableStateOf(false) } // Initialize with false


    LaunchedEffect(light) {
        if (light) {
            LightOn(window)
        }else{
            LightOff(window)
        }
    }


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


        Column {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .align(Alignment.CenterHorizontally),

                onClick = { light = !light }

            ) {
                Text( text = "${if (light) "On" else "Off"}")

            }
            Clock(text)

        }
    }

}





@Composable
private fun Clock(textTime: String) {


    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp
    var orientation = configuration.orientation



    var limitBoth = .39F
    var limitHorizontal = limitBoth
    var limitVertical = limitBoth


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
    Column {
//        Button(
//            modifier = Modifier
//                .fillMaxWidth(0.2f)
//                .align(Alignment.CenterHorizontally),
//
//            onClick = { }
//
//        ) {
//            Text( text = "+")
//
//        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = color,
            text = textTime,
            maxLines = 1,
            fontSize = fontSize,
            onTextLayout = {

                fontSize = screenWidth.sp * limit

            }
        )
//        Button(
//            modifier = Modifier
//                .fillMaxWidth(0.2f)
//                .align(Alignment.CenterHorizontally),
//
//            onClick = {
//
//
//            }
//
//        ) {
//            Text( text = "-")
//
//        }
    }
}

//@Composable
//fun WearApp(greetingName: String) {
//    DigitalNightClockRedTheme {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MaterialTheme.colors.background),
//            contentAlignment = Alignment.Center
//        ) {
//            TimeText()
//            Greeting(greetingName = greetingName)
//        }
//    }
//}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

//@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
//@Composable
//fun DefaultPreview() {
//    WearApp(        )
//}