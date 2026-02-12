package com.coderroots.kotlinandroidclass6m2026compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
                        modifier = Modifier
                            .height(80.dp)
                            .drawBehind {
                                // Define shadow height (e.g., 8.dp)
                                val shadowHeight = 8.dp.toPx()

                                // Draw a vertical gradient that fades out as it moves UP
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black.copy(alpha = 0.1f), // Shadow start (at the top edge)
                                            Color.Transparent               // Shadow end (fading out upwards)
                                        ),
                                        startY = 0f,
                                        endY = -shadowHeight
                                    )
                                )
                            },
                        containerColor = colorResource(R.color.white)


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
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        navController.navigate("home") {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
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
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        navController.navigate("setting") {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
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
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },

                                        ) {
                                        navController.navigate("profile") {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
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
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },

                                        ) {
                                        navController.navigate("call") {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)){
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape),
                    contentScale = Crop,

                    )
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    tint = Color.Blue
                )
            }
            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )

            Text(
                text = "Rajneesh Kumar",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Android Developer",
                fontSize = 18.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color.Blue, shape = RoundedCornerShape(18.dp)),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Find a job faster",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Attract more attention to your resume",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(50.dp),
                    tint = Color.White
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Resume",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Create Resume",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Blue
                )
                Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Blue,
                        modifier = Modifier
                            .requiredSize(28.dp)
                    )}
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                , elevation = CardDefaults.cardElevation(2.dp)
                , colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "UI/UX Designer",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Updated on Feb 12, 12:33",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(  horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "12",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Views",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )}
                        Column() {
                            Row() {
                                Text(
                                    text = "24 ",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "+4",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )}
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Profile Views",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )}
                        Column() {
                            Row() {
                                Text(
                                    text = "6 " ,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "+1 " ,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )}
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Invitations",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray)}}
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(onClick = {}, shape = (RoundedCornerShape(10.dp))
                            , colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                        ){
                            Text(text = "Boost in Search")
                        }
                        OutlinedButton(onClick = {}, shape = (RoundedCornerShape(10.dp))){
                            Text(text = "Vacancies")
                        }}
                }}

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color = Color.Blue, shape = CircleShape),
                ){}

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .offset(2.dp)
                        .background(color = Color.Gray, shape = CircleShape),
                ){}

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    , verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Search Country",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "Russia",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                    Box(modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .requiredSize(28.dp)
                        )}
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    , verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "Notifications",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box(modifier = Modifier
                        .size(20.dp)
                        .offset(x = 10.dp)
                        .background(color = Color.Red, shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ){
                        Text(
                            text = "1",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,)
                    }
                    Box(modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(28.dp)
                        )
                    }
                }
            }
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
@Preview(showSystemUi = true)
@Composable
fun HomeScreen() {
val studentList = remember { mutableStateListOf<String>("Abhishek","Rajneesh","Mukesh","Rajat") }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(Modifier.fillMaxSize().padding(10.dp)){
            items(studentList.size) {index->
                Card(Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(7.dp)
                    ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            studentList[index],
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp).weight(1f)
                        )
                        ElevatedButton(
                            onClick = {
                                studentList.removeAt(index)
                                println("StudentList Check: ${studentList}")
                            },
                            modifier = Modifier.padding(end = 10.dp)

                        ) {
                            Text("Delete")
                        }

                        ElevatedButton(
                            onClick = {
                                
                            },
                            modifier = Modifier.padding(end = 10.dp)

                        ) {
                            Text("Update")
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
        ) {
            Icon(Icons.Default.Add,
                contentDescription = "")
        }

        if(showDialog){
            ShowDialogBox(
                showDialog = showDialog,
                dismiss = { showDialog = false},
                studentList = studentList
            )
        }
    }

}

@Composable
fun ShowDialogBox(showDialog: Boolean, dismiss: () -> Unit, studentList: SnapshotStateList<String>) {

    var name by remember { mutableStateOf("") }
    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            dismiss()
        },
        properties = DialogProperties(
            dismissOnClickOutside = false
        ),
        content = {
            Column(Modifier.fillMaxWidth().background(color = Color.White).padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Add Student Name",
                    fontSize =25.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                TextField(
                    value = name,
                    onValueChange = { name = it},
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                )
                Spacer(Modifier.height(10.dp))
                ElevatedButton(
                    onClick = {
                        if(name.isEmpty())
                        {
                            Toast.makeText(context,"Add Name", Toast.LENGTH_SHORT).show()
                        }else{
                            if(studentList.contains(name)){
                                Toast.makeText(context,"This name already exist", Toast.LENGTH_SHORT).show()
                            }else {
                                studentList.add(name)
                                dismiss()
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(7.dp)
                ) {
                    Text("Save")
                }

            }
        }
    )
}