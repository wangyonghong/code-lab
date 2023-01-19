package me.yonghong.patterns.structural.composite;

/**
 * 组合模式（Composite Pattern），又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。
 * 组合模式依据树形结构来组合对象，用来表示部分以及整体层次。
 *
 * @author yonghongwang#163.com
 * @link <a href="https://java-design-patterns.com/patterns/composite/"></a>
 * @link <a href="https://github.com/apache/wicket"></a>
 * @see java.awt.Container
 * @see java.awt.Component
 * @since 2023/1/7
 */
public class App {

    public static void main(String[] args) {
        var orcMessage = new Messenger().messageFromOrcs();
        orcMessage.print(); // Where there is a whip there is a way.
        var elfMessage = new Messenger().messageFromElves();
        elfMessage.print(); // Much wind pours from your mouth.
    }
}
