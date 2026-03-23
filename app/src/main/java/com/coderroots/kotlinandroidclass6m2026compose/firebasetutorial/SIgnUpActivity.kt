package com.coderroots.kotlinandroidclass6m2026compose.firebasetutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderroots.kotlinandroidclass6m2026compose.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class SIgnUpActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignUpScreen()
        }
    }
}

@Composable
fun SignUpScreen(){
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var uAuth = Firebase.auth
    val db = Firebase.firestore

    Box(Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text("Welcome to Coder Roots!",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.purple_200)
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Name")
                }
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Email")
                }
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Password")
                }
            )
            Spacer(Modifier.height(10.dp))

            ElevatedButton(
                onClick ={

                    if (name.isEmpty()){
                        Toast.makeText(context,"Enter Name", Toast.LENGTH_SHORT).show()
                    } else   if (email.isEmpty()){
                        Toast.makeText(context,"Enter Email", Toast.LENGTH_SHORT).show()
                    }else   if (password.isEmpty()){
                        Toast.makeText(context,"Enter Password", Toast.LENGTH_SHORT).show()
                    }else{
                        uAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                            if(it.isSuccessful){
                                var userLogin = UserLogin(id = uAuth.currentUser?.uid.toString(), name = name, email= email)
                                db.collection("UserLogin").document(uAuth.currentUser?.uid.toString()).set(userLogin)
                                    .addOnCompleteListener {
                                        if(it.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "Registered Successful",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }else{
                                            println("Check compolete listner Exception: ${it.exception?.message}")
                                        }
                                    }.addOnFailureListener {
                                        println("Check Data Saved Failure Exception: ${it.message}")
                                    }
                            }else{
                                Toast.makeText(context,it.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {
                            println("Check Auht Login Failure : ${it.message}")
                        }
                    }


                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_200)
                )
            ) {
                Text("Sign Up")
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center) {
                Text("If your already have an account ? ")
                Text("Login", fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.purple_200),
                    modifier = Modifier.clickable{

                    })
            }
        }


    }


}