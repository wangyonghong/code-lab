import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "自定义滚动组件",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("自定义滚动组件")),
            body: new CustomScrollView(
              slivers: <Widget>[
                SliverPadding(
                  padding: const EdgeInsets.all(8.0),
                  // GridView
                  sliver: new SliverGrid(
                    gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 3,
                      childAspectRatio: 3.0,
                    ),
                    delegate: new SliverChildBuilderDelegate(
                      (BuildContext context, int index) {
                        return new Container(
                          child: new Icon(new IconData(0xe145 + index,
                              fontFamily: 'MaterialIcons')),
                        );
                      },
                      childCount: 12,
                    ),
                  ),
                ),
                // ListView
                new SliverFixedExtentList(
                  itemExtent: 30.0,
                  delegate: new SliverChildBuilderDelegate(
                      (BuildContext context, int index) {
                    return new Text("当前位置：$index");
                  }, childCount: 50),
                ),
              ],
            )));
  }
}
