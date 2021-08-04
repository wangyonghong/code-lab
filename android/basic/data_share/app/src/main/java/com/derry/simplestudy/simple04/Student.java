package com.derry.simplestudy.simple04;

import android.os.Parcel;
import android.os.Parcelable;

// JVM平台 Java开发  Serializable

//  TODO 推荐的方式  Android 必须使用 Parcelable，因为这个是支持 兼容 安卓虚拟机   比Serializable 性能高很多
// 此对象成为 Parcelable 的子类，就具备传递的资格
public class Student implements Parcelable {

    public Student(){}

    // 我们自己定义的成员
    public String name;
    public int age;

    // TODO 注意： 读取的顺序 和 写入的顺序 必须一致，否则报错

    // MainActivity2 后读取
    // 从Parcel对象里面读出来 赋值给成员
    // 构造函数
    protected Student(Parcel in) {
        // 从Parcel对象里面读取成员 赋值给 name age
        name = in.readString();
        age = in.readInt();
    }

    // MainActivity1 先写入
    // 把属性写入到 Parcel 对象里面去
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    // 先不管，是系统扩展用的
    @Override
    public int describeContents() {
        return 0;
    }

    // CREATOR 一定要有  自动生成/从文档里面复制，不需要自己写
    public static final Creator<Student> CREATOR = new Creator<Student>() {

        // 创建Student对象 并且 Parcel构建好，传递给我们的Student（成员数据就可以从Parcel对象获取了）
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
