import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "触摸事件监听",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("触摸事件监听"),
            ),
            body: new Listener(
                child: new Container(
                    width: double.infinity,
                    height: double.infinity,
                    color: Colors.green),
                onPointerDown: (event) => debugPrint("按下 $event"),
                onPointerUp: (event) => debugPrint("抬起 $event"),
                onPointerMove: (event) => debugPrint("移动 $event"))));
  }
}
