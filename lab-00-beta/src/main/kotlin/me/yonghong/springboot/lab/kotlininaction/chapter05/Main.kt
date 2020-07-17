package me.yonghong.springboot.lab.kotlininaction.chapter05

// javap -c MainKt
//警告: 文件 ./MainKt.class 不包含类 MainKt
//Compiled from "Main.kt"
//public final class me.yonghong.springboot.lab.kotlininaction.chapter05.MainKt {
//    public static final void main(java.lang.String[]);
//    Code:
//    0: aload_0
//    1: ldc           #9                  // String args
//    3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkParameterIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
//    6: ldc           #17                 // String Hello Kotlin!
//    8: astore_1
//    9: iconst_0
//    10: istore_2
//    11: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
//    14: aload_1
//    15: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
//    18: return
//}
fun main(args: Array<String>) {
    println("Hello Kotlin!")
}