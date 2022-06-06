import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "圆形头像框",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new Scaffold(
        backgroundColor: Colors.green,
        appBar: new AppBar(
          title: new Text("圆形头像框"),
        ),
        body: new Center(
            child: new CircleAvatar(
          radius: 72.0,
          backgroundColor: Colors.white,
          child: new FlutterLogo(size: 100.0),
        )),
      ),
    );
  }
}
