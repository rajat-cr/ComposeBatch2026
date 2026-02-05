package com.coderroots.kotlinandroidclass6m2026compose

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
class SignupActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUI()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun BasicUI(){
    Box(modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
        ) {

        Image(painter = painterResource(R.drawable.chat_bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize())



        Box(Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.TopStart)
            .background(color = Color.Black))

        Box(Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.TopEnd)
            .background(color = Color.Black))

        Box(Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.BottomStart)
            .background(color = Color.Black))

        Box(Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.BottomEnd)
            .background(color = Color.Black))
        Box(Modifier
            .height(100.dp)
            .width(100.dp)
            .align(Alignment.Center)
            .background(color = Color.Black))

        Text("Hello My First Compose App",
            fontSize = 20.sp,
            color = colorResource(R.color.purple_200)
        )
    }
}