import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "堆叠布局演示",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(
              title: new Text("堆叠布局演示"),
            ),
            body: new Stack(
              alignment: AlignmentDirectional.center,
              fit: StackFit.expand,
              children: <Widget>[
                // 绿色的边框
                new Container(child: null, color: Colors.green),
                // 白色的背景
                Positioned(
                    child: new Container(child: null, color: Colors.white),
                    top: 10.0,
                    left: 10.0,
                    bottom: 10.0,
                    right: 10.0),
                // 水平和垂直都居中的图标
                Positioned(child: new Icon(Icons.home)),
                // 水平居中的文本组件
                Positioned(child: new Text("我在顶部"), top: 0.0),
                // 垂直居中的文本组件
                Positioned(child: new Text("我在左侧"), left: 0.0),
                // 距页面底部有一定距离的文本组件
                Positioned(child: new Text("我在底部"), bottom: 20.0),
                // 距页面右侧有一定距离的文本组件
                Positioned(child: new Text("我在右侧"), right: 20.0)
              ],
            )));
  }
}
