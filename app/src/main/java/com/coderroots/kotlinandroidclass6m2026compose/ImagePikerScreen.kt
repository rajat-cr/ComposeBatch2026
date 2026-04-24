package com.coderroots.kotlinandroidclass6m2026compose

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.net.http.HttpResponseCache.install
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ImagePickerActivity: ComponentActivity(){
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImagePikerScreen()
        }
    }
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showSystemUi = true)
@Composable
fun ImagePikerScreen(){
    val supabase = createSupabaseClient(
            supabaseUrl = SupabseObject.supabaseUrl,
            supabaseKey = SupabseObject.supabaseKey
          ) {
            install(Storage)
        }

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val checkpermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {
        if(it){
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Permission Not Granted", Toast.LENGTH_SHORT).show()
        }
    }

    val pickGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        imageUri = it
        CoroutineScope(Dispatchers.IO).launch {
            val fileName = "${System.currentTimeMillis()}.jpg"
            val inputSream = context.contentResolver.openInputStream(imageUri!!)
            val bytes = inputSream?.readBytes()

            val bucket = supabase.storage.from("image_gallery")
            bucket.upload(
                path = fileName,
                data = bytes!!,
            )
            val getUrl = bucket.publicUrl(fileName)
            println("Get Image Public Url: $getUrl")
        }
    }

    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        AsyncImage(
            model = imageUri,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier.size(200.dp),
            contentDescription =  ""
        )
        Spacer(Modifier.height(10.dp))
        ElevatedButton(
            onClick = {
                if(CheckPermission(context)){
                    pickGallery.launch("image/*")
                }else{
                    checkpermission.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
            }
        ) {
            Text("Open Gallery")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun CheckPermission(context: Context): Boolean{
    return ContextCompat.checkSelfPermission(context,android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
}