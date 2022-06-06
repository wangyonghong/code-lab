import 'package:flutter/cupertino.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return CupertinoApp(
      title: "Page 1",
      home: Page1(title: "Page 1"),
    );
  }
}

class Page1 extends StatefulWidget {
  Page1({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _Page1State createState() => _Page1State();
}

class _Page1State extends State<Page1> {
  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: CupertinoNavigationBar(
        middle: Text("Page 1"),
      ),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "Page 1",
            ),
            CupertinoButton(
              child: Text("Jump to page 2"),
              onPressed: () {
                debugPrint("Jump to page 2");
                Navigator.push(
                  context,
                  new CupertinoPageRoute(builder: (context) => new Page2()),
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}

class Page2 extends StatefulWidget {
  Page2({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _Page2State createState() => _Page2State();
}

class _Page2State extends State<Page2> {
  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: CupertinoNavigationBar(
          middle: Text("Page 2"),
          previousPageTitle: "Page 1",
          trailing: CupertinoButton(padding: EdgeInsets.all(0.0),
              child: Text("Delete"),
              onPressed: () {
                debugPrint("delete");
              })),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              "Page 2",
            ),
          ],
        ),
      ),
    );
  }
}
