import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Hero动画",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HeroAnimationRoute(),
    );
  }
}

class HeroAnimationRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.topCenter,
      child: Scaffold(
        appBar: new AppBar(title: Text("小图")),
        body: InkWell(
          child: Hero(
            tag: "title",
            child: Image.asset(
              "assets/images/title.jpg",
              width: 80.0,
            ),
          ),
          onTap: () {
            Navigator.push(context, PageRouteBuilder(pageBuilder:
                (BuildContext context, Animation animation,
                    Animation secondaryAnimation) {
              return new FadeTransition(
                opacity: animation,
                child: Scaffold(
                  appBar: new AppBar(title: Text("原图")),
                  body: HeroAnimationRouteB(),
                ),
              );
            }));
          },
        ),
      ),
    );
  }
}

class HeroAnimationRouteB extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Hero(
      tag: "title",
      child: Image.asset("assets/images/title.jpg"),
    );
  }
}
