main(List<String> args) {
  
  // 自动转换整型为浮点型
  double intValue = 1;
  print(intValue);

  // 使用单引号声明字符串
  var string_1 = 'I\'m David';
  
  // 使用双引号声明字符串
  var string_2 = "I'm David";

  // 使用表达式作为字符串的一部分
  var userName = "张先生";
  final welcome = "$userName，欢迎您的使用。";
  print(welcome);

  // 使用加号操作符联结
  final welcome_2 = userName + "，欢迎您的使用。";
  print(welcome_2);

  // 使用连等号判断两个字符串（对象）的值是否完全一致
  String strA = "abc";
  String strB = "abc";
  String strC = "xyz";
  print(strA == strB);
  print(strB == strC);

  // 使用三个单引号（双引号也可）声明的多行字符串
  String longStr = """
  菜单
  （新菜品！）蒸羊羔（感谢孙大厨）
  蒸熊掌
  蒸鹿尾
  烧花鸭
  烧雏鸡
  烧子鹅
  """;
  print(longStr);

  // String 转换为 int
  String toIntStr = "1000";
  int toIntValue = int.parse(toIntStr);
  print(toIntValue);

  // int 转换为 String
  int toStringInt = 1000;
  String toStringValue = toStringInt.toString();
  print(toStringValue);

  // 布尔类型的声明
  var isChecked = true;
  print(isChecked);

  // 声明列表对象
  // var listExp = [1,2,3];
  var listExp = [1,"Hello",3.456];
  print(listExp);

  // 获取列表长度
  int listLength = listExp.length;
  print("列表长度为：" + listLength.toString());

  // 声明集合对象
  // var setExp = {'Alice', 'Bob', 'Cindy', 'David'};
  Set setExp = {'Alice', 'Bob', 'Cindy', 'David', 123, 456, 7.89};
  print(setExp);

  // 向集合添加元素
  setExp.add('Elan');
  setExp.add('Bob');
  print(setExp);

  // 删除集合中的某个元素
  setExp.remove('Bob');
  print(setExp);

  // 检测某个元素是否被包含在集合中
  print(setExp.contains('Alice'));
  print(setExp.contains('Fiona'));

  // 清空集合
  setExp.clear();
  print(setExp);

  // 声明一个映射
  var newYearGift = {
    "雁雁" : "唇膏",
    "婷婷" : "精装书",
    "童童" : "精装书",
    "雯雯" : "手表"
  };
  print(newYearGift);

  // 更改一个映射中某个键对应的值
  newYearGift["童童"] =  "乐高玩具";
  newYearGift["彤彤"] =  "乐高玩具";
  print(newYearGift);
  
  // 获取某个映射中对应键的值
  print(newYearGift["童童"]);

  // 删除某个映射中对应的键值对
  newYearGift.remove("彤彤");
  print(newYearGift);

  // 检查是否存在指定的键
  print(newYearGift.containsKey("彤彤"));
  print(newYearGift.containsKey("童童"));

  // Runes表示32位Unicode字符
  var heartLogo = "\u2665";
  print(heartLogo);
  Runes happyLogo = new Runes('\u{1f47b}');
  print(happyLogo);
}