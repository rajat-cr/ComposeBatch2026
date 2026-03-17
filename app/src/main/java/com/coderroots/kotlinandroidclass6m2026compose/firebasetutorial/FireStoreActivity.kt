package com.coderroots.kotlinandroidclass6m2026compose.firebasetutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.coderroots.kotlinandroidclass6m2026compose.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.firestore


class FireStoreActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FireStoreLazyScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun FireStoreLazyScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val db = Firebase.firestore
    var studentList = remember { mutableStateListOf<UserDataModel>() }
    var selectedIndex  by  remember { mutableStateOf(0) }


    db.collection("UserData").addSnapshotListener { snapshots, exception ->
        if(exception!=null){
            return@addSnapshotListener
        }

        for(doc in snapshots!!.documentChanges){
            when(doc.type){
                DocumentChange.Type.ADDED->{
                    val model = doc.document.toObject(UserDataModel::class.java)
                    model.id = doc.document.id
                    studentList.add(model)
                    println("StudentList After Snapshot: $studentList")
                }
                DocumentChange.Type.MODIFIED->{
                    val model = doc.document.toObject(UserDataModel::class.java)
                  val index =   studentList.indexOfFirst { it.id == model.id }
                    studentList[index] = model

                }
                DocumentChange.Type.REMOVED->{

                }
                
            }

        }

    }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Firestore")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple_200)
                )
                )
        }
    ) {innerPadding->

        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(studentList.size){index->
                    Card(Modifier.fillMaxWidth().padding(10.dp)) {
                        Row(Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.padding(5.dp)) {
                            Text(studentList[index].name.toString())
                            Spacer(Modifier.height(5.dp))
                            Text(studentList[index].className.toString())
                        }
                            Spacer(Modifier.weight(1f))
                            Icon(Icons.Default.Edit,
                                contentDescription = "",
                                modifier = Modifier.clickable{
                                    showDialog = true
                                    selectedIndex = index

                                })
                            Spacer(Modifier.width(5.dp))
                            Icon(Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier.clickable{

                                })

                        }
                    }
                }
            }
            FloatingActionButton(
                onClick = {
                    showDialog = true
                    selectedIndex = -1
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = ""
                )
            }

        }

        if (showDialog) {
            OpenFirebaseDialog(
                showDialog = showDialog,
                selectedIndex = selectedIndex,
                dismiss = {
                    showDialog = false
                },
                studentList = studentList

                )
        }

    }
}
@Composable
fun OpenFirebaseDialog(
    showDialog: Boolean,
    dismiss: () -> Unit,
    selectedIndex: Int,
    studentList: SnapshotStateList<UserDataModel>
) {
    var studentName by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    val context = LocalContext.current

    val db = Firebase.firestore
if(selectedIndex !=-1){
    studentName = studentList[selectedIndex].name.toString()
    studentClass = studentList[selectedIndex].className.toString()
}

    Dialog(

        onDismissRequest = {
            dismiss()
        },
        content = {
            Column(Modifier.fillMaxWidth().background(color = Color.White, shape = RoundedCornerShape(7.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(Modifier.height(20.dp))
                Text("Add Student",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                TextField(
                    value = studentName,
                    onValueChange = {studentName = it  },
                    placeholder = {
                        Text("Enter Your Name")
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                )

                Spacer(Modifier.height(10.dp))
                TextField(
                    value = studentClass,
                    onValueChange = {studentClass = it  },
                    placeholder = {
                        Text("Enter Your Roll No.")
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                )

                Spacer(Modifier.height(10.dp))
                ElevatedButton(
                    onClick = {
                        if(studentName.isEmpty()){
                            Toast.makeText(context,"Enter Name", Toast.LENGTH_SHORT).show()
                        }else if(studentClass.isEmpty()){
                            Toast.makeText(context,"Enter studentClass", Toast.LENGTH_SHORT).show()
                        }else{
                            val entity = UserDataModel(name = studentName, className = studentClass)
                            if(selectedIndex == -1) {

                                db.collection("UserData").add(entity).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Data Added Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        dismiss()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            it.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }.addOnFailureListener {
                                    println("CHeck Save Data Exception: ${it.message}")
                                }
                            }else {

                                entity.id = studentList[selectedIndex].id.toString()
                                db.collection("UserData").document(studentList[selectedIndex].id.toString()).set(entity).addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Data Updated Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        dismiss()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            it.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }.addOnFailureListener {
                                    println("CHeck Update Data Exception: ${it.message}")
                                }

                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding( start = 20.dp, end = 20.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.purple_200)
                    )
                ) {
                    Text(if(selectedIndex == - 1)
                        "ADD STUDENT"
                    else
                    "UPDATE STUDENT")
                }
            }
        }
    )
}
