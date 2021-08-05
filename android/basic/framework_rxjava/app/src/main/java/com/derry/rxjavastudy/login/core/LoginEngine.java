package com.derry.rxjavastudy.login.core;

import com.derry.rxjavastudy.login.bean.ResponseResult;
import com.derry.rxjavastudy.login.bean.SuccessBean;

import io.reactivex.Observable;

public class LoginEngine {

    // 返回 起点
    public static Observable<ResponseResult> login(String name, String pwd) {

        // 最终返回 总Bean
        ResponseResult responseResult = new ResponseResult();

        if ("Derry".equals(name) && "123456".equals(pwd)) { // 登录成功

            // 创建 成功 Bean
            SuccessBean successBean = new SuccessBean();
            successBean.setId(45674565);
            successBean.setName("Derry登录成功");

            /**
             * {
             *   data:{ xxxxxxx 登录成功的Bean xxxxxxx  SuccesBean   成功Bean }
             *   code:200
             *   message:"登录成功"
             * }
             */
            responseResult.setData(successBean);
            responseResult.setCode(200);
            responseResult.setMessage("登录成功");

        } else { // 登录失败

            /**
             * {
             *   data:null
             *   code:404
             *   message:"登录错误"
             * }
             */
            responseResult.setData(null);
            responseResult.setCode(404);
            responseResult.setMessage("登录错误");
        }

        // 起点
        return Observable.just(responseResult);
    }

}
