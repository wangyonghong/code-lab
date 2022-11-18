package me.yonghong.android.cube.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class FirstComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestText(name = "World")
        }
    }

    @Composable
    fun TestText(name: String) {
        Text(text = "Hello $name !")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TestText(name = "Android")
    }
}