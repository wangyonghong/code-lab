import 'Person.dart';
import 'Person_2.dart';

main(List<String> args) {
  // 类的实例化
  var alice = new Person('alice', 25, 'female');
  print(alice.toString());
  print(alice.age);
  print(alice.runtimeType);

  // getter()和setter()
  print(alice.getAgeGrades);
  
  // 静态变量
  // alice.notice;
  Person.notice;

  // 使用命名构造方法
  var me = new Person.myself(28);
  print(me.toString());

  // 调用父类构造函数
  var studentPerson = new Student.myself(16);
  if (studentPerson is Person) {
    studentPerson.name = "Student";
  }
  print(studentPerson.toString());

  // 初始化列表
  var noAgeInfo = Person.withoutAge("Bob", "male");
  print(noAgeInfo.toString());

  // 重定向构造函数
  var easyGenderTest = new Person.easyGender("Cindy", "30", 1);
  print(easyGenderTest.toString());

  // 常量构造函数
  var whoIam = Myself.me;
  print(whoIam.toString());
  // whoIam.age = 22;

  // 工厂方法的构造方法
  Person_2 sayHello = new Person_2("David");
  sayHello.say("Hello!");
  print(sayHello.hashCode);
  Person_2 sayHello_2 = new Person_2("David");
  print(sayHello_2.hashCode);
  Person_2 sayHello_3 = new Person_2("Elan");
  print(sayHello_3.hashCode);

  // 静态方法
  print(Person_2.readme());

  // 复写运算符
  var a = new Multi2ItemOperator(10, 20);
  var b = new Multi2ItemOperator(30, 40);
  print((a * b).x);
  print((a * b).y);
}

// 复写运算符
class Multi2ItemOperator {
  final int x;
  final int y;

  const Multi2ItemOperator(this.x, this.y);

  Multi2ItemOperator operator *(Multi2ItemOperator multi2ItemOperator) {
    return new Multi2ItemOperator(x * multi2ItemOperator.x, y * multi2ItemOperator.y);
  }
}