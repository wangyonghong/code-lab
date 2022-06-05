class Person_2 {
  final String name;

  static final Map<String, Person_2> _cache = new Map<String, Person_2>();

  factory Person_2(String name) {
    final cached = _cache[name];
    if (cached != null) {
      return cached;
    } else {
      final person = new Person_2.newPerson(name);
      _cache[name] = person;
      return person;
    }
  }

  Person_2.newPerson(this.name);

  void say(String content) {
    print(content);
  }

  // 静态方法
  static String readme() {
    return "我是一个工厂方法的构造方法的应用举例。";
  }
}
