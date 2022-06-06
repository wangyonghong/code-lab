import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:io';

import 'WeatherResp.dart';

class WeatherDetail extends StatelessWidget {
  var cityCode;

  WeatherDetail(this.cityCode);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "天气详情",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: WeatherInfo(title: "天气详情",cityCode: this.cityCode,),
    );
  }
}

class WeatherInfo extends StatefulWidget {
  WeatherInfo({Key key, this.title,this.cityCode}) : super(key: key);
  final String title;
  final String cityCode;

  @override
  _WeatherInfoState createState() => _WeatherInfoState(cityCode);
}

class _WeatherInfoState extends State<WeatherInfo> {

  String cityCode;
  bool isLoading = false;
  WeatherData weatherData;

  _WeatherInfoState(this.cityCode);

  refresh() async {
    if (!isLoading) {
      isLoading = true;
      try {
        HttpClient httpClient = new HttpClient();
        debugPrint(cityCode);
        HttpClientRequest request = await httpClient.getUrl(Uri.parse(
            "http://t.weather.sojson.com/api/weather/city/$cityCode"));
        HttpClientResponse response = await request.close();
        String responseContent = await response.transform(utf8.decoder).join();
        setState(() {
          debugPrint(responseContent);
          this.weatherData = WeatherData.fromJson(json.decode(responseContent));
        });
        httpClient.close();
      } catch (e) {
        debugPrint("请求失败：$e");
      } finally {
        setState(() {
          isLoading = false;
        });
      }
    } else {
      debugPrint("正在刷新");
    }
  }


  @override
  void initState() {
    super.initState();
    refresh();
  }

  Column buildBaseInfo(weatherData) {
    List<Widget> widgets = new List();
    widgets.add(Text("获取时间：${weatherData.time}"));
    widgets.add(Text("省：${weatherData.cityInfo.parent}"));
    widgets.add(Text("市：${weatherData.cityInfo.city}"));
    widgets.add(Text("更新时间：${weatherData.cityInfo.updateTime}"));
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: widgets,
    );
  }

  Column buildCurrent(weatherData) {
    List<Widget> widgets = new List();
    widgets.add(Text("湿度：${weatherData.data.shidu}"));
    widgets.add(Text("PM2.5：${weatherData.data.pm25}"));
    widgets.add(Text("PM10：${weatherData.data.pm10}"));
    widgets.add(Text("空气质量：${weatherData.data.quality}"));
    widgets.add(Text("气温：${weatherData.data.wendu}"));
    widgets.add(Text("感冒指数：${weatherData.data.ganmao}"));
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: widgets,
    );
  }

  Column buildYesterday(weatherData) {
    List<Widget> widgets = new List();
    widgets.add(Text("昨日日出：${weatherData.data.yesterday.sunrise}"));
    widgets.add(Text("昨日日落：${weatherData.data.yesterday.sunset}"));
    widgets.add(Text("昨日最高温：${weatherData.data.yesterday.high}"));
    widgets.add(Text("昨日最低温：${weatherData.data.yesterday.low}"));
    widgets.add(Text("昨日风向：${weatherData.data.yesterday.fx}"));
    widgets.add(Text("昨日风力：${weatherData.data.yesterday.fl}"));
    widgets.add(Text("昨日天气：${weatherData.data.yesterday.type}"));
    widgets.add(Text("昨日提醒：${weatherData.data.yesterday.notice}"));
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: widgets,
    );
  }

  Column buildForecast(weatherData) {
    List<Widget> widgets = new List();
    for (var i = 0; i < weatherData.data.foreCast.foreCastItems.length; i++) {
      widgets
          .add(Text("日期：${weatherData.data.foreCast.foreCastItems[i].date}"));
      widgets.add(
          Text("日出：${weatherData.data.foreCast.foreCastItems[i].sunrise}"));
      widgets
          .add(Text("日落：${weatherData.data.foreCast.foreCastItems[i].sunset}"));
      widgets
          .add(Text("最高温：${weatherData.data.foreCast.foreCastItems[i].high}"));
      widgets
          .add(Text("最低温：${weatherData.data.foreCast.foreCastItems[i].low}"));
      widgets.add(Text("风向：${weatherData.data.foreCast.foreCastItems[i].fx}"));
      widgets.add(Text("风力：${weatherData.data.foreCast.foreCastItems[i].fl}"));
      widgets
          .add(Text("天气：${weatherData.data.foreCast.foreCastItems[i].type}"));
    }
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: widgets,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ListView(
        children: <Widget>[
          Text(weatherData == null ? "" : "【基本信息】",
              style: new TextStyle(color: Colors.red)),
          weatherData == null ? Text("") : buildBaseInfo(weatherData),
          Text(weatherData == null ? "" : "【当前天气】",
              style: new TextStyle(color: Colors.red)),
          weatherData == null ? Text("") : buildCurrent(weatherData),
          Text(weatherData == null ? "" : "【昨日天气】",
              style: new TextStyle(color: Colors.red)),
          weatherData == null ? Text("") : buildYesterday(weatherData),
          Text(weatherData == null ? "" : "【未来预报】",
              style: new TextStyle(color: Colors.red)),
          weatherData == null ? Text("") : buildForecast(weatherData),
        ],
      ),
    );
  }
}
