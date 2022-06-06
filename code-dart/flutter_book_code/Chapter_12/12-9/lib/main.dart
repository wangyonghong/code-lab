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
  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
        navigationBar: CupertinoNavigationBar(middle: Text(widget.title)),
        child: Container(
            alignment: Alignment.bottomCenter,
            child: Container(
                height: 300.0,
                child: CupertinoTimerPicker(
                    onTimerDurationChanged: (duration) {
                      debugPrint(
                          "onTimerDurationChanged: ${duration.inSeconds}s");
                    },
                    mode: CupertinoTimerPickerMode.hms,
                    initialTimerDuration: Duration(minutes: 5)))));
  }
}
