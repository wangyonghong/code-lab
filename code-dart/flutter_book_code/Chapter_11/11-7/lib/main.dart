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
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title), actions: <Widget>[
        new IconButton(
          icon: new Icon(Icons.favorite),
          onPressed: () {
            debugPrint("favorite icon pressed");
          },
          color: Colors.green,
        ),
        new IconButton(
          icon: new Icon(Icons.delete),
          color: Colors.red,
          onPressed: () {
            debugPrint("delete icon pressed");
          },
        ),
      ]),
      body: Center(
        child: Column(
          children: <Widget>[],
        ),
      ),
    );
  }
}
