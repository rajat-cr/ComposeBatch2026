package com.coderroots.kotlinandroidclass6m2026compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class ScrollColumnActivity: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent{
            val scrollState = rememberScrollState()
            var selectedIndex by remember { mutableStateOf(0) }
            val navController = rememberNavController()

            val navBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStack?.destination?.route?:"home"

            selectedIndex = when(currentRoute){
                "home"->0
                "setting"->1
                "profile"->2
                "call"->3
                else-> 0
            }


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

                    BottomAppBar(
                        modifier = Modifier.height(80.dp),
                        containerColor = colorResource(R.color.white),

                        ) {
                        Row(
                            Modifier.fillMaxWidth(),
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
                                    navController.navigate("home"){
                                        popUpTo(navController.graph.startDestinationId){
                                            saveState = true
                                        }
                                        launchSingleTop =true
                                        restoreState = true
                                    }
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
                                    navController.navigate("setting"){
                                        popUpTo(navController.graph.startDestinationId){
                                            saveState = true
                                        }
                                        launchSingleTop =true
                                        restoreState = true
                                    }

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
                                    navController.navigate("profile"){
                                        popUpTo(navController.graph.startDestinationId){
                                            saveState = true
                                        }
                                        launchSingleTop =true
                                        restoreState = true
                                    }
                                }
                            )
                            Icon(
                                if(selectedIndex ==3)
                                    Icons.Filled.Call
                                else
                                    Icons.Outlined.Call,
                                contentDescription = "",
                                modifier = Modifier.size(30.dp).clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },

                                    ){
                                    navController.navigate("call"){
                                        popUpTo(navController.graph.startDestinationId){
                                            saveState = true
                                        }
                                        launchSingleTop =true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }

                }
            ) {innerPadding->
//                Column(Modifier.fillMaxSize().padding(innerPadding)
//                    .verticalScroll(scrollState)) {


//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")
//
//            Text("Hello Scafhold")
//            Text("Hello Scafhold")


                    NavHost(navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize().padding(innerPadding)){
                        composable("home") {
                            HomeScreen()
                        }
                        composable("setting") {
                            SettingScreen()
                        }
                        composable("profile") {
                            ProfileScreen()
                        }
                        composable("call") {
                           ColumnScreen(navController)
                        }
                    }
                }
         //   }


//            ScrollViewScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ScrollViewScreen(){


}

@Composable
fun CallScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Call Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Profile Screen")
    }
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Setting Screen")
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Home Screen")
    }
}