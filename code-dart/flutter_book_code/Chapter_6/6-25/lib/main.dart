import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "手势识别——单击、双击和长按",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("手势识别——单击、双击和长按"),
            ),
            body: new GestureDetector(
                child: new Container(
                    width: double.infinity,
                    height: double.infinity,
                    color: Colors.green),
                // 单击、双击和长按
                onTap: () => debugPrint("手势：单击"),
                onDoubleTap: () => debugPrint("手势：双击"),
                onLongPress: () => debugPrint("手势：长按"))));
  }
}
