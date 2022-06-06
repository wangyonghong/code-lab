class CityList {
  List<CityListItem> cityListItems;

  CityList.fromJson(List items) {
    cityListItems = new List();
    for (var i = 0; i < items.length; i++) {
      CityListItem cityListItem = new CityListItem(
          items[i]['_id'],
          items[i]['id'],
          items[i]['pid'],
          items[i]['city_code'],
          items[i]['city_name']);
      cityListItems.add(cityListItem);
    }
  }
}

class CityListItem {
  int _id;
  int id;
  int pid;
  String city_code;
  String city_name;

  CityListItem(this._id, this.id, this.pid, this.city_code, this.city_name);
}
