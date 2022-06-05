main(List<String> args) {

  // 初始化一个字符串，内容为David，用来表示一个人名
  var name = "David";
  print(name);

  // 使用dynamic（动态数据类型）对name进行初始化
  // dynamic name = "David";

  // 指明String（字符串类型）对name进行初始化
  // String name = "David";

  // 未经初始化的变量，值为null
  var nullTest;
  print(nullTest);

  // 使用final声明常量
  final weight = 67.5;
  final int height = 170;

  // 使用const声明常量
  const int age = 18;
  const gender = "female";

  // final和const的区别
  // var singlePrice = 1;
  const singlePrice = 1;
  final buyTen = singlePrice * 10;
  const buyTwo = singlePrice * 2;

  // 使用const创建常量值
  var intList = const[];
  intList = [1,2,3];

  // 使用const创建常量值
  const temp = [];
  var intList_2 = temp;
  intList_2 = [1,2,3];
  // 下面一行会报错误，因为temp本身是const，不可变
  // temp = [1,2,3];
}