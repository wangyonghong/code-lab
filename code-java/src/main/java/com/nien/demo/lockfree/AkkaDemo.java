package com.nien.demo.lockfree;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class AkkaDemo {
    //创建命令对象
    @Data
    @AllArgsConstructor
    static class Command implements Serializable {
        private static final long serialVersionUID = 1L;
        private String data;
    }

    //创建Actor对象
    static class SimpleActor extends UntypedActor {

        LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        public SimpleActor() {
            log.info("SimpleActor constructor");
        }

        @Override
        public void onReceive(Object msg) throws Exception {

            log.info("Received Command: " + msg);
            if (msg instanceof Command) {
                final String data = ((Command) msg).getData();
                // emmit an event somewhere...

            } else if (msg.equals("echo")) {
                log.info("ECHO!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(SimpleActor.class), "simple-actor");

        actorRef.tell(new Command("CMD 1"), null);
        actorRef.tell(new Command("CMD 2"), null);
        actorRef.tell(new Command("CMD 3"), null);
        actorRef.tell(new Command("CMD 4"), null);
        actorRef.tell(new Command("CMD 5"), null);

        Thread.sleep(5000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.shutdown();

    }
}
