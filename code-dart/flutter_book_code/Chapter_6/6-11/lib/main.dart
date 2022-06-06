import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "流式布局演示",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(
              title: new Text("流式布局演示"),
            ),
            body: new Wrap(
              direction: Axis.horizontal,
              alignment: WrapAlignment.start,
              children: <Widget>[
                new Container(height: 50.0, width: 150.0, color: Colors.green),
                new Container(height: 50.0, width: 150.0, color: Colors.lightGreen),
                new Container(height: 50.0, width: 150.0, color: Colors.lime),
              ],
            )));
  }
}
