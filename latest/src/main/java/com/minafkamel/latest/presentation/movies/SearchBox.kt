package com.minafkamel.latest.presentation.movies

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.minafkamel.latest.R
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(query: String, onQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = { onQueryChanged(it) },
        label = { Text(stringResource(R.string.search)) },
        placeholder = { Text(stringResource(R.string.search_movies)) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {}
        )
    )
}