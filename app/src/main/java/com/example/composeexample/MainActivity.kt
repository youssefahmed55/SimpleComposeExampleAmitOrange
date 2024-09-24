package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExampleTheme {
                NavigationExample()
            }
        }
    }
}

@Composable
fun NavigationExample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2() }
    }
}


@Composable
fun Screen2(){
    //Empty Screen
}

@Composable
fun Screen1(navController: NavHostController) {

   val list = listOf(1,2,5,8,10)
    
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp) , horizontalAlignment = Alignment.CenterHorizontally) {
        MyButton(){navController.navigate("screen2")}
        MyTextField()
        MyTextField()
        MyTextField()
        MyTextField()
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp))  {
            items(list){
                Text(text = it.toString(), color = Color.Blue)
            }
        }
    }


}

@Composable
fun MyButton(onclick : () -> Unit) {
    val text = remember {
        mutableStateOf("Click Here")
    }

    Button(onClick = onclick , Modifier.padding(20.dp)) {

        Text(text.value, fontSize = 15.sp)
    }
}

@Composable
fun MyTextField(){
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label")
        }, shape = RoundedCornerShape(10.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun GreetingPreview() {
    ComposeExampleTheme {
        NavigationExample()
    }
}