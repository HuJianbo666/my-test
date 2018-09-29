package jdk.io.serializable;

import java.io.*;

/**
 * Serializable 序列反序列demo
 * <p>
 * Created by hujianbo on 2018/3/io1.
 */
public class SerializableTest {

    /**
     * 序列化
     */
    public static void writeSerializableObject() {
        ObjectOutputStream oos = null;
        try {
            Man man = new Man();
            man.setId(1);
            man.setName("name1");
            man.setPhone("123123123");

            oos = new ObjectOutputStream(new FileOutputStream("Out.txt"));
            oos.writeObject("string");
            oos.writeObject(man);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化
     */
    public static void readSerializableObject() {

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Out.txt"));
            String str = (String) ois.readObject();
            Man man = (Man) ois.readObject();

            System.out.println(str + ":" + man);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SerializableTest.writeSerializableObject();

        SerializableTest.readSerializableObject();
        //结果
        //string:Man{id=io1, name='name1', phone='null'}
        //属性加上transient，不序列
    }
}
