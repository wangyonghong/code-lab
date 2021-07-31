package com.gupaoedu.vip.pattern.proxy.staticproxy;

import com.gupaoedu.vip.pattern.proxy.Person;

/**
 * Created by Tom on 2019/3/10.
 */
public class Father implements Person {
    private Son son;

    public Father(Son son){
        this.son = son;
    }

    public void findLove(){
        System.out.println("父亲物色对象");
        this.son.findLove();
        System.out.println("双方父母同意，确立关系");
    }

    public void findJob(){

    }


}
