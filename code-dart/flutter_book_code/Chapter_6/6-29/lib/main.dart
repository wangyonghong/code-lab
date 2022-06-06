import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "通知组件",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("通知组件")),
            body: new NotificationListener(
                onNotification: (notification) {
                  switch (notification.runtimeType) {
                    case ScrollStartNotification:
                      debugPrint("滚动开始");
                      break;
                    case ScrollUpdateNotification:
                      debugPrint("滚动中");
                      break;
                    case ScrollEndNotification:
                      debugPrint("滚动停止");
                      break;
                    case OverscrollNotification:
                      debugPrint("滚动到界限");
                      break;
                  }
                },
                child: new ListView.builder(
                  itemCount: 50,
                  itemExtent: 30,
                  itemBuilder: (BuildContext context, int index) {
                    return new Text("当前位置：$index");
                  },
                ))));
  }
}
