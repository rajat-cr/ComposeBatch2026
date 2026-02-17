package com.coderroots.kotlinandroidclass6m2026compose.roomdb

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.coderroots.kotlinandroidclass6m2026compose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview(showSystemUi = true)
@Composable
fun RoomScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var context = LocalContext.current
    val studentDatabase = StudentDatabase.getInstance(context)
    val studentDao = studentDatabase?.studentDao()
    var studentList = studentDao?.getAllStudent()?.collectAsState(emptyList())?.value

    Box(Modifier.fillMaxSize()){
        LazyColumn(Modifier.fillMaxSize().padding(10.dp)) {
            items(studentList!!.size) { index->
                Card(Modifier.fillMaxWidth().padding(top = 10.dp),

                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                    ){
                    Column(Modifier.fillMaxWidth()) {
                        Text(studentList[index].studentName.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp))
                        Spacer(Modifier.height(5.dp))

                        Text(studentList[index].rollNo.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp))
                    }
                }

            }

        }
        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp)
        )
        {
            Icon(Icons.Default.Add,
                contentDescription = "",
               )
        }

    }


    if(showDialog){
        OpenShowDialog(
            showDialog = showDialog,
            dismiss = {
                showDialog = false
            },

        )
    }


}

@Preview(showSystemUi = true)
@Composable
fun OpenShowDialog(
  showDialog: Boolean, dismiss: () -> Unit
) {

    var studentName by remember { mutableStateOf("") }

    var studentRollNo by remember { mutableStateOf("") }
    val context = LocalContext.current
    val studentDatabase = StudentDatabase.getInstance(context)
    val studentDao = studentDatabase?.studentDao()
    var scope = rememberCoroutineScope()

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
                    value = studentRollNo,
                    onValueChange = {studentRollNo = it  },
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
                        }else if(studentRollNo.isEmpty()){
                            Toast.makeText(context,"Enter Roll No.", Toast.LENGTH_SHORT).show()
                        }else{
                            val entity = StudentEntity(studentName = studentName, rollNo = studentRollNo.toInt())
                            scope.launch {
                                withContext(Dispatchers.IO) {
                                    studentDao?.insertData(entity)
                                }
                                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
                                dismiss()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding( start = 20.dp, end = 20.dp, bottom = 20.dp),
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.purple_200)
                    )
                ) {
                    Text("ADD STUDENT")
                }

            }
        }
    )
}