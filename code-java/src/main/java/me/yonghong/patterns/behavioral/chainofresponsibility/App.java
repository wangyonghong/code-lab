package me.yonghong.patterns.behavioral.chainofresponsibility;

/**
 * Chain of responsibility
 * 责任链模式
 *
 * @author yonghongwang#163.com
 * @link <a href="https://java-design-patterns.com/patterns/chain-of-responsibility/"></a>
 * @see java.util.logging.Logger
 * @see android.view.View#dispatchTouchEvent
 * @since 2021/8/6
 */
public class App {

    public static void main(String[] args) {
        var king = new OrcKing();
        king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle"));
        king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner"));
        king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax"));
    }
}
