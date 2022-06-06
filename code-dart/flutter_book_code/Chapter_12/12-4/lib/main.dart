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

  CupertinoActionSheet buildCupertinoActionSheet(BuildContext context) {
    return CupertinoActionSheet(
        cancelButton: CupertinoActionSheetAction(
            onPressed: () {
              debugPrint("取消");
              Navigator.pop(context);
            },
            child: Text("取消")),
        actions: <Widget>[
          CupertinoActionSheetAction(
              onPressed: () {
                debugPrint("喜欢");
                Navigator.pop(context);
              },
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Icon(CupertinoIcons.heart_solid),
                  Container(width: 5.0),
                  Text("喜欢")
                ],
              )),
          CupertinoActionSheetAction(
              onPressed: () {
                debugPrint("跳过");
                Navigator.pop(context);
              },
              child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Icon(CupertinoIcons.delete_solid),
                    Container(width: 5.0),
                    Text("跳过")
                  ]))
        ]);
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
        navigationBar: CupertinoNavigationBar(middle: Text(widget.title)),
        child: Center(
            child: CupertinoButton(
                child: Text("Show menu"),
                onPressed: () {
                  showCupertinoModalPopup(
                      context: context,
                      builder: (context) {
                        return buildCupertinoActionSheet(context);
                      });
                })));
  }
}
