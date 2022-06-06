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
            body: new ListView.separated(
              itemCount: 50,
              separatorBuilder: (BuildContext context, int index) {
                return new Divider(color: Colors.black);
              },
              itemBuilder: (BuildContext context, int index) {
                return new Text("当前位置：$index");
              },
            )));
  }
}
