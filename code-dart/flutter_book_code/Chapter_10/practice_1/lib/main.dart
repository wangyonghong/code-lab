import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';
import 'dart:math';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int a = 0;
  int b = 0;

  String calcResult = "0";

  static const platform =
      const MethodChannel("methodchannel.practice.com/calc");

  Future<Null> calcSum() async {
    a = Random().nextInt(100);
    b = Random().nextInt(100);
    try {
      int result = await platform.invokeMethod("getCalcResult", [a, b]);
      calcResult = "$result";
    } catch (e) {
      debugPrint(e.toString());
      calcResult = "无法计算";
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[Text("$a + $b = $calcResult")],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: calcSum,
        tooltip: "两数相加",
        child: Icon(Icons.refresh),
      ),
    );
  }
}
