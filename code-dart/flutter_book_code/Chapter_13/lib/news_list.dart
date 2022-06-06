import 'package:flutter/material.dart';
import 'news_json_resp.dart';

import 'package:url_launcher/url_launcher.dart';

class ListBuilder {
  static List<Widget> genWidgetsFromJson(NewsData newsData) {
    List<Widget> returnData = new List();
    List<DataItem> dataItems = newsData.result.data.dataItems;
    for (var i = 0; i < dataItems.length; i++) {
      returnData.add(ListItem.genSingleItem(dataItems[i]));
    }
    return returnData;
  }
}

class ListItem {
  static Widget genSingleItem(DataItem dataItem) {
    String uniquekey = dataItem.uniquekey;
    String title = dataItem.title;
    String date = dataItem.date;
    String category = dataItem.category;
    String author_name = dataItem.author_name;
    String url = dataItem.url;
    String thumbnail_pic_s = dataItem.thumbnail_pic_s;
    String thumbnail_pic_s02 = dataItem.thumbnail_pic_s02;
    String thumbnail_pic_s03 = dataItem.thumbnail_pic_s03;
    return Container(
        padding: EdgeInsets.all(5.0),
        child: InkWell(
            onTap: () {
              openDetail(url);
            },
            child: Row(
              children: <Widget>[
                Image(
                    alignment: Alignment.centerLeft,
                    width: 100,
                    height: 100,
                    image: new NetworkImage(thumbnail_pic_s)),
                Expanded(
                    child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    Container(
                        padding: EdgeInsets.all(3.0),
                        child: Text(date),
                        alignment: Alignment.topLeft),
                    Container(
                      padding: EdgeInsets.all(3.0),
                      child: Text(title,
                          softWrap: false, overflow: TextOverflow.ellipsis),
                      alignment: Alignment.centerLeft,
                    ),
                    Container(
                        padding: EdgeInsets.all(3.0),
                        child: Text(author_name),
                        alignment: Alignment.bottomLeft)
                  ],
                ))
              ],
            )));
  }

  static void openDetail(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw 'Could not launch $url';
    }
  }
}
