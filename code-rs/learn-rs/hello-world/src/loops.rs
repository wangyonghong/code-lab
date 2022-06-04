pub fn main() {
    _loop();
    _while();
    _for();
}

fn _loop() {
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };
    println!("counter = {}", result);
}

fn _while() {
    let mut counter = 0;
    while counter != 10 {
        counter += 1;
    };
    println!("counter = {}", counter);

    let a = [1, 2, 3, 4, 5];
    let mut index = 0;
    while index < 5 {
        println!("{}", a[index]);
        index = index + 1;
    }
}

fn _for() {
    let a = [1, 2, 3, 4, 5];
    for x in a {
        println!("{}", x);
    }

    println!("倒序");
    for x in (1..4).rev() {
        println!("{}", x);
    }
}
