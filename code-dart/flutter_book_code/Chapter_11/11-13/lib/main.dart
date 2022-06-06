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

  void showAlertDialog() {
    showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text("确实要删除这个文件吗"),
            content: Text("点击删除后，文件立即删除，无法恢复！请确认。"),
            actions: <Widget>[
              FlatButton(
                child: Text("取消"),
                onPressed: () {
                  debugPrint("取消！");
                },
              ),
              FlatButton(
                textColor: Colors.red,
                child: Text("删除"),
                onPressed: () {
                  debugPrint("删除！");
                },
              ),
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
        onPressed: showAlertDialog,
        tooltip: 'Show AlertDialog',
        child: Icon(Icons.delete),
      ),
    );
  }
}
