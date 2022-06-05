main(List<String> args) {
  var intArray = [25, 32, 13, 48];
  for (var i = 0; i < intArray.length; i++) {
    var pos = i;
    for (var j = i + 1; j < intArray.length; j++) {
      if (intArray[pos] > intArray[j])
        pos = j;
    }
    if (pos != i) {
      intArray[i] = intArray[i] + intArray[pos];
      intArray[pos] = intArray[i] - intArray[pos];
      intArray[i] = intArray[i] - intArray[pos];
    }
  }
  print(intArray);
}