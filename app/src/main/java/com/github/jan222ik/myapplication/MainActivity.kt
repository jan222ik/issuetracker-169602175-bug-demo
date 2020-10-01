package com.github.jan222ik.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import com.github.jan222ik.myapplication.ui.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                val showDialog = remember { mutableStateOf(false) }
                val textString = "initText"
                if (showDialog.value) {
                    DialogInputText(
                        textString = textString,
                        title = "Title",
                        onDismiss = { showDialog.value = false },
                    )
                }
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Button(onClick = { showDialog.value = true }) {
                            Text("Dialog")
                        }
                        Divider()
                        Text("Without Dialog")
                        val (text, setText) = remember { mutableStateOf(textString) }
                        TextField(value = text, onValueChange = setText)
                    }
                }
            }
        }
    }
}


@Composable
fun DialogInputText(textString: String, title: String, onDismiss: () -> Unit) {
    val (text, setText) = remember { mutableStateOf(textString) }
    AlertDialog(
        title = { Text(title) },
        buttons = {
            TextField(value = text, onValueChange = setText)
        },
        onDismissRequest = onDismiss
    )
}
