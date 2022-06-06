import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "网格列表组件",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("网格列表组件")),
            body: new GridView(
                gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
                    maxCrossAxisExtent: 100.0),children: <Widget>[
              new Icon(Icons.add),
              new Icon(Icons.arrow_upward),
              new Icon(Icons.arrow_forward),
              new Icon(Icons.arrow_downward),
              new Icon(Icons.arrow_back),
              new Icon(Icons.print),
              new Icon(Icons.home),
              new Icon(Icons.android)
            ])));
  }
}
