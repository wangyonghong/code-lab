import 'package:flutter/material.dart';
import 'dart:math';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "交错动画",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: new Scaffold(
          appBar: new AppBar(title: new Text("交错动画")),
          body: new StaggeredDemo(),
        ));
  }
}

class StaggeredDemo extends StatefulWidget {
  @override
  _StaggeredDemoState createState() => _StaggeredDemoState();
}

class _StaggeredDemoState extends State<StaggeredDemo>
    with TickerProviderStateMixin {
  AnimationController _controller;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
        duration: const Duration(milliseconds: 500), vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> widgets = new List();
    for (var i = 0; i < 10; i++) {
      widgets.add(new StaggeredAnimation(controller: _controller));
      widgets.add(new Container(width: 5.0));
    }
    return GestureDetector(
        behavior: HitTestBehavior.opaque,
        onTap: () {
          _controller.reset();
          _controller.forward();
        },
        child: new Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: widgets,
        ));
  }
}

class StaggeredAnimation extends StatelessWidget {
  StaggeredAnimation({Key key, this.controller}) : super(key: key) {
    height = Tween<double>(
      begin: 0.0,
      end: new Random().nextInt(500) + 100 * 1.0,
    ).animate(
      CurvedAnimation(
        parent: controller,
        curve: Curves.ease,
      ),
    );

    color = ColorTween(
      begin: Colors.white,
      end: Colors.blue,
    ).animate(
      CurvedAnimation(
        parent: controller,
        curve: Interval(0.0, 0.8, curve: Curves.ease),
      ),
    );
  }

  final Animation<double> controller;
  Animation<double> height;
  Animation<Color> color;

  Widget _buildAnimation(BuildContext context, Widget child) {
    return Container(
      alignment: Alignment.bottomCenter,
      child: Container(
        color: color.value,
        width: 20.0,
        height: height.value,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      builder: _buildAnimation,
      animation: controller,
    );
  }
}
