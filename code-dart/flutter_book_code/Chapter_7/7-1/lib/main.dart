import 'package:flutter/material.dart';
import 'page_2.dart';
import 'page_3.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "页面跳转及数据传递",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "页面跳转及数据传递"),
      routes: {"page_2": (BuildContext context) => new Page2()},
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
    setState(() {
      _counter++;
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
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.display1,
            ),
            RaisedButton(
              child: Text("跳转至页面二"),
              onPressed: () {
                Navigator.pushNamed(context, "page_2");
              },
            ),
            RaisedButton(
              child: Text("跳转至页面三"),
              onPressed: () {
                Navigator.push<int>(context,
                    new MaterialPageRoute(builder: (BuildContext context) {
                  return new Page3(_counter);
                })).then((int backData) {
                  setState(() {
                    if (backData != null) {
                      _counter = backData;
                    }
                  });
                });
              },
            )
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
