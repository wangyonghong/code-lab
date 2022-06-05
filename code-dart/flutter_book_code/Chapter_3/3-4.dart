main(List<String> args) {
  newFriend("Alice", 25);
  print(getPrice(bookName: "Dart 编程入门"));
  print(sayWelcome('张先生'));

  // 变量的作用域
  void scopeTest() {
    var scope_a = 1;
    void scopeTestInner() {
      var scope_b = 2;
      print(scope_a);
      print(scope_b);
    }

    print(scope_a);
    // print(scope_b);
  }

  // 判断两个方法是否相等
  print(leftSide() == rightSide());
}

// 声明一个方法
int getNumber() {
  return 150;
}

// 只有一个表达式的方法的简便写法
double getDouble() => 1.5 * getNumber();

// 声明一个带有必选参数的方法
newFriend(String name, var age) {
  print("I have a new friend: $name, age is $age");
}

// 声明一个带有可选命名参数的方法
double getPrice({bookName: "非热门图书"}) {
  if (bookName == "Dart 编程入门") {
    return 78.5;
  } else {
    return 50.0;
  }
}

//声明一个带有可选位置参数的方法
String sayWelcome([String name = "您好"]) {
  return "$name，欢迎您的使用";
}

// 闭包 
// TODO 这里报错了，以后再看
// Function newFriend_2(String name, var age) {
//   print("I have a new friend: $name, age is $age");
// }

// 判断两个方法是否相等 - 左侧
int leftSide() {
  return 2 + 3;
}

// 判断两个方法是否相等 - 右侧
int rightSide() {
  return 3 + 2;
}
