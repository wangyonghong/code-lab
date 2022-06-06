import 'package:flutter/material.dart';

class Page2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text("页面二"),
      ),
      body: Center(
        child: RaisedButton(
          onPressed: () => Navigator.pop(context, null),
          child: Text("返回"),
        ),
      ),
    );
  }
}
