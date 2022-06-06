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
  var currentIndex = -1;
  List<int> mList;

  @override
  void initState() {
    super.initState();
    mList = new List();
    for (int i = 0; i < 3; i++) {
      mList.add(i);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: SingleChildScrollView(
        child: Container(
          child: ExpansionPanelList(
            expansionCallback: (index, isExpanded) {
              setState(() {
                currentIndex = (currentIndex != index) ? index : -1;
              });
            },
            children: mList.map((i) {
              return new ExpansionPanel(
                headerBuilder: (context, isExpanded) {
                  return new ListTile(
                    title: new Text("第${i + 1}个元素标题"),
                  );
                },
                body: new Padding(
                  padding: EdgeInsets.all(10.0),
                  child: ListBody(
                    children: <Widget>[
                      new Text("第${i + 1}个元素内容"),
                    ],
                  ),
                ),
                isExpanded: currentIndex == i,
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
}
