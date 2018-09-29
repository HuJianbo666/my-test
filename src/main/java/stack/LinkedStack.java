//package stack;
//
//import org.w3c.dom.Node;
//
//import java.io.Serializable;
//import java.util.EmptyStackException;
//
///**
// * @author Hu Jianbo
// * @date: 2018/5/16
// */
//public class LinkedStack<T> implements Stack<T>, Serializable {
//
//    private static final long serialVersionUID = 1911829302658328353L;
//
//    private Node<T> top;
//
//    private int size;
//
//    public LinkedStack() {
//        this.top = new Node<>();
//    }
//
//    public int size() {
//        return size;
//    }
//
//
//    @Override
//    public boolean isEmpty() {
//        return top == null || top.data == null;
//    }
//
//    @Override
//    public void push(T data) {
//        if (data == null) {
//            throw new StackException("data can\'t be null");
//        }
//        if (this.top == null) {//调用pop()后top可能为null
//            this.top = new Node<>(data);
//        } else if (this.top.data == null) {
//            this.top.data = data;
//        } else {
//            Node<T> p = new Node<>(data, this.top);
//            top = p;//更新栈顶
//        }
//        size++;
//    }
//
//    @Override
//    public T peek() {
//        if (isEmpty()) {
//            throw new EmptyStackException("Stack empty");
//        }
//
//        return top.data;
//    }
//
//    @Override
//    public T pop() {
//        if (isEmpty()) {
//            throw new EmptyStackException("Stack empty");
//        }
//
//        T data = top.data;
//        top = top.next;
//        size--;
//        return data;
//    }
//
//    //测试
//    public static void main(String[] args) {
//        LinkedStack<String> sl = new LinkedStack<>();
//        sl.push("A");
//        sl.push("B");
//        sl.push("C");
//        int length = sl.size();
//        for (int i = 0; i < length; i++) {
//            System.out.println("sl.pop->" + sl.pop());
//        }
//    }
//}