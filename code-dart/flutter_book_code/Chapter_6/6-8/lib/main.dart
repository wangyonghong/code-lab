import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "线性布局演示",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
          appBar: new AppBar(
            title: new Text("线性布局演示"),
          ),
          body: new Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[new PartRow(), new PartRow(), new PartRow(),new PartRow()],
          ),
        ));
  }
}

class PartRow extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
      new Icon(Icons.arrow_back),
      new Icon(Icons.arrow_downward),
      new Icon(Icons.arrow_upward),
      new Icon(Icons.arrow_forward),
    ]);
  }
}
