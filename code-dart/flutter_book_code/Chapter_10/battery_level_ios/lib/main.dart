import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';

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

  String batteryLevel = "点击右下角按钮获取当前电量";

  static const platform = const MethodChannel("methodchannel.example.com/batterylevel");

  Future<Null> getBatteryLevel() async{
    try{
      int result = await platform.invokeMethod("getBatteryLevel");
      batteryLevel = "当前电量：$result";
    }catch(e){
      debugPrint(e.toString());
      batteryLevel = "无法获取当前电量信息";
    }
    setState(() {

    });
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
          children: <Widget>[
            Text(batteryLevel)
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: getBatteryLevel,
        tooltip: "获取电量",
        child: Icon(Icons.battery_unknown),
      ),
    );
  }
}