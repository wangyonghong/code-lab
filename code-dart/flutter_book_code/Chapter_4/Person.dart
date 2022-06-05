class Person {

  // 实例变量
  var name;
  var age;
  var gender;
  var ageGrades;

  // 类变量
  static var notice = "Only for test";

  // 构造方法
  Person(this.name, this.age, this.gender);

  Person.myself(var age){
    name = "Wenhan.Xiao";
    this.age = age;
    this.gender = "male";
  }

  Person.withoutAge(var name, var gender) : 
    this.name = name,
    this.gender = gender {
      print(this.name);
      print(this.gender);
    }

  Person.easyGender(var name, var age, var gender) : 
    this(name, age, gender == 0? "male" : "female" );  

  // 自定义gettter()
  String get getAgeGrades{
    if(age < 0){
      ageGrades = "非法数据";
    } else if (age < 44){
      ageGrades = "青年期";
    } else if (age < 59){
      ageGrades = "中年期";
    } else if (age < 74){
      ageGrades = "年轻老人";
    } else if (age < 89){
      ageGrades = "老年人";
    } else if (age > 89){
      ageGrades = "长寿老人";
    } 
    return ageGrades;
  }

  @override
  String toString() {
    return "name: $name, age: $age, gender: $gender";
  }
}

// Person类的子类
class Student extends Person {
  Student.myself(var age) : super.myself(age) {
    print("I'm a student, $age years old.");
  }
}

// 常量构造函数
class Myself {
  final String name;
  final num age;
  final String gender;

  const Myself(this.name, this.age, this.gender);
  static final Myself me = const Myself("Wenhan", 28, "male");

  @override
  String toString() {
    return "name: $name, age: $age, gender: $gender";
  }
}