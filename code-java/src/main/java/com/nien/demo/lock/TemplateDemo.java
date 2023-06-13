package com.nien.demo.lock;

import com.nien.demo.util.Print;

public class TemplateDemo {

    static abstract class AbstractAction {
        /**
         * 模板方法：算法骨架
         */
        public void tempMethod() {
            Print.cfo("模板方法的算法骨架被执行");
            beforeAction();
            action();
            afterAction();
        }

        /**
         * 执行前
         */
        protected void beforeAction() {
            Print.cfo("准备执行钩子方法");
        }


        /**
         * 钩子方法
         */
        public abstract void action();

        /**
         * 执行后
         */
        private void afterAction() {
            Print.cfo("钩子方法执行完成");

        }
    }

    static class ActionA extends AbstractAction {
        /**
         * 钩子方法
         */
        @Override
        public void action() {
            Print.cfo("钩子方法的实现 ActionA.action() 被执行");
        }
    }

    static class ActionB extends AbstractAction {
        /**
         * 钩子方法
         */
        @Override
        public void action() {
            Print.cfo("钩子方法的实现 ActionB.action() 被执行");
        }
    }

    public static void main(String[] args) {
        AbstractAction action = null;

        //创建一个 ActionA 实例
        action = new ActionA();
        //执行基类的模板方法
        action.tempMethod();

        //创建一个 ActionB 实例
        action = new ActionB();
        //执行基类的模板方法
        action.tempMethod();


    }
}
