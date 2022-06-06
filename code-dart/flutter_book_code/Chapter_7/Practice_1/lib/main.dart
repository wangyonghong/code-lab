import 'package:flutter/material.dart';
import 'weatherDetail.dart';
import 'dart:convert';
import 'cityList.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "城市选择",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
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
        new MaterialPageRoute(builder: (BuildContext context) {
      return new WeatherDetail(citycode);
    }));
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "城市选择",
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text("城市选择"),
          ),
          body: new FutureBuilder(
              future: DefaultAssetBundle.of(context)
                  .loadString("assets/citylist.json"),
              builder: (context, data) {
                if (data.data != null) {
                  citylist =
                      CityList.fromJson(json.decode(data.data.toString()));
                  return new ListView.separated(
                      itemBuilder: (context, index) {
                        return new InkWell(
                          child: new Container(
                            child: Center(
                                child: Text(
                                    citylist.cityListItems[index].city_name)),
                            width: double.infinity,
                            height: 50.0,
                          ),
                          onTap: () {
                            if (citylist.cityListItems[index].city_code != "") {
                              jumpToDetail(
                                  citylist.cityListItems[index].city_code);
                            }
                          },
                        );
                      },
                      separatorBuilder: (context, index) =>
                          Divider(height: 0.0),
                      itemCount: citylist.cityListItems.length);
                }else{
                  return new Text("载入中……");
                }
              }),
        ));
  }
}
