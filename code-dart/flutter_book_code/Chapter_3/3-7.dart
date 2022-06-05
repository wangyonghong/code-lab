main(List<String> args) {

  // 抛出一个已知类型的异常
  throw new FormatException("Data format exception occurred");
  print('test');
  
  // 抛出一个自定义的异常
  throw 'Custom exception';

  // 捕捉异常
  var intArray = [10, 20, 30, 40, 50];
  try {
    print(intArray[5]);
  } on RangeError{
    print(intArray[4]);
    // rethrow;
  } on FormatException{
     print("FormatException");
  } catch(e) {
    print(e.toString());
  }

  // Finally语句
  try {
    print(intArray[4]);
    // print(intArray[5]);
  } catch (e){
    print(e.toString());
  } finally {
    print("程序运行结束");
  }

}