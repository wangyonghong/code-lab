pub fn main() {
    let num = 3;
    if num < 5 {
        println!("true");
    } else {
        println!("false");
    }

    let condition = true;
    let num = if condition {
        5
    } else {
        6
    };

    println!("num = {}", num);
}