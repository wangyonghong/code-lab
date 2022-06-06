import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "弹性布局演示",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(
              title: new Text("弹性布局演示"),
            ),
            body: new Flex(
              direction: Axis.horizontal,
              children: <Widget>[
                new Expanded(
                    flex: 2,
                    child: new Container(height: 50.0, color: Colors.blue)),
                new Expanded(
                    flex: 3,
                    child: new Container(height: 50.0, color: Colors.green))
              ],
            )));
  }
}
