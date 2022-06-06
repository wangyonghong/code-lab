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
  CupertinoPicker buildCupertinoPicker(BuildContext context) {
    return CupertinoPicker(
        itemExtent: 30,
        looping: false,
        backgroundColor: CupertinoColors.white,
        onSelectedItemChanged: (index) {
          debugPrint("Current select $index");
        },
        children: List<Widget>.generate(5, (index){
          return Center(child: Text("我是第$index个项目"),);
        }));
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
        navigationBar: CupertinoNavigationBar(middle: Text(widget.title)),
        child: Center(
            child: CupertinoButton(
                child: Text("Show CupertinoPicker"),
                onPressed: () {
                  showCupertinoModalPopup(
                      context: context,
                      builder: (context) {
                        return buildCupertinoPicker(context);
                      });
                })));
  }
}
