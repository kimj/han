package com.mentalmachines.han.ui.conjugator

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun VerbConjugationScreen(
    viewModel: VerbConjugationViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    // onAction: (actions: VerbConjugationScreenActions) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        VerbConjugationScreenContent(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun VerbConjugationScreenContent(
    modifier: Modifier,
) {
    Text(text = "Verb Conjugation")
}

sealed class VerbConjugationScreenActions {
    object Back : VerbConjugationScreenActions()
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VerbConjugationWebView(
    pageUrl: String,
    onWebpageScroll: (x: Int, y: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(pageUrl)
            }
        })
}


class Verb(
    name: String,
    description: String
)


@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    onAction: (actions: DetailScreenActions) -> Unit,
    verbs: List<Verb>
) {

    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
    val verbs: List<Verb> =
        listOf(Verb("first", "decr"), Verb("second", "decr"), Verb("third", "decr"))

    Text(text = "Verb Conjugation")
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 56.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" }
    ) {
        TextField(value = inputvalue.value, onValueChange = { inputvalue.value = it })
        /*LazyColumn{
            items(verbs){ verb ->
                VerbCard(verb.name, verb.description, verb.imageRes)
            }}*/

        /*
            Image(
                painter = painterResource(id = R.drawable.img_detail),
                contentDescription = null,
                modifier = Modifier.clip(
                    RoundedCornerShape(10.dp)
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Lalakhal", style = MaterialTheme.typography.H2)
                Spacer(modifier = Modifier.weight(1F))
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "(4.8)", style = MaterialTheme.typography.captionDefault)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "To journey from place to place or to a distant place. To get around pass from one place to another the news traveled fast.",
                style = MaterialTheme.typography.body,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            MoreImages()
            Spacer(modifier = Modifier.weight(1F))
            Row(Modifier.fillMaxWidth()) {
                Column {
                    Text(text = "Total Price", style = MaterialTheme.typography.H3)
                    Spacer(modifier = Modifier.size(12.dp))
                    Row {
                        Text(text = "₹", style = MaterialTheme.typography.currency)
                        Text(text = "3435", style = MaterialTheme.typography.price)
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                Button(
                    onClick = {},
                    modifier = Modifier.padding(bottom = 56.dp).size(170.dp, 56.dp),
                    shape = RoundedCornerShape(72.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ButtonColor2
                    )
                ) {
                    Text(text = "Book Now", style = MaterialTheme.typography.buttonText)
                }
            }
        }*/
    }
}

@Composable
fun VerbCard(name: String, description: String, image: Int) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {}
}


/*@Composable
private fun MoreImages() {
    Spacer(modifier = Modifier.size(24.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(text = "More Images", style = MaterialTheme.typography.bodyBold)
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.more_1),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_2),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_3),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_4),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}*/


sealed class DetailScreenActions {
    object Back : DetailScreenActions()
}
