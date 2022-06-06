import 'package:flutter/cupertino.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return CupertinoApp(
      title: 'Flutter Demo',
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

  void showAlertDialog(BuildContext context) {
    showCupertinoDialog(
        context: context,
        builder: (context) {
          return CupertinoAlertDialog(
            title: Text("您正在控制卧室的台灯"),
            content: Text("请选择要执行的操作："),
            actions: <Widget>[
              CupertinoButton(
                  child: Text("打开"),
                  onPressed: () {
                    debugPrint("台灯打开");
                    Navigator.pop(context);
                  }),
              CupertinoButton(
                  child: Text("关闭"),
                  onPressed: () {
                    debugPrint("台灯关闭");
                    Navigator.pop(context);
                  }),
              CupertinoButton(
                  child: Text("增加亮度"),
                  onPressed: () {
                    debugPrint("增加亮度");
                    Navigator.pop(context);
                  }),
              CupertinoButton(
                  child: Text("减少亮度"),
                  onPressed: () {
                    debugPrint("减少亮度");
                    Navigator.pop(context);
                  }),
              CupertinoButton(
                  child: Text("保持现状"),
                  onPressed: () {
                    debugPrint("保持台灯");
                    Navigator.pop(context);
                  }),
            ],
          );
        });
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
        navigationBar: CupertinoNavigationBar(middle: Text(widget.title)),
        child: Center(
            child: CupertinoButton(
                child: Text("Show Alert Dialog"),
                onPressed: () {
                  showAlertDialog(context);
                })));
  }
}
