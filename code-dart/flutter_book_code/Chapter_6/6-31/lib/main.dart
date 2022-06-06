import 'package:flutter/material.dart';
import 'package:event_bus/event_bus.dart';
import 'dart:math';

EventBus eventBus;
var themeColor;

final List<Color> themeColorList = [
  Colors.red,
  Colors.orange,
  Colors.yellow,
  Colors.green,
  Colors.cyan,
  Colors.blue,
  Colors.purple
];

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return RainbowTheme();
  }
}

class RainbowTheme extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new RainbowThemeState();
  }
}

class RainbowThemeState extends State<RainbowTheme> {
  @override
  void initState() {
    super.initState();
    eventBus = new EventBus();
    themeColor = themeColorList[0];
    eventBus
        .on<ChangeThemeEvent>()
        .listen((ChangeThemeEvent onData) => setState(() {
              themeColor = themeColorList[onData.themeIndex];
            }));
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "全局事件总线",
        theme: ThemeData(
          primarySwatch: themeColor,
        ),
        home: new Scaffold(
            appBar: AppBar(
              title: Text("全局事件总线"),
            ),
            body: new RaisedButton(
                onPressed: () => eventBus
                    .fire(new ChangeThemeEvent(new Random().nextInt(7))),
                child: new Text("点击更换主题色"))));
  }
}

class ChangeThemeEvent {
  var themeIndex;

  ChangeThemeEvent(this.themeIndex);
}
