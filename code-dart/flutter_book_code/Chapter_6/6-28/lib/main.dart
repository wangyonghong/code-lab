import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "手势识别——双指缩放",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("手势识别——双指缩放"),
            ),
            body: new GestureDetector(
              child: new Container(
                  width: double.infinity,
                  height: double.infinity,
                  color: Colors.green),
              // 缩放
              onScaleStart: (details) =>
                  debugPrint("手势：缩放开始：${details.toString()}"),
              onScaleUpdate: (details) => debugPrint("手势：缩放中：${details.scale}"),
              onScaleEnd: (details) =>
                  debugPrint("手势：缩放结束：${details.toString()}"),
            )));
  }
}
