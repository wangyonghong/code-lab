package me.yonghong.android.cube.compose.animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class AnimateAsStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfiniteTransition2()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun InfiniteTransition2() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        val infiniteTransition = rememberInfiniteTransition()

        val easingList = mutableListOf(
            FastOutSlowInEasing,
            LinearOutSlowInEasing,
            FastOutLinearInEasing,
            LinearEasing,
            CubicBezierEasing(0F, 0.25F, 0.75F, 1F)
        )

        Column(Modifier.padding(16.dp)) {
            Text("TransitionAnimation")
            Spacer(Modifier.height(16.dp))
            repeat(easingList.size) {
                val offsetX = infiniteTransition.animateFloat(
                    initialValue = 0.dp.value,
                    targetValue = (screenWidth - 72.dp).value,
                    animationSpec = infiniteRepeatable(
                        repeatMode = RepeatMode.Reverse,
                        animation = tween(
                            durationMillis = 2000,
                            easing = easingList[it]
                        )
                    )
                )
                Box(
                    Modifier
                        .padding(start = offsetX.value.dp) // 注意顺序，如果放在 size 后面不生效
                        .size(40.dp)
                        .background(Blue)
                )
                Divider(Modifier.padding(vertical = 5.dp))
            }
        }
    }

    // @Preview(showSystemUi = true)
    @Composable
    fun InfiniteTransition() {
        val infiniteTransition = rememberInfiniteTransition()
        val color by infiniteTransition.animateColor(
            initialValue = Red,
            targetValue = Green,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            Modifier
                .size(360.dp)
                .background(color)
        )
    }

    // @Preview(showSystemUi = true)
    @Composable
    fun TransitionAnimation() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp

        var isOn by remember { mutableStateOf(false) }
        val offSetX = animateDpAsState(
            targetValue = if (isOn) screenWidth - 72.dp else 0.dp,
            infiniteRepeatable(
                repeatMode = RepeatMode.Reverse,
                animation = tween(durationMillis = 3000)
            )
        )

        Column(Modifier.padding(16.dp)) {
            Text("TransitionAnimation")
            Spacer(Modifier.height(16.dp))
            Box(
                Modifier
                    .padding(start = offSetX.value) // 注意顺序，如果放在 size 后面不生效
                    .size(40.dp)
                    .background(Blue)
            )
        }
    }

    // @Preview
    @Composable
    fun AnimateAsStateDemo() {
        var blue by remember { mutableStateOf(true) }
        val color = animateColorAsState(if (blue) Blue else Red,
            animationSpec = spring(Spring.StiffnessVeryLow),
            finishedListener = {
                blue = !blue
            })

        Column(Modifier.padding(16.dp)) {
            Text("AnimateAsStateDemo")
            Spacer(Modifier.height(16.dp))

            Button(onClick = { blue = !blue }) {
                Text("Change Color")
            }
            Spacer(Modifier.height(16.dp))
            Box(
                Modifier
                    .size(128.dp)
                    .background(color.value)
            )
        }
    }

}