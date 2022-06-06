import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "上滑列表加载",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "上滑列表加载"),
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
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: InfiniteListView(),
      ),
    );
  }
}

class InfiniteListView extends StatefulWidget {
  @override
  InfiniteListViewState createState() => new InfiniteListViewState();
}

class InfiniteListViewState extends State<InfiniteListView> {
  static const loadingTag = "end";
  var itemContent = <String>[loadingTag];

  @override
  void initState() {
    super.initState();
    loadNextPage();
  }

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      itemCount: itemContent.length,
      itemBuilder: (context, index) {
        // 滚动到末尾的判断
        if (itemContent[index] == loadingTag) {
          loadNextPage();
          return Container(
            padding: const EdgeInsets.all(16.0),
            alignment: Alignment.center,
            child: new Center(child: new Text("更多内容加载中……")),
          );
        }
        return ListTile(title: Text(itemContent[index]));
      },
      separatorBuilder: (context, index) => Divider(height: 0.0),
    );
  }

  // 获取下一分页数据
  void loadNextPage() {
    Future.delayed(Duration(seconds: 1)).then((e) {
      var length = itemContent.length;
      List<String> nextPart = new List();
      for (var i = length; i < length + 20; i++) {
        nextPart.add(i.toString());
      }
      itemContent.insertAll(itemContent.length - 1, nextPart);
      setState(() {});
    });
  }
}
