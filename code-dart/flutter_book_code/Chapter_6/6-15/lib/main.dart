import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "线性列表组件",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("线性列表组件")),
            body: new ListView.builder(
              itemCount: 50,
              itemExtent: 30,
              itemBuilder: (BuildContext context, int index) {
                return new Text("当前位置：$index");
              },
            )));
  }
}
