class WeatherData {
  String time;
  CityInfo cityInfo;
  String date;
  String message;
  int status;
  Data data;

  WeatherData(this.time, this.cityInfo, this.date, this.message, this.status,
      this.data);

  WeatherData.fromJson(Map<String, dynamic> jsonStr) {
    this.time = jsonStr['time'];
    this.cityInfo = CityInfo.fromJson(jsonStr['cityInfo']);
    this.data = Data.fromJson(jsonStr['data']);
    this.date = jsonStr['date'];
    this.message = jsonStr['message'];
    this.status = jsonStr['status'];
  }
}

class CityInfo {
  String city;
  String cityId;
  String parent;
  String updateTime;

  CityInfo(this.city, this.cityId, this.parent, this.updateTime);

  CityInfo.fromJson(Map<String, dynamic> jsonStr) {
    this.city = jsonStr['city'];
    this.cityId = jsonStr['cityId'];
    this.parent = jsonStr['parent'];
    this.updateTime = jsonStr['updateTime'];
  }
}

class Data {
  String shidu;
  double pm25;
  double pm10;
  String quality;
  String wendu;
  String ganmao;
  Yesterday yesterday;
  ForeCast foreCast;

  Data(this.shidu, this.pm25, this.pm10, this.quality, this.wendu, this.ganmao,
      this.yesterday, this.foreCast);

  Data.fromJson(Map<String, dynamic> jsonStr) {
    this.shidu = jsonStr['shidu'];
    this.pm25 = jsonStr['pm25'];
    this.pm10 = jsonStr['pm10'];
    this.quality = jsonStr['quality'];
    this.wendu = jsonStr['wendu'];
    this.ganmao = jsonStr['ganmao'];
    this.yesterday = Yesterday.fromJson(jsonStr['yesterday']);
    this.foreCast = ForeCast.fromJson(jsonStr['forecast']);

  }
}

class Yesterday {
  String date;
  String sunrise;
  String high;
  String low;
  String sunset;
  double aqi;
  String ymd;
  String week;
  String fx;
  String fl;
  String type;
  String notice;

  Yesterday(this.date, this.sunrise, this.high, this.low, this.sunset, this.aqi,
      this.ymd, this.week, this.fx, this.fl, this.type, this.notice);

  Yesterday.fromJson(Map<String, dynamic> jsonStr) {
    this.date = jsonStr['date'];
    this.sunrise = jsonStr['sunrise'];
    this.high = jsonStr['high'];
    this.low = jsonStr['low'];
    this.sunset = jsonStr['sunset'];
    this.aqi = jsonStr['aqi'];
    this.ymd = jsonStr['ymd'];
    this.week = jsonStr['week'];
    this.fx = jsonStr['fx'];
    this.fl = jsonStr['fl'];
    this.type = jsonStr['type'];
    this.notice = jsonStr['notice'];
  }
}

class ForeCast {
  List<ForeCastItem> foreCastItems;

  ForeCast.fromJson(List items){
    foreCastItems = new List();
    for (var i = 0; i < items.length; i++) {
      ForeCastItem foreCastItem = new ForeCastItem(
          items[i]['date'],
          items[i]['sunrise'],
          items[i]['high'],
          items[i]['low'],
          items[i]['sunset'],
//          items[i]['aqi'],
          items[i]['ymd'],
          items[i]['week'],
          items[i]['fx'],
          items[i]['fl'],
          items[i]['type'],
          items[i]['notice']);
      foreCastItems.add(foreCastItem);
    }
  }

}

class ForeCastItem {
  String date;
  String sunrise;
  String high;
  String low;
  String sunset;
//  double aqi;
  String ymd;
  String week;
  String fx;
  String fl;
  String type;
  String notice;

  ForeCastItem(this.date, this.sunrise, this.high, this.low, this.sunset,
//      this.aqi, this.ymd, this.week, this.fx, this.fl, this.type, this.notice);

      this.ymd, this.week, this.fx, this.fl, this.type, this.notice);

}
