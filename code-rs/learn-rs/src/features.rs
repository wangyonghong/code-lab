pub fn log_feature(feature: i32) {
    for i in 0..19 {
        println!("1 << {} = {}", i, feature >> i & 1)
    }
}
