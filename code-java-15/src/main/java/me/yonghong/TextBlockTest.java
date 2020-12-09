package me.yonghong;

/**
 * @author yonghongwang#163.com
 * @since 2020/12/9
 **/
public class TextBlockTest {

    public static void main(String[] args) {
        String text = """
                The Sound of Silence
                寂静之声
                Hello darkness, my old friend
                你好 黑暗 我的老朋友
                I've come to talk with you again
                我又来和你交谈
                Because a vision softly creeping
                因为有一种幻觉正向悄悄地向我袭来
                Left its seeds while I was sleeping
                在我熟睡的时候留下了它的种子
                And the vision that was planted in my brain
                这种幻觉在我的脑海里生根发芽
                Still remains
                缠绕着我
                Within the sound of silence 取消换行 \
                伴随着寂静的声音\s空格
                """;
        System.out.println(text);
    }
}
