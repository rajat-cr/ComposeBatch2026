package com.coderroots.kotlinandroidclass6m2026compose

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

class ColumnActivity:  ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColumnScreen()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ColumnScreen(){
    var etEmail by remember { mutableStateOf("") }
    var etPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Welcome Back!",
            fontSize = 35.sp,
            color = colorResource(R.color.purple_200)
        )
        Spacer(Modifier.height(10.dp))

        Image(painter = painterResource(R.drawable.profile),
            contentDescription = "",
            )
        Spacer(Modifier.height(10.dp))
        TextField(
            value = etEmail,
            onValueChange = {etEmail = it},
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            singleLine = true,
            placeholder = {
                Text("Enter Email")
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.purple_200)

            )
        )
        Spacer(Modifier.height(10.dp))

        TextField(
            value = etPassword,
            onValueChange = {etPassword = it},
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            singleLine = true,
            placeholder = {
                Text("Enter Password")
            },


            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.purple_200)
            )
        )
        Spacer(Modifier.height(10.dp))
        Text("Forget Password ?",
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().padding(end = 10.dp))
        Spacer(Modifier.height(20.dp))
        ElevatedButton(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_200)
            )
        ) {
            Text("Login")
        }
    }

}