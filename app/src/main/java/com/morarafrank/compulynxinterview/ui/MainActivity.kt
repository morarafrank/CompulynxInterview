package com.morarafrank.compulynxinterview.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.morarafrank.compulynxinterview.ui.navigation.CompulynxInterviewNavGraph
import com.morarafrank.compulynxinterview.ui.theme.ComposeTemplateTheme
import com.morarafrank.compulynxinterview.utils.CompulynxAndroidInterviewSharedPrefs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompulynxInterviewNavGraph(
                        navController = navController,
                        isSignedIn = CompulynxAndroidInterviewSharedPrefs.getIsLoggedIn() ,
                    )

                }
            }
        }
    }
}
