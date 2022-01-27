package thread.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhuang.ma
 * @date 2022/1/25
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.offer("b");
        queue.offer("a");
        queue.offer("c");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        PriorityQueue<MessageObject> queue1 = new PriorityQueue<>(new Comparator<MessageObject>() {
            @Override
            public int compare(MessageObject o1, MessageObject o2) {
                return o1.order - o2.order;
            }
        });

        MessageObject m1 =   new  MessageObject("a",1);
        MessageObject m2 =    new  MessageObject("b",2);
        MessageObject m3 =    new  MessageObject("c",3);

        queue1.add(m1);
        queue1.add(m2);
        queue1.add(m3);
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());

    }

    static class MessageObject{
        String content;
        int order;

        public MessageObject(String content, int order) {
            this.content = content;
            this.order = order;
        }


    }
}
