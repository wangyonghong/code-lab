import 'package:flutter/material.dart';

class Page3 extends StatelessWidget {
  var currentNum;

  Page3(this.currentNum);

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: AppBar(
        title: Text("页面三"),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Text("当前计数器的值为：$currentNum，返回后将重置"),
            RaisedButton(
              onPressed: () => Navigator.pop(context, 0),
              child: Text("返回"),
            ),
          ],
        ),
      ),
    );
  }
}
