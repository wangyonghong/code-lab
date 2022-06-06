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
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text('Welcome to Flutter'),
        ),
        body: new Center(
          child: new ThreeArea(),
        ),
      ),
    );
  }
}

class ThreeArea extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          TopArea(),
          MidAreaParent(),
          BottomAreaParent(),
        ],
      ),
    );
  }
}

class TopArea extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new TopAreaState();
  }
}

class TopAreaState extends State<TopArea> {
  var focused = false;

  void doClick() {
    setState(() {
      focused = !focused;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: doClick,
      child: new Container(
        child: Center(
          child: new Text(
            "Click me!.",
            style: new TextStyle(fontSize: 18.0, color: Colors.red),
          ),
        ),
        width: double.infinity,
        height: 200.0,
        decoration: new BoxDecoration(
          color: focused ? Colors.red : Colors.grey,
        ),
      ),
    );
  }
}

class MidAreaParent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new MidAreaParentState();
  }
}

class MidAreaParentState extends State<MidAreaParent> {
  var focused = false;

  void handleClick(bool focused) {
    setState(() {
      this.focused = focused;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Container(
      child: new MidArea(
        focused: focused,
        focusChanged: handleClick,
      ),
    );
  }
}

class MidArea extends StatelessWidget {
  final focused;
  final ValueChanged<bool> focusChanged;

  MidArea({this.focused, this.focusChanged});

  void doClick() {
    focusChanged(!focused);
  }

  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: doClick,
      child: new Container(
        child: Center(
          child: new Text(
            "Click me!.",
            style: new TextStyle(fontSize: 18.0, color: Colors.green),
          ),
        ),
        width: double.infinity,
        height: 200.0,
        decoration: new BoxDecoration(
          color: focused ? Colors.green : Colors.grey,
        ),
      ),
    );
  }
}

class BottomAreaParent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new BottomAreaParentState();
  }
}

class BottomAreaParentState extends State<BottomAreaParent> {
  var focused;

  void handleClick(bool focused) {
    setState(() {
      this.focused = focused;
      if (focused) {
        debugPrint("Change to blue.");
      } else {
        debugPrint("Change to grey.");
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Container(
      child: new BottomArea(
        focused: this.focused,
        focusChanged: handleClick,
      ),
    );
  }
}

class BottomArea extends StatefulWidget {
  final bool focused;
  final ValueChanged<bool> focusChanged;

  BottomArea({this.focused, this.focusChanged});

  @override
  State<StatefulWidget> createState() {
    return new BottomAreaState();
  }
}

class BottomAreaState extends State<BottomArea> {
  var focused = false;

  void doClick() {
    setState(() {
      focused = !focused;
    });
    widget.focusChanged(focused);
  }

  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: doClick,
      child: new Container(
        child: Center(
          child: new Text(
            "Click me!.",
            style: new TextStyle(fontSize: 18.0, color: Colors.blue),
          ),
        ),
        width: double.infinity,
        height: 200.0,
        decoration: new BoxDecoration(
          color: focused ? Colors.blue : Colors.grey,
        ),
      ),
    );
  }
}
