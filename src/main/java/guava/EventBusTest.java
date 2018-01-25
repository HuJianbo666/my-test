package guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * 发布订阅
 * Created by hujianbo on 2018/1/25.
 */
public class EventBusTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus("jack");
        eventBus.register(new EventListener1());
        eventBus.register(new EventListener2());
        eventBus.post(new OrderEvent("hello"));
        eventBus.post(new OrderEvent("guava"));
        eventBus.post("!");

    }

    //Guava 发布-订阅模式中传递的事件,是一个普通的POJO类
    private static class OrderEvent {  //事件
        private String message;

        public OrderEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private static class EventListener1 {

        /**
         * 如果你需要订阅某种类型的消息,只需要在指定的方法上加上@Subscribe注解即可
         *
         * @param message
         */
        @Subscribe
        public void listen(String message) {
            System.out.println("receive message: " + message);
        }

        @Subscribe
        public void listen(OrderEvent event) {
            System.out.println("receive message: " + event.getMessage());
        }
    }

    private static class EventListener2 {

        /**
         * 如果你需要订阅某种类型的消息,只需要在指定的方法上加上@Subscribe注解即可
         *
         * @param message
         */
        @Subscribe
        public void listen(String message) {
            System.out.println("receive message: " + message);
        }

        @Subscribe
        public void listen(OrderEvent event) {
            System.out.println("receive message: " + event.getMessage());
        }
    }
}

