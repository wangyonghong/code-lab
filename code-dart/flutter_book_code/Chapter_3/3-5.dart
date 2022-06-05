main(List<String> args) {

  // 运算符优先级
  if((1 % 2 == 1) || (2 % 3 == 1)){
    print("true");
  } 

  if(1 % 2 == 1 || 2 % 3 == 1){
    print("true");
  } 

  // 算术运算符
  // 加法
  print(2 + 3);
  // 减法
  print(4 - 1);
  print(4 - 5);
  // 乘法
  print(3 * 6);
  // 除法
  print(1 / 2);
  // 除法（只取整数）
  print(9 ~/ 2);
  // 取反
  print(- (1 - 4));
  // 取余
  print(9 % 2);
  // 自增操作
  var a = 1;
  print(++a);
  print(a);
  print(a++);
  print(a);

  // 关系运算符
  // 相等运算符
  print((2 + 3) == (3 + 2));
  // 不等运算符
  print((1 + 4) != (6 - 1));
  // 大于运算符
  print((1 + 4) > (6 - 1));
  // 大于等于运算符
  print((1 + 4) >= (6 - 1));
  // 小于运算符
  print((1 + 4) > (6 - 1));
  // 小于等于运算符
  print((1 + 4) >= (6 - 1));

  // 类型判断运算符
  // 转换类型
  String stringObj = "I'm String";
  print((stringObj as Object).hashCode);
  // 判断是否是指定类型
  int intObj = 100;
  print(intObj is int);
  // 判断是否不是指定类型
  print(intObj is! String);

  // 赋值运算符
  var assignmentValue = 10;
  assignmentValue += 5;
  print(assignmentValue);

  // 逻辑运算符
  // 取反运算符
  print(!true);
  // 或运算符
  print(true || false);
  // 与运算符
  print(true && false);

  // 位操作运算符
  var bitValue = 0x22;
  var bitValueMask = 0x0f;
  // 按位与
  print((bitValue & bitValueMask) == 0x02);
  // 一元位补码
  print(~bitValue);
  // 按位或
  print((bitValue | bitValueMask) == 0x2f);
  // 按位异或
  print((bitValue ^ bitValueMask) == 0x2d);
  // 左移位
  print((bitValue << 4) == 0x220);
  // 右移位
  print((bitValue >> 4) == 0x02);

  // 条件表达式
  var conditionBool = true;
  print(conditionBool? 'true': 'false');
  conditionBool = false;
  print(conditionBool?? 'careful it is null');

  // 级联操作符
  // Point pointExp = new Point();
  // pointExp.setX(10);
  // pointExp.setY(20);
  // pointExp.setZ(30);
  // print(pointExp.toString());
  print(new Point()..setX(10)..setY(20)..setZ(30)..toString());

}

  class Point {
    late num _x;
    late num _y;
    late num _z;
    void setX(num x){
      _x = x;
    }
    void setY(num y){
      _y = y;
    }
    void setZ(num z){
      _z = z;
    }
    @override
    String toString() {
      return 'x=$_x, y=$_y, z=$_z';
    }
  }