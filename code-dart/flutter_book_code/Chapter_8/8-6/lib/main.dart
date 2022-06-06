import 'package:flutter/material.dart';
import 'package:proximity_plugin/proximity_plugin.dart';
import 'dart:async';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _proximity ;
  List<StreamSubscription<dynamic>> _streamSubscriptions =
  <StreamSubscription<dynamic>>[];
  @override
  initState() {
    super.initState();
    initPlatformState();
  }

  initPlatformState() async {
    _streamSubscriptions
        .add(proximityEvents.listen((ProximityEvent event) {
      setState(() {
        _proximity= event.x;
      });
    }));
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text("Proximity"),
        ),
        body: new Center(
          child: new Text("接近侦测值： $_proximity"),
        ),
      ),
    );
  }
}