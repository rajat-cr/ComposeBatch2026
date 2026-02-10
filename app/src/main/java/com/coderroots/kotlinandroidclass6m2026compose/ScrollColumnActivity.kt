package com.coderroots.kotlinandroidclass6m2026compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ScrollColumnActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            ScrollViewScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ScrollViewScreen(){

    var scrollState = rememberScrollState()
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Dashbaord",
                        color = colorResource(R.color.white))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple_200)
                ),
            )
        },
        bottomBar = {

            Row(Modifier.fillMaxWidth().background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceAround) {
                Row(
                    Modifier.fillMaxWidth().padding(20.dp).background(color = Color.White),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(
                        if(selectedIndex ==0)
                            Icons.Filled.Home
                        else
                            Icons.Outlined.Home,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ){
                            selectedIndex = 0
                        }
                    )

                    Icon(
                        if(selectedIndex ==1)
                            Icons.Filled.Settings
                        else
                            Icons.Outlined.Settings,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                        ){
                            selectedIndex = 1
                        }
                    )

                    Icon(
                        if(selectedIndex ==2)
                            Icons.Filled.AccountCircle
                        else
                            Icons.Outlined.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },

                        ){
                            selectedIndex = 2
                        }
                    )
                }
            }

        }
    ) {innerPadding->
        Column(Modifier.fillMaxSize().padding(innerPadding)
            .verticalScroll(scrollState)) {


            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")

            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")

            Text("Hello Scafhold")
            Text("Hello Scafhold")

            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")

            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")
            Text("Hello Scafhold")

            Text("Hello Scafhold")
            Text("Hello Scafhold")




        }
    }

}