import 'package:flutter/material.dart';
import 'dart:io';
import 'package:flutter_sound/flutter_sound.dart';
import 'package:path_provider/path_provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Audio Record & Play",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "Audio Record & Play"),
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
  FlutterSound flutterSound = new FlutterSound();
  String path;

  Future<File> _getLocalFile() async {
    String dir = (await getApplicationDocumentsDirectory()).path;
    return new File('$dir/counter.dat');
  }

  @override
  void initState() {
    super.initState();
    _getLocalFile().then((onValue) {
      path = onValue.path;
      debugPrint("path: $path");
    });
  }

  startRec() {
    debugPrint("Start record");
    if (!flutterSound.isRecording) {
      stopPlay();
      flutterSound.startRecorder(null);
    } else {
      stopRec();
    }
  }

  stopRec() {
    debugPrint("Stop record");
    if (flutterSound.isRecording) {
      flutterSound.stopRecorder();
    }
  }

  startPlay() {
    debugPrint("Start play");
    if (!flutterSound.isPlaying) {
      stopRec();
      flutterSound.startPlayer(null);
    } else {
      stopPlay();
    }
  }

  stopPlay() {
    debugPrint("Stop play");
    if (flutterSound.isPlaying) {
      flutterSound.stopPlayer();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            IconButton(
                icon: Icon(Icons.fiber_manual_record, color: Colors.redAccent),
                onPressed: startRec),
            IconButton(
                icon: Icon(Icons.play_arrow, color: Colors.redAccent),
                onPressed: startPlay),
          ],
        ),
      ),
    );
  }
}
