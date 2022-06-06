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
          body: new ListView(
            scrollDirection: Axis.vertical,
            reverse: false,
            padding: EdgeInsets.all(5.0),
            primary: true,
            physics: ClampingScrollPhysics(),
            children: <Widget>[
              new Container(
                color: Colors.blue,
                height: 200.0,
              ),
              new Container(
                color: Colors.green,
                height: 200.0,
              ),
              new Container(
                color: Colors.red,
                height: 200.0,
              ),
              new Container(
                color: Colors.grey,
                height: 200.0,
              )
            ],
          ),
        ));
  }
}
