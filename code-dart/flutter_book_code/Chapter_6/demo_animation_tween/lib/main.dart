import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "补间动画——缩放",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new Scaffold(
        appBar: AppBar(
          title: Text("补间动画——缩放"),
        ),
        body: new SmallToBigLogo(),
      ),
    );
  }
}

class SmallToBigLogo extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new SmallToBigLogoState();
  }
}

class SmallToBigLogoState extends State<SmallToBigLogo>
    with SingleTickerProviderStateMixin {
  Animation<double> animation;
  AnimationController controller;
  AnimationStatus animationState;
  double animationValue;

  @override
  void initState() {
    super.initState();
    controller =
        AnimationController(duration: const Duration(seconds: 1), vsync: this);
    animation = Tween<double>(begin: 0, end: 150).animate(controller)
      ..addListener(() {
        setState(() {
          animationValue = animation.value;
        });
      })
      ..addStatusListener((AnimationStatus state) {
        setState(() {
          animationState = state;
        });
      });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      child: Column(
        children: <Widget>[
          new RaisedButton(
            onPressed: () {
              controller.reset();
              controller.forward();
            },
            child: new Text("缩放，变大！"),
          ),
          Container(
            height: animation.value,
            width: animation.value,
            child: FlutterLogo(),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();
    controller.dispose();
  }
}
