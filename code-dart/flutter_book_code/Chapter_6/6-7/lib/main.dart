import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "界面布局流程体验",
      home: new Scaffold(
          appBar: new AppBar(
            title: new Text("界面布局流程体验"),
          ),
          body: new Column(
            children: <Widget>[
              new Text("我是一个文本组件"),
              new RaisedButton(
                  onPressed: () => debugPrint("button clicked!"),
                  child: new Text("我是一个按钮")),
              new Icon(Icons.android)
            ],
          )),
    );
  }
}
