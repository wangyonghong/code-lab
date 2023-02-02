# Android 全埋点解决方案

// @formatter:off

## 概述

全埋点，也叫无埋点、无码埋点、无痕埋点、自动埋点。全埋点是指无须Android应用程序开发工程师写代码或者只写少量的代码，就能预先自动收集用户的所有行为数据，然后就可以根据实际的业务分析需求从中筛选出所需行为数据并进行分析。
全埋点采集的事件目前主要包括以下四种（事件名称前面的$符号，是指该事件是预置事件，与之对应的是自定义事件）。

- $AppStart事件
是指应用程序启动，同时包括冷启动和热启动场景。热启动也就是指应用程序从后台恢复的情况。
- $AppEnd事件
是指应用程序退出，包括应用程序的正常退出、按Home键进入后台、应用程序被强杀、应用程序崩溃等场景。
- $AppViewScreen事件
是指应用程序页面浏览，对于Android应用程序来说，就是指切换Activity或Fragment。
- $AppClick事件
是指应用程序控件点击，也即View被点击，比如点击Button、ListView等。

在采集的这四种事件当中，最重要并且采集难度最大的是$AppClick事件。所以，全埋点的解决方案基本上也都是围绕着如何采集$AppClick事件来进行的。

对于$AppClick事件的全埋点整体解决思路，归根结底，就是要自动找到那个被点击的控件处理逻辑（后文统称原处理逻辑），然后再利用一定的技术原理，对原处理逻辑进行“拦截”，或者在原处理逻辑的执行前面或执行者后面“插入”相应的埋点代码逻辑，从而达到自动埋点的效果。

至于如何做到自动“拦截”控件的原处理逻辑，一般都是参考Android系统的事件处理机制来进行的。 至于如何做到自动“插入”埋点代码逻辑，基本上都是参考编译器对Java代码的整体处理流程来进行的，即：

```
JavaCode --> .java --> .class --> .dex
```

选择在不同的处理阶段“插入”埋点代码，所采用的技术或者原理也不尽相同，所以全埋点的解决方案也是多种多样的。

### Android View 类型

- Button
- SeekBar
- TabHost
- RatingBar
- CheckBox、SwitchCompat、RadioButton、ToggleButton、RadioGroup
- Spinner
- MenuItem
- ListView、GridView
- ExpandableListView
- Dialog（普通的Dialog、展示列表的Dialog）

### View 绑定 listener 的方式

- 通过显式代码来设置 listenr
- 通过 android:onClick 属性绑定 listener
- 通过注解绑定 listener，如 ButterKnife [https://github.com/JakeWharton/butterknife](https://github.com/JakeWharton/butterknife)
- listener 含有 lambda 语法
- 通过 Data Binding 绑定 listener [Android 官方 Data Binding 使用说明](https://developer.android.com/topic/libraries/data-binding)

## $AppViewScreen全埋点方案

### 原理

ActivityLifecycleCallbacks 是 Application 的一个内部接口，是从API 14（即 Android 4.0）开始提供的。Application 类通过此接口提供了一系列的回调方法，用于让开发者可以对 Activity 的所有生命周期事件进行集中处理（或称监控）。我们可以通过 Application 类提供的 registerActivityLifecycleCallback(ActivityLifecycleCallbacks callback) 方法来注册 ActivityLifecycleCallbacks 回调。

在应用程序自定义的 Application 类的 onCreate() 方法中初始化埋点SDK，并传入当前的 Application 对象。埋点 SDK 拿到 Application 对象之后，通过调用 Application 的 registerActivityLifecycleCallback(ActivityLifecycleCallbacks callback) 方法注册 Application.ActivityLifecycleCallbacks 回调。这样埋点SDK就能对当前应用程序中所有的 Activity 的生命周期事件进行集中处理（监控）了。在注册的 Application.ActivityLifecycleCallbacks的onActivityResumed(Activity activity) 回调方法中，我们可以拿到当前正在显示的 Activity 对象，然后调用SDK的相关接口触发页面浏览事件（$AppViewScreen）即可。

### 关键代码

```
application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
});
```

## $AppStart、$AppEnd 全埋点方案

### 原理


### 关键代码




