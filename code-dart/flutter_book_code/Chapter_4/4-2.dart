main(List<String> args) {
  new printTriangle().printUseChar("*");
}

// 具体的打印类
class printTriangle<T extends String> {
  void printUseChar(T char) {
    var str = "";
    for (var i = 0; i < 5; i++) {
      int j = 0;
      for (j = 0; j < 2 * i + 1; j++) {
          str += char;
      }
      str += "\n";
      print(str);
    }
  }
}