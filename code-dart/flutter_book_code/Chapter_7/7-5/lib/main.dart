import 'package:flutter/material.dart';

import 'database/dbhelper.dart';
import 'employeelist.dart';
import 'model/employee.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "数据库的使用",
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(title: "数据库的使用"),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Employee employee = new Employee("", "", "", "");

  String firstName;
  String lastName;
  String emailId;
  String mobileNo;
  final scaffoldKey = new GlobalKey<ScaffoldState>();
  final formKey = new GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      key: scaffoldKey,
      appBar: new AppBar(title: new Text("数据库的使用"), actions: <Widget>[
        new IconButton(
          icon: const Icon(Icons.view_list),
          tooltip: "全部数据",
          onPressed: () {
            navigateToEmployeeList();
          },
        ),
      ]),
      body: new Padding(
        padding: const EdgeInsets.all(16.0),
        child: new Form(
          key: formKey,
          child: new Column(
            children: [
              new TextFormField(
                keyboardType: TextInputType.text,
                decoration: new InputDecoration(labelText: "名字"),
                validator: (val) => val.length == 0 ? "请输入名字" : null,
                onSaved: (val) => this.firstName = val,
              ),
              new TextFormField(
                keyboardType: TextInputType.text,
                decoration: new InputDecoration(labelText: "姓氏"),
                validator: (val) => val.length == 0 ? "请输入姓氏" : null,
                onSaved: (val) => this.lastName = val,
              ),
              new TextFormField(
                keyboardType: TextInputType.phone,
                decoration: new InputDecoration(labelText: "电话号码"),
                validator: (val) => val.length == 0 ? "请输入电话号码" : null,
                onSaved: (val) => this.mobileNo = val,
              ),
              new TextFormField(
                keyboardType: TextInputType.emailAddress,
                decoration: new InputDecoration(labelText: "电子邮箱"),
                validator: (val) => val.length == 0 ? "请输入电子邮箱地址" : null,
                onSaved: (val) => this.emailId = val,
              ),
              new Container(
                margin: const EdgeInsets.only(top: 10.0),
                child: new RaisedButton(
                  onPressed: _submit,
                  child: new Text("添加数据"),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  void _submit() {
    if (this.formKey.currentState.validate()) {
      formKey.currentState.save();
    } else {
      return null;
    }
    var employee = Employee(firstName, lastName, mobileNo, emailId);
    var dbHelper = DBHelper();
    dbHelper.saveEmployee(employee);
    this.formKey.currentState.reset();
  }

  void navigateToEmployeeList() {
    Navigator.push(
      context,
      new MaterialPageRoute(builder: (context) => new MyEmployeeList()),
    );
  }
}
