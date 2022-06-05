main(List<String> args) {
  var a = 1;
  var sum = 0;
  while(a <= 200){
    if(a % 3 == 0){
      sum = sum + a;
    }
    a++;
  }
  print("1~200之间所有3的倍数之和为: $sum");
}