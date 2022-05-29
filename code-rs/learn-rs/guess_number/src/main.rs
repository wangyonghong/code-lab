use std::cmp::Ordering;
use std::io;
use rand::Rng;

fn main() {
    println!("Guess the number!");
    let secret_number = rand::thread_rng().gen_range(1..101);

    // loop关键字会创建一个无限循环
    loop {
        println!("please input your guess.");
        // 使用mut关键字来声明一个变量是可变的
        // ::语法表明new是String类型的一个关联函数（associated function）。
        // 我们会针对类型本身来定义关联函数，
        // 比如本例中的String，而不会针对String的某个特定实例。
        // 关联函数在某些语言中也被称为静态方法（static method）
        let mut guess = String::new();

        // 参数前面的&意味着当前的参数是一个引用
        io::stdin().read_line(&mut guess).expect("Failed to read line!");

        // 普通处理
        // let guess: u32 = guess.trim().parse().expect("Please type a number!");

        // 异常后继续循环
        // 使用了match表达式来替换之前的expect方法，这是我们处理错误行为的一种惯用手段
        let guess: i32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => continue
        };
        println!("You guessed: {}", guess);
        match guess.cmp(&secret_number) {
            Ordering::Less => println!("Too small!"),
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal => {
                println!("You win!");
                break;
            }
        }
    }
}
