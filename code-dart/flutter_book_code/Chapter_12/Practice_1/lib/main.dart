import 'package:flutter/cupertino.dart';
import 'weatherDetail.dart';
import 'dart:convert';
import 'cityList.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return CupertinoApp(
      title: "城市选择",
      home: CityListWidget(),
    );
  }
}

class CityListWidget extends StatefulWidget {
  @override
  State createState() {
    return new CityListWidgetState();
  }
}

class CityListWidgetState extends State<CityListWidget> {
  CityList citylist;

  void jumpToDetail(String citycode) {
    Navigator.push<int>(context,
        new CupertinoPageRoute(builder: (BuildContext context) => WeatherDetail(citycode)));
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: CupertinoNavigationBar(
          middle: Text("城市选择"), automaticallyImplyLeading: true),
      child: new FutureBuilder(
          future:
              DefaultAssetBundle.of(context).loadString("assets/citylist.json"),
          builder: (context, data) {
            if (data.data != null) {
              citylist = CityList.fromJson(json.decode(data.data.toString()));
              return ListView.separated(
                  separatorBuilder: (context, index) => Container(
                      color: CupertinoColors.inactiveGray, height: 0.3),
                  itemCount: citylist.cityListItems.length,
                  itemBuilder: (context, index) {
                    return Container(
                      child: Center(
                          child: CupertinoButton(
                              child:
                                  Text(citylist.cityListItems[index].city_name),
                              onPressed: () {
                                if (citylist.cityListItems[index].city_code !=
                                    "") {
                                  jumpToDetail(
                                      citylist.cityListItems[index].city_code);
                                }
                              })),
                      width: double.infinity,
                      height: 60.0,
                    );
                  });
            } else {
              return new Text("载入中……");
            }
          }),
    );
  }
}
