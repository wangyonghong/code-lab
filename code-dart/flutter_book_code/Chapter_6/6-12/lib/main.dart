import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "容器类组件演示",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(
              title: new Text("容器类组件演示"),
            ),
            body: new Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                //内边距Padding组件
                new Text("内边距Padding组件"),
                Padding(
                  padding: EdgeInsets.all(10.0),
                  child: new Icon(Icons.home),
                ),

                // 约束
                new Text("约束组件"),
                new ConstrainedBox(
                    constraints:
                    BoxConstraints(minWidth: 50.0, maxHeight: 100.0),
                    child: new Column(
                      children: <Widget>[
                        Container(
                            color: Colors.green, height: 50.0, width: 50.0),
                        Container(
                            color: Colors.red, height: 50.0, width: 100.0),
//                        Container(
//                            color: Colors.blue, height: 100.0, width: 50.0),
                      ],
                    )),

                //装饰
                new Text("装饰组件"),
                new DecoratedBox(
                    position: DecorationPosition.background,
                    decoration: BoxDecoration(
                        gradient:
                        LinearGradient(colors: [Colors.blue, Colors.green]),
                        borderRadius: BorderRadius.circular(5)),
                    child: new Text("我是文本组件")),

                new DecoratedBox(
                    position: DecorationPosition.foreground,
                    decoration: FlutterLogoDecoration(
                        lightColor: Colors.green, darkColor: Colors.red),
                    child: new Text("我是文本组件")),

                new DecoratedBox(
                    position: DecorationPosition.background,
                    decoration: ShapeDecoration(
                        shape: Border.all(color: Colors.red, width: 1.0) +
                            Border.all(color: Colors.green, width: 1.0)),
                    child: new Text("我是文本组件")),

                new DecoratedBox(
                    position: DecorationPosition.background,
                    decoration: UnderlineTabIndicator(
                        borderSide: BorderSide(color: Colors.red)),
                    child: new Text("我是文本组件")),

                // 变换
                new Text("变换组件"),
                new Transform(
                    transform: new Matrix4.skewY(0.3), child: new Text("我是文本组件")),

                // Container容器
                new Text("Container容器组件"),
                new Container(
                  color: Colors.green,
                  margin: EdgeInsets.all(10.0),
                  padding: EdgeInsets.all(10.0),
                  width: 50,
                  height: 50,
                  child: new Container(color: Colors.blue)
                )
              ],
            )));
  }
}
