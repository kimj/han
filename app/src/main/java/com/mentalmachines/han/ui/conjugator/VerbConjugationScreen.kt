package com.mentalmachines.han.ui.conjugator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun VerbConjugationScreen(
    viewModel: VerbConjugationViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    onAction: (actions: VerbConjugationScreenActions) -> Unit
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


@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    onAction: (actions: DetailScreenActions) -> Unit
) {
    Text(text = "Verb Conjugation")
    /*Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 56.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" }
    ) {
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
