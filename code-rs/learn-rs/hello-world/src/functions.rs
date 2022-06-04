pub fn main() {
    a_function();
    b_function(5);
    c_function(5, 6);
    statement_expression();
    println!("five: {}", five());
    println!("5 + 1 = {}", plus_one(5));
}

fn a_function() {
    println!("Hello A!");
}

fn b_function(x: i32) {
    println!("x: {}", x);
}

fn c_function(x: i32, y: i32) {
    println!("x: {}, y: {}", x, y);
}

fn statement_expression() {
    let y = {
        let x = 3; // 语句
        x + 1 // 表达式
    };
    println!("y: {}", y);
}

fn five() -> i32 {
    // return 5;
    5
}

fn plus_one(x: i32) -> i32 {
    x + 1
}