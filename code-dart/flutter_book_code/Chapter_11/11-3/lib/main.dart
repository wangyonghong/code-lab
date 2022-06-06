import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
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

class _MyHomePageState extends State<MyHomePage> with SingleTickerProviderStateMixin{

  TabController _controller;
  var _tabs = <Tab>[];

  @override
  void initState() {
    super.initState();
    _controller = TabController(initialIndex: 0,length: 3,vsync: this);
    _tabs = <Tab>[
          Tab(text: "TabA",),
          Tab(text: "TabB",),
          Tab(text: "TabC",)];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        bottom: TabBar(tabs: _tabs,
          indicatorColor: Colors.white,
          indicatorWeight: 5,
          indicatorSize: TabBarIndicatorSize.tab,
          controller: _controller ,),
      ),
      body: TabBarView(
        controller: _controller,
        children: _tabs
                  .map((Tab tab) =>
                  Container(child: Center(child: Text(tab.text))))
                  .toList()),
    );
  }

  @override
  void dispose() {
    super.dispose();
    _controller.dispose();
  }

}
