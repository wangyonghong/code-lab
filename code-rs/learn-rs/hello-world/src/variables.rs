pub fn main() {
    println!("Hello, world!");
    mutable();
    shadow();
    scalar();
    compound();
}

fn mutable() {
    let mut x = 5;
    println!("The value of x is: {}", x);
    x = x + 1;
    println!("The value of x is: {}", x);
}

fn shadow() {
    let x = 5;
    println!("The value of x is: {}", x);
    let x = x + 1;
    println!("The value of x is: {}", x);
    let x = x * 1;
    println!("The value of x is: {}", x);

    let spaces = "  ";
    let spaces = spaces.len();
    println!("spaces: {}", spaces)
}

fn scalar() {
    // 加 _ 可以去除 warning: unused variable: `x`
    let _x: u32 = 32;
    let _x: u32 = 32u32;
    let _x: u64 = 64;
    let _x: u64 = 64u64;
    let _x: f64 = 32.32;
    let _x: f32 = 32.32f32;
    let _x: f64 = 32.32f64;

    let _sum = 5 + 6;
    let _diff = 95.1 - 6.2;
    let _product = 4 * 30;
    let _quotient = 57.3 / 223.3;
    let _remainder = 43 % 5;

    let _t = true;
    let _f = false;

    let _c = 'z';
    let z = '😄';
    println!("哈哈哈: {}", z)
}

fn compound() {
    // tuple
    let _tup: (i32, f64, u8) = (500, 6.4, 1);
    let tup = (500, 6.4, 1);
    // 通过索引并使用点号（.）访问元组中的值
    println!("x: {}, y: {}, z: {}", tup.0, tup.1, tup.2);
    // 将tup拆分为3个不同的部分：x、y和z，这个操作也被称为解构（destructuring）
    let (x, y, z) = tup;
    println!("x: {}, y: {}, z: {}", x, y, z);

    // array
    let _a = [1, 2, 3, 4, 5];
    let _a: [i32; 5] = [1, 2, 3, 4, 5];
    let _a = [3, 3, 3, 3, 3];
    let a = [3; 5];
    println!("{}, {}, {}", a[0], a[2], a[4]);

    let _months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
}