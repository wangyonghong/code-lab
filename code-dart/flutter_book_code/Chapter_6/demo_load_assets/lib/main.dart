import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
        title: "资源加载",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
          appBar: AppBar(
            title: Text("资源加载"),
          ),
          body:new FutureBuilder(
              future: DefaultAssetBundle.of(context).loadString("assets/example.json"),
              builder: (context, snapshot) {
                return new Text(snapshot.data);
              }
          ),
        )
    );
  }
}

