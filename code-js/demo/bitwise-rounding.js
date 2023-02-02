// 对浮点数取整数
const num1 = 1.2 | 0
const num2 = -1.2 | 0

console.log(`num1: ${num1}, num2: ${num2}`)

// 用 A ± 0.5 | 0 进行四舍五人
const num3 = 1.2 + 0.5 | 0
const num4 = -1.7 - 0.5 | 0

console.log(`num3: ${num3}, num4: ${num4}`)