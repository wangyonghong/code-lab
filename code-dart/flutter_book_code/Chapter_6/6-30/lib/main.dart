import 'package:flutter/material.dart';
import 'dart:math';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "通知组件——自定义",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("通知组件——自定义"),
            ),
            body: new GestureDetector(child: new PlusNumWidget())));
  }
}

class PlusNumWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new PlusNumWidgetState();
  }
}

class PlusNumWidgetState extends State<PlusNumWidget> {
  int numA;
  int numB;

  int result;

  @override
  Widget build(BuildContext context) {
    return NotificationListener<CustomNotification>(
        onNotification: (notification) {
          setState(() {
            numA = notification.numA;
            numB = notification.numB;
            result = numA + numB;
          });
        },
        child: Center(
            child: Column(children: <Widget>[
          new Text("$numA + $numB = $result"),
          new Builder(builder: (context) {
            return new RaisedButton(
                child: Text("计算！"),
                onPressed: () {
                  CustomNotification(
                          new Random().nextInt(100), new Random().nextInt(100))
                      .dispatch(context);
                });
          })
        ])));
  }
}

class CustomNotification extends Notification {
  int numA;
  int numB;

  CustomNotification(this.numA, this.numB);
}
