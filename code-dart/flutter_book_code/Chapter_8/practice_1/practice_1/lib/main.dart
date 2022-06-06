import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "GeoLocator",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: "GeoLocator"),
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
  String locationInfo = "点击定位按钮开始定位";

  getLocate() async {
    setState(() {
      locationInfo = "正在定位\n";
    });
    Geolocator geoLocator = new Geolocator();
    geoLocator.forceAndroidLocationManager = true;
    Position position = await geoLocator.getCurrentPosition();
    setState(() {
      locationInfo += "当前位置: ${position.latitude} - ${position.longitude}\n";
    });
    setState(() {
      locationInfo += "根据经纬度计算距离\n";
    });
    double distanceInMeters = await Geolocator().distanceBetween(
        39.9077798469, 116.3912285961, 39.9177397478, 116.3970290499);
    setState(() {
      locationInfo += "天安门到故宫的距离：$distanceInMeters米";
    });
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Text(locationInfo),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: getLocate,
        tooltip: "获取当前位置",
        child: Icon(Icons.add_location),
      ),
    );
  }
}
