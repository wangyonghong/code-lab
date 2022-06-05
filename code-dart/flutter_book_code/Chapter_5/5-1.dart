import 'ALib.dart' as plus hide Useless;
import 'BLib.dart' deferred as multiply;
import 'dart:io';

typedef StringOutput = String Function(String str);
typedef IntOutput = int Function(int number);

main(List<String> args) {

  // 使用库
  var plusCalc = new plus.Calc();

  // 库的懒加载
  doMultiply();

  // 异步处理
  doComplexJob();
  print('后面的逻辑' + new DateTime.now().toString());

  // 可调用的类
  var runnableClassTest = new RunnableClass();
  print(runnableClassTest('测试文本'));

  // 类型定义
  TypedefClass typedefClassTest = new TypedefClass(output);
  assert(typedefClassTest.exampleFunction is Function);
  assert(typedefClassTest.exampleFunction is StringOutput);
  // assert(typedefClassTest.exampleFunction is IntOutput);

  // 元数据
  getCurrentTime();

  // 单行注释

  /*
  这是一个多行注释
  这是一个多行注释
  这是一个多行注释
  这是一个多行注释
  */

  ///这是文档注释
  ///请参阅 [print] 方法
  ///这是文档注释
}

// 库的懒加载
void doMultiply() async {
  await multiply.loadLibrary();
  multiply.Calc.readMe();
  var multiplyCalc = new multiply.Calc();
  print(multiplyCalc.multiplyNum(2));
}

// 异步处理
doComplexJob() async {
  print("耗时操作开始于："  + new DateTime.now().toString());
  print(await new Future.delayed(Duration(seconds: 2), () => "耗时操作结束于：" + new DateTime.now().toString()));
}

// 可调用的类
class RunnableClass {
  call(var stringContent) => "输入了 $stringContent";
}

// 类型定义
class TypedefClass {

  late Function exampleFunction;

  TypedefClass(String test(String str)) {
    exampleFunction = test;
  }
}

String output(String str) => "输入了 $str";

int outputInt(int number) => number;

// 元数据
@deprecated
/// 直接使用 new DateTime.now();
int getCurrentTime(){
  return DateTime.january;
}