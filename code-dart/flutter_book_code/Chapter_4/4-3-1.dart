main(List<String> args) {
  var square = new Rectangle.square(1.5);
  print(square.perimeter());
  print(square.area());
  
  var rectangle = new Rectangle(2.5, 3.5);
  print(rectangle.perimeter());
  print(rectangle.area());
}

class Rectangle {
  late double edge_a;
  late double edge_b;

  Rectangle(this.edge_a, this.edge_b);

  Rectangle.square(double edgeForSquare){
    edge_a = edgeForSquare;
    edge_b = edgeForSquare;
  }

  double perimeter() {
    return edge_a * 2 + edge_b * 2;
  }

  double area(){
    return edge_a * edge_b;
  }

}