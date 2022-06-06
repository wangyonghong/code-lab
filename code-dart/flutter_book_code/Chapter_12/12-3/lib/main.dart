import 'package:flutter/cupertino.dart';

List<String> title;

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

    title = new List();
    title.add("Home");
    title.add("Book");
    title.add("Setting");

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
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoTabScaffold(
      tabBuilder: (BuildContext context, int index) {
        return CupertinoTabView(
          builder: (BuildContext context) {
            return CupertinoPageScaffold(
              navigationBar: CupertinoNavigationBar(
                middle: Text(title[index]),
              ),
              child: Center(
                child: Text("${title[index]} Page"),
              ),
            );
          },
        );
      },
      tabBar: CupertinoTabBar(
        onTap: (int index) {
          debugPrint("点击第$index个页面");
        },
        items: <BottomNavigationBarItem>[
          BottomNavigationBarItem(
              icon: Icon(CupertinoIcons.home), title: Text(title[0])),
          BottomNavigationBarItem(
              icon: Icon(CupertinoIcons.book), title: Text(title[1])),
          BottomNavigationBarItem(
              icon: Icon(CupertinoIcons.settings), title: Text(title[2]))
        ],
      ),
    );
  }
}
