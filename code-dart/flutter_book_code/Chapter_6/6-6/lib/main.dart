import 'package:flutter/material.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  MyAppState createState() => new MyAppState();
}

class MyAppState extends State<MyApp> {
  GlobalKey<FormState> formGlobalKey = new GlobalKey<FormState>();
  String username;

  void save() {
    var form = formGlobalKey.currentState;
    if (form.validate()) {
      form.save();
    } else {
      form.reset();
    }
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
        title: "Form demo",
        home: new Scaffold(
            appBar: new AppBar(
              title: new Text('Form demo'),
            ),
            body: new Column(children: <Widget>[
              new Form(
                key: formGlobalKey,
                child: new TextFormField(
                  decoration: new InputDecoration(
                    labelText: "姓名",
                  ),
                  validator: (content) {
                    if (content.length <= 0) {
                      return "姓名太短";
                    } else if (content.length > 8) {
                      return "姓名过长";
                    }
                  },
                  onSaved: (content) {
                    username = content;
                  },
                ),
              ),
              new RaisedButton(onPressed: save, child: new Text("保存"))
            ])));
  }
}
