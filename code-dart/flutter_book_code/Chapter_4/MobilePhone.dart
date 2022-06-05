main(List<String> args) {
  var iPhone_8 = new IPhone();
  iPhone_8.model = "iPhone 8";
  iPhone_8.screenSize = 4.7;
  iPhone_8.weight = 148;
  iPhone_8.timePublished = "20170913";
  iPhone_8.systemVersion = "iOS 11";
  print(iPhone_8.toString());
  iPhone_8.call("12345");
  iPhone_8.sendSms("12345", "刚给你打电话");

  // 接口
  iPhone_8.printMyBrand(iPhone_8.runtimeType);

  var samsungS8 = new AndroidPhone();
  samsungS8.brand = "三星";
  samsungS8.model = "S8";
  samsungS8.screenSize = 5.8;
  samsungS8.weight = 155;
  samsungS8.timePublished = 20170329;
  samsungS8.systemVersion = "Android 7.0";
  print(samsungS8.toString());
  samsungS8.call("67890");
  samsungS8.sendSms("67890", "刚打电话给你");

  // 接口
  samsungS8.printMyBrand(samsungS8.runtimeType);

  // Mixin特性
  samsungS8.bringMeBack("浏览器");

  // 枚举
  AndroidBrand.Huawei.index;
  if(samsungS8.brand == AndroidBrand.Samsung) {
    // ...
  }
}

// 表示手机的父类
abstract class MobilePhone {
  var screenSize;
  var weight;
  var timePublished;
  var model;
  var systemVersion;

  void call(var number);

  void sendSms(var number, var content) {
    print("发短信给：$number，内容是：$content");
  }
}

// 表示iPhone手机的子类
class IPhone extends MobilePhone implements GetBrand{

  @override
  void call(number) {
    print("来自iPhone $model 拨出电话：$number");
  }

  @override
  void sendSms(number, content) {
    super.sendSms(number, content);
    print("来自iPhone $model");
  }

  @override
  String toString() {
    return "我是iPhone手机，型号是：$model";
  }

  @override
  void printMyBrand(brandStr) {
    print("我是$brandStr手机");
  }
}

// 表示Android手机的子类
class AndroidPhone extends MobilePhone with BackgroundApp implements GetBrand {
  var brand;

  @override
  void call(number) {
    print("来自$brand $model 拨出电话：$number");
  }

  @override
  void sendSms(number, content) {
    super.sendSms(number, content);
    print("来自$brand $model");
  }

  @override
  String toString() {
    return "我是$brand手机，型号是：$model";
  }

  @override
  void printMyBrand(brandStr) {
    print("我是Android手机");
  }
}

// 接口
class GetBrand {
    void printMyBrand(var brandStr) {
        print("brandStr的值为 $brandStr");
    }
}

// Mixin特性
class BackgroundApp{
  void bringMeBack(var appName) {
    print("$appName 后台并持续运行中");
  }
}

// 枚举
enum AndroidBrand {
  Samsung,
  Huawei,
  Xiaomi,
  Oppo,
  Vivo
}