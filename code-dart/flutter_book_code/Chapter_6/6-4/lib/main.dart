import 'package:flutter/material.dart';
import 'package:flutter/gestures.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "Basic Widget",
      theme: new ThemeData(primaryColor: Colors.blue),
      home: new Scaffold(
        appBar: new AppBar(title: new Text("Basic Widget")),
        body: new BasicWidgetList(),
      ),
    );
  }
}

class BasicWidgetList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView(
        children: <Widget>[
          new WidgetText(),
          new WidgetButton(),
          new WidgetImage(),
          new WidgetSwitchAndCheckbox()
        ],
      ),
    );
  }
}

// 文本组件
class WidgetText extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        child: Column(children: <Widget>[
      // 自定义样式的文本组件
      new Text(
        "文本组件" * 10,
        textAlign: TextAlign.start,
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
        textScaleFactor: 2,
        style: TextStyle(
            color: Colors.white,
            fontSize: 7.0,
            background: new Paint()..color = Colors.blue,
            decoration: TextDecoration.underline),
      ),

      // 显示富文本的文本组件
      Text.rich(TextSpan(children: [
        TextSpan(
          text: "Red text ",
          style: TextStyle(color: Colors.red, fontSize: 25.0),
        ),
        TextSpan(
          text: "Blue text ",
          style: TextStyle(color: Colors.blue, fontSize: 25.0),
          recognizer: new TapGestureRecognizer()
            ..onTap = () {
              debugPrint("Blue text clicked");
            },
        ),
      ])),

      // 样式的继承
      DefaultTextStyle(
        style: TextStyle(
          color: Colors.green,
          fontSize: 20.0,
        ),
        textAlign: TextAlign.start,
        child: Column(
          children: <Widget>[
            Text("Green Text"),
            Text(
              "Another text",
              style: TextStyle(inherit: false, color: Colors.black),
            ),
            Text(
              "My color is not green",
              style: TextStyle(color: Colors.grey),
            ),
          ],
        ),
      )
    ]));
  }
}

// 按钮组件
class WidgetButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: new Center(
        child: Column(
          children: <Widget>[
            // RaisedButton
            new RaisedButton(
                child: Text("RaisedButton"),
                onPressed: () => {debugPrint("RaisedButton clicked")}),

            //FlatButton
            new FlatButton(
              onPressed: () => {debugPrint("FlatButton clicked")},
              child: Text("FlatButton"),
            ),

            // IconButton
            new IconButton(
                icon: Icon(Icons.phone),
                onPressed: () => {debugPrint("IconButton clicked")}),

            // OutlineButton
            new OutlineButton(
                onPressed: () => {debugPrint("OutlineButton clicked")},
                child: Text("OutlineButton")),

            // 自定义按钮
            new RaisedButton(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(10.0)),
                highlightColor: Colors.green,
                child: Text("Custom button"),
                onPressed: () => {debugPrint("Custom button clicked")})
          ],
        ),
      ),
    );
  }
}

// 图片组件
class WidgetImage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        child: new Column(children: <Widget>[
      // 从资源文件中载入图片
      new Image.asset("images/image.png"),
      // 从网络上载入图片
      new Image.network("https://www.baidu.com/img/baidu_jgylogo3.gif"),
      // 使用替代图片避免网络因素造成的显示空缺
      new FadeInImage.assetNetwork(
          placeholder: "images/image.png",
          image: "https://www.baidu.com/img/baidu_jgylogo3.gif"),
      // 矢量图标
      new Icon(Icons.account_circle),
      new Icon(Icons.verified_user, color: Colors.blue),
      new Icon(Icons.android, size: 50.0)
    ]));
  }
}

// 单选框和复选框组件
class WidgetSwitchAndCheckbox extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new WidgetSwitchAndCheckboxState();
  }
}

// 单选框和复选框组件
class WidgetSwitchAndCheckboxState extends State<WidgetSwitchAndCheckbox> {
  var switchEnable = false;
  var checkboxSelected = false;

  @override
  Widget build(BuildContext context) {
    return Column(children: <Widget>[
      // 单选框组件
      new Switch(
        activeColor: Colors.green,
        value: switchEnable,
        onChanged: (value) {
          setState(() {
            switchEnable = value;
          });
        },
      ),

      // 复选框组件
      new Checkbox(
          activeColor: Colors.black,
          value: checkboxSelected,
          onChanged: (value) {
            setState(() {
              checkboxSelected = value;
            });
          })
    ]);
  }
}
