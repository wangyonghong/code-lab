main(List<String> args) {
  // if-else
  var volumeLevel = 7;
  if(volumeLevel < 0){
    print("音量值非法");
  } else if(volumeLevel < 3){
    print("低音量");
  } else if(volumeLevel < 7){
    print("中音量");
  } else if(volumeLevel <= 10){
    print("高音量");
  }
  
  // for循环
  var studentName = ['雁雁','婷婷','彤彤','雯雯'];
  for (var i = 0; i < studentName.length; i++){
    print(studentName[i]);
  }

  // for-in遍历Set
  Set setExp = {'Alice', 'Bob', 'Cindy', 'David', 123, 456, 7.89};
  for (var x in setExp) {
    print(x);
  }

  // while循环
  var i = 0;
  while (i < 100){
    i++;
  }
  print(i);

  // do-while循环
  var j = 100;
  do {
    j--;
  } while (j > 0);
  print(j);

  // break
  for (int i = 27; i < 100; i++){
    if (i % 26 == 0){
      print(i);
      break;
    }
  }

  // continue
  for (int i = 0; i < 100; i++){
    if (i % 10 != 0){
      continue;
    }
    print(i);
  }

  // switch-case
  var name = "雁雁";
  switch (name){
    case "雁雁":
      print("唇膏");
      break;
    case "婷婷":
    case "童童":
      print("精装书");
      break;
    case "雯雯":
      print("手表");
      break;
    default:
      print("不知道你是谁，不送了");
  }

  // assert
  var intValue = 300;
  assert(intValue == 299);

}