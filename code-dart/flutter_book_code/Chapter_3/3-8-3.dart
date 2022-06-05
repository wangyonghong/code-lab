main(List<String> args) {
  for(var a = 1; a <= 1000; a++){
    int num = a;
    if(isPerfectNum(num)){
      print(num);
    }
  }
}

bool isPerfectNum(var num){
  int sum = 0;
  for(int d = num-1; d >= 1; d--){
    if(num % d == 0){
      sum += d;
    }
  }
  return sum == num;
}