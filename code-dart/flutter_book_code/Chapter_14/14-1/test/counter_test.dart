import 'package:flutter_test/flutter_test.dart';
import 'package:demo_unit_test/counter.dart';

void main() {
  test('Counter value should be incremented', () {
    final counter = Counter();

    counter.increment();

    expect(counter.value, 1);
  });

  test('value should be decremented', () {
      final counter = Counter();

      counter.decrement();

      expect(counter.value, -1);
  });
}