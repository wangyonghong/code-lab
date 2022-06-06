class NewsData {
  String reason;
  Result result;
  int error_code;

  NewsData(this.reason, this.result, this.error_code);

  NewsData.fromJson(Map<String, dynamic> jsonStr) {
    this.reason = jsonStr['reason'];
    this.error_code = jsonStr['error_code'];
    this.result = Result.fromJson(jsonStr['result']);
  }
}

class Result {
  String stat;
  Data data;

  Result(this.stat, this.data);

  Result.fromJson(Map<String, dynamic> jsonStr) {
    this.stat = jsonStr['stat'];
    this.data = Data.fromJson(jsonStr['data']);
  }
}

class Data {
  List<DataItem> dataItems;

  Data.fromJson(List items) {
    dataItems = new List();
    for (var i = 0; i < items.length; i++) {
      DataItem dataItem = new DataItem(
          items[i]['uniquekey'],
          items[i]['title'],
          items[i]['date'],
          items[i]['category'],
          items[i]['author_name'],
          items[i]['url'],
          items[i]['thumbnail_pic_s'],
          items[i]['thumbnail_pic_s02'],
          items[i]['thumbnail_pic_s03']);
      dataItems.add(dataItem);
    }
  }
}

class DataItem {
  String uniquekey;
  String title;
  String date;
  String category;
  String author_name;
  String url;
  String thumbnail_pic_s;
  String thumbnail_pic_s02;
  String thumbnail_pic_s03;

  DataItem(
      this.uniquekey,
      this.title,
      this.date,
      this.category,
      this.author_name,
      this.url,
      this.thumbnail_pic_s,
      this.thumbnail_pic_s02,
      this.thumbnail_pic_s03);
}
