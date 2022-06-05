
var totalJob;

main(List<String> args) {
  print("程序开始 " + new DateTime.now().toString());
  totalJob = 0;
  job_1();
  print("程序结束 " + new DateTime.now().toString());
}

job_1() async {
  for(int i = 0; i < 3; i++) {
    print('任务一开始');
    print(await new Future.delayed(Duration(seconds: 2), () => "任务一结束"));
  }
  job_2();
}

job_2() async {
  for(int i = 0; i < 5; i++) {
    print('任务二开始');
    print(await new Future.delayed(Duration(seconds: 1), () => "任务二结束"));
  }
  totalJob++;
  if (totalJob < 3){
    job_1();
  }
}