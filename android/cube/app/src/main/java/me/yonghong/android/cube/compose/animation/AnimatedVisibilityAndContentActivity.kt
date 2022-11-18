package me.yonghong.android.cube.compose.animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AnimatedVisibilityAndContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Preview
    @Composable
    fun EasyAnimation() {
        val visible = remember { mutableStateOf(true) }
        val expand = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .size(width = 360.dp, height = 360.dp)
                .padding(10.dp)
        ) {
            Button(onClick = { visible.value = !visible.value }) {
                Text(text = "可见性动画")
            }
            AnimatedVisibility(
                visible = visible.value,
                enter = fadeIn() + scaleIn() + slideInHorizontally { it },
                exit = fadeOut() + scaleOut() + slideOutVertically { it }
            ) {
                Text(text = "天青色等烟雨", modifier = Modifier.size(width = 160.dp, height = 50.dp))
            }
            if (visible.value) {
                Text(text = "而我在等你", modifier = Modifier.size(width = 160.dp, height = 50.dp))
            }
            Divider()
            Row(verticalAlignment = Alignment.CenterVertically) {
                var count by remember { mutableStateOf(0) }
                Button(onClick = { count++ }) {
                    Text(text = "Add")
                }
                AnimatedContent(targetState = count) {
                    Text(
                        text = "Count: $it",
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
            Divider()
            Text(
                text = "甭说了，干咱们这行儿的就得它妈的打一辈子光棍儿！连它妈的小家雀儿都一对一对儿的，不许咱们成家！还有一说，成家以后，一年一个孩子，我现在有五个了！全张着嘴等着吃！车份大，粮食贵，买卖苦，有什么法儿呢！不如打一辈子光棍，犯了劲上白房子，长上杨梅大疮，认命！一个人，死了就死了！",
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.animateContentSize(),
                maxLines = if (expand.value) Int.MAX_VALUE else 2
            )
            Text(
                text = if (expand.value) "收起" else "全文",
                color = Color.Blue,
                modifier = Modifier.clickable { expand.value = !expand.value }
            )
        }
    }
}