import 'package:flutter/material.dart';
import 'package:flutter_blue/flutter_blue.dart';
import 'bt_device.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Bluetooth Demo",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "Bluetooth Demo"),
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
  String status = "";
  var scanSubscription;
  List<BtDevice> deviceName;

  void startScan() {
    deviceName = new List();
    scanSubscription = FlutterBlue.instance.scan().listen((scanResult) {
      BtDevice btDevice = new BtDevice(
          scanResult.device.name, scanResult.device.id, scanResult.device.type);
      if (!deviceName.contains(btDevice)) {
        deviceName.add(btDevice);
        setState(() {});
      }
    });
  }

  void stopScan() {
    if (scanSubscription != null) {
      scanSubscription.cancel();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                child: Text("搜索蓝牙设备"),
                onPressed: startScan,
              ),
              Container(width: 10.0),
              RaisedButton(
                child: Text("停止搜索蓝牙设备"),
                onPressed: stopScan,
              ),
            ],
          ),
          deviceName == null
              ? Text("点击开始搜索蓝牙设备")
              : Flexible(
                  child: ListView.separated(
                      separatorBuilder: (BuildContext context, int index) {
                        return new Divider(color: Colors.grey);
                      },
                      itemCount: deviceName.length,
                      itemBuilder: (BuildContext context, int index) {
                        return InkWell(
                            onTap: () {},
                            child: Container(
                                height: 35.0,
                                child: Text(
                                    "${deviceName[index].singleDeviceName} (${deviceName[index].id.toString()}), ${deviceName[index].type.toString()}")));
                      }),
                )
        ],
      ),
    );
  }
}
