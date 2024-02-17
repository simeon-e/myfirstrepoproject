package com.example.practice


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.remember
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedButton
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.Image
//import androidx.compose.ui.res.painterResource
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
//import androidx.compose.foundation.border
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
import com.example.practice.ui.theme.PracticeTheme
//
//
//class MainActivity: ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PracticeTheme {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    MessageCard(Message("Android", "Jetpack Compose"))
//                }
//            }
//        }
//    }
//}
//
//data class Message(val author: String, val body: String)
//
//@Composable
//fun MessageCard(msg: Message) {
//    // Add padding around our message
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(R.drawable.androidpractice),
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.secondary)
//        )
//
//
//        // Add a horizontal space between the image and the column
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column {
//            Text(text = msg.author,
//                color = MaterialTheme.colorScheme.primary,
//                style = MaterialTheme.typography.titleSmall
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
//                Text(
//                    text = msg.body,
//                    modifier = Modifier.padding(all = 4.dp),
//                    style = MaterialTheme.typography.bodyMedium
//                )
//        }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewMessageCard() {
//            Surface {
//                MessageCard(
//                    msg = Message("Lexi", "Take a look at Jetpack Compose, it's great!")
//                )
//            }
//    }


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeTheme {
                MyApp()
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable{mutableStateOf(false)}
    val extrapadding by animateDpAsState(targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extrapadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(onClick = {expanded = !expanded}) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}
@Preview(showBackground = true, widthDp = 1080/2, heightDp = 2408/2)
@Composable
fun GreetingPreview() {
    PracticeTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, ){
  var shouldShowOnboarding by rememberSaveable {mutableStateOf(true)}
    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(modifier: Modifier = Modifier,
              names: List<String> = List(1000){"$it"}) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
        items(items=names) {
            name -> Greeting(name = name)
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the basics Codelab!")
        Button(
            modifier = modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 1080/2, heightDp = 2408/2)
@Composable
fun OnboardingPreview() {
    PracticeTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview
@Composable
fun MyAppPreview() {
    PracticeTheme {

        MyApp()
    }
}