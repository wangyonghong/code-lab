import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "组件树共享数据",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
            appBar: new AppBar(title: new Text("组件树共享数据")),
            body: new ShareDataWidget(
                color: Colors.blue,
                child: new Center(
                    child: new Column(children: <Widget>[
                  new IconList(Icons.arrow_upward),
                  new IconList(Icons.arrow_forward),
                  new IconList(Icons.arrow_downward),
                  new IconList(Icons.arrow_back)
                ])))));
  }
}

class IconList extends StatefulWidget {
  final IconData icon;

  IconList(this.icon);

  @override
  State<StatefulWidget> createState() {
    return new IconListState(icon);
  }
}

class IconListState extends State<IconList> {
  var icon;

  IconListState(this.icon);

  @override
  Widget build(BuildContext context) {
    return new Icon(icon, color: ShareDataWidget.of(context).color);
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
  }
}

class ShareDataWidget extends InheritedWidget {
  final Color color;

  ShareDataWidget({@required this.color, Widget child}) : super(child: child);

  static ShareDataWidget of(BuildContext context) {
    return context.inheritFromWidgetOfExactType(ShareDataWidget);
  }

  @override
  bool updateShouldNotify(ShareDataWidget oldWidget) {
    return oldWidget.color != color;
  }
}
