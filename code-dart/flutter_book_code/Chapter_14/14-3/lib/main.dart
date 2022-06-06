import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/scheduler.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
//      showSemanticsDebugger: true,
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
  int _counter = 0;

  void _incrementCounter() {
    debugPrintBeginFrameBanner = true;
    setState(() {
      _counter++;
    });
    debugPrintEndFrameBanner = true;
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
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
            RaisedButton(
                onPressed: () => debugDumpApp(),
                child: Text("Create app dump")),
            RaisedButton(
                onPressed: () => debugDumpRenderTree(),
                child: Text("Create render tree dump")),
            RaisedButton(
                onPressed: () => debugDumpLayerTree(),
                child: Text("Create layer tree dump")),
            RaisedButton(
                onPressed: () => debugDumpSemanticsTree(DebugSemanticsDumpOrder.traversalOrder),
                child: Text("Create semantics tree dump")),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),
    );
  }
}
