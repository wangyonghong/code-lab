package me.yonghong.futuretask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskManager {

  private static final ExecutorService executor = new ThreadPoolExecutor(10, 15,
      1000L, TimeUnit.SECONDS, new PriorityBlockingQueue<>(15), new ThreadFactory() {
    @Override
    public Thread newThread(Runnable runnable) {
      return new Thread("task");
    }
  });

  public static Future<?> submit(Runnable runnable) {
    return executor.submit(runnable);
  }
}
