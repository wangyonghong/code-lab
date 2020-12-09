package me.yonghong.algo.hug.sort;

public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] nums);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected void print(T[] nums) {
        for (T t : nums) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}
