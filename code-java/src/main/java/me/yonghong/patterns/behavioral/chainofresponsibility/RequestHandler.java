package me.yonghong.patterns.behavioral.chainofresponsibility;

public interface RequestHandler {

    boolean canHandleRequest(Request req);

    int getPriority();

    void handle(Request req);

    String name();
}
