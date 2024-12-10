package com.example.weatherapp.Navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(navController: NavHostController){
    TopAppBar({
        IconButton(onClick = {navController.navigate("current_weather")}, Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.app_name))
        }
    })
}