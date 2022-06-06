import 'package:flutter/material.dart';

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

  void showSimpleDialog() {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return SimpleDialog(
            title: Text("一个SimpleDialog示例"),
            children: <Widget>[
              SimpleDialogOption(
                child: Text("选项 A"),
                onPressed: () {
                  debugPrint("点击了选项A");
                },
              ),
              SimpleDialogOption(
                child: Text("选项 B"),
                onPressed: () {
                  debugPrint("点击了选项B");
                },
              ),
              SimpleDialogOption(
                child: Text("选项 C"),
                onPressed: () {
                  debugPrint("点击了选项C");
                },
              ),
              SimpleDialogOption(
                child: Text("选项 D"),
                onPressed: () {
                  debugPrint("点击了选项D");
                },
              )
            ],
          );
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
          children: <Widget>[],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: showSimpleDialog,
        tooltip: 'Show SimpleDialog',
        child: Icon(Icons.flip_to_front),
      ),
    );
  }
}
