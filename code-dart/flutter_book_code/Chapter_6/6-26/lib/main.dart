import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "手势识别——滑动",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("手势识别——滑动"),
            ),
            body: new GestureDetector(
              child: new Container(
                  width: double.infinity,
                  height: double.infinity,
                  color: Colors.green),
              // 滑动
              onPanDown: (DragDownDetails dragDownDetails) =>
                  debugPrint("手势：滑动按下：${dragDownDetails.globalPosition}"),
              onPanUpdate: (DragUpdateDetails dragDownDetails) => debugPrint(
                  "手势：滑动中，当前位置：${dragDownDetails.delta.dx} - ${dragDownDetails.delta.dy}"),
              onPanEnd: (DragEndDetails dragDownDetails) => debugPrint(
                  "手势：滑动结束，瞬时速度：${dragDownDetails.velocity.pixelsPerSecond}"),
            )));
  }
}
