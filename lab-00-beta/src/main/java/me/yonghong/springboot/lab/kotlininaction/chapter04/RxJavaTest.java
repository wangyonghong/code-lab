package me.yonghong.springboot.lab.kotlininaction.chapter04;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;

/**
 * {@link CollectionTestKt}
 */
public class RxJavaTest {

    public static void main(String[] args) {
        final String[] a = new String[]{"4", "0", "7", "i", "f", "w", "0", "9"};
        final Integer[] index = new Integer[]{5, 3, 9, 4, 8, 3, 1, 9, 2, 1, 7};

        Observable.just(index)
                .flatMap(new Function<Integer[], ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer[] integers) throws Throwable {
                        return Observable.fromArray(integers);
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer i) throws Throwable {
                        return i < a.length;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        return a[integer];
                    }
                })
                .reduce(new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) throws Throwable {
                        return s + s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println("密码是" + s);
                    }
                });

        Observable.just(index)
                .flatMap((Function<Integer[], ObservableSource<Integer>>) Observable::fromArray)
                .filter(i -> i < a.length)
                .map(integer -> a[integer])
                .reduce((s, s2) -> s + s2)
                .subscribe(s -> System.out.println("密码是" + s));
    }
}
