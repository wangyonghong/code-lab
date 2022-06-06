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
  var progressValue = 0.0;

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
            LinearProgressIndicator(),
            Container(
              height: 20,
            ),
            CircularProgressIndicator(),
            Container(
              height: 20,
            ),
            LinearProgressIndicator(value: progressValue),
            Container(
              height: 20,
            ),
            CircularProgressIndicator(value: progressValue),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
          onPressed: () {
            progressValue += 0.1;
            setState(() {

            });
          },
          child: Icon(Icons.add)),
    );
  }
}
