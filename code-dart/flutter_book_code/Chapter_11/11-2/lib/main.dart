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
        leading: Builder(
          builder: (BuildContext context) {
            return IconButton(
              icon: const Icon(Icons.menu),
              onPressed: () { 
                Scaffold.of(context).openDrawer(); 
              },
            );
          },
        ),
        actions: <Widget>[
          IconButton(icon: Icon(Icons.save), onPressed: (){
            debugPrint("save");
          },),
          IconButton(icon:Icon(Icons.share), onPressed: (){
            debugPrint("share");
          },),
          PopupMenuButton(itemBuilder: (BuildContext context) =>
            <PopupMenuItem<String>>[
              PopupMenuItem<String>(child: Text("帮助"), value: "help",),
              PopupMenuItem<String>(child: Text("关于"), value: "about",),
            ],
            onSelected: (String action){
              switch (action) {
                case "help":
                  debugPrint("help");
                  break;
                case "about":
                  debugPrint("about");
                  break;
              }
            },
          )
        ],
        title: Text(widget.title),
      ),
      drawer: Drawer(
        child: Container(color: Colors.lightBlue),
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
