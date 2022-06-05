main(List<String> args) {
  var a = 3.6;
  var b = 7.5;
  print(new plusOperation().calc(a, b));
  print(new subtractionOperation().calc(a, b));
  print(new multiplicationOperation().calc(a, b));
  print(new divisionOperation().calc(a, b));
}

class numOperation {

  double calc(double x, double y) {
    return 0;
  }
} 

class plusOperation extends numOperation {
  @override
  double calc(double x, double y) {
    return x + y;
  }
}

class subtractionOperation extends numOperation {
  @override
  double calc(double x, double y) {
    return x - y;
  }
}

class multiplicationOperation extends numOperation {
  @override
  double calc(double x, double y) {
    return x * y;
  }
}

class divisionOperation extends numOperation {
  @override
  double calc(double x, double y) {
    return x / y;
  }
}