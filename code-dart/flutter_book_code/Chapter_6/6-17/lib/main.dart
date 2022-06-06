import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "线性列表组件",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("线性列表组件")),
            body: new ListView.custom(
              itemExtent: 30.0,
              childrenDelegate:
                  new CustomChildrenDelegate((BuildContext context, int index) {
                return new Text("当前位置：$index");
              }, childCount: 50),
              cacheExtent: 0.0,
            )));
  }
}

class CustomChildrenDelegate extends SliverChildBuilderDelegate {
  CustomChildrenDelegate(
    Widget Function(BuildContext, int) builder, {
    int childCount,
    bool addAutomaticKeepAlive = true,
    bool addRepaintBoundaries = true,
  }) : super(builder,
            childCount: childCount,
            addAutomaticKeepAlives: addAutomaticKeepAlive,
            addRepaintBoundaries: addRepaintBoundaries);

  @override
  void didFinishLayout(int firstIndex, int lastIndex) {
    super.didFinishLayout(firstIndex, lastIndex);
    debugPrint("Finish! Start at $firstIndex, end at $lastIndex");
  }
}
