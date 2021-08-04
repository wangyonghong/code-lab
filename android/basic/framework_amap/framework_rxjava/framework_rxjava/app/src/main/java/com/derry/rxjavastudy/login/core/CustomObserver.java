package com.derry.rxjavastudy.login.core;


import com.derry.rxjavastudy.login.bean.ResponseResult;
import com.derry.rxjavastudy.login.bean.SuccessBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CustomObserver implements Observer<ResponseResult> {

    public abstract void success(SuccessBean successBean);
    public abstract void error(String message);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseResult responseResult) {
        if (responseResult.getData() == null) {
            error(responseResult.getMessage() + "请求失败，请Derry老师检查日志..");
        } else {
            success(responseResult.getData());
        }
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage() + "请Derry老师检查日志，错误详情");
    }

    @Override
    public void onComplete() {

    }
}
