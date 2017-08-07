package com.kuchi;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Examples of Singleton in Real Life
//Runtime - Singleton -- Every thread able to see the changes done by others
//LogManager -- uniform logging
//CacheManager
//ResourceManager - DatabaseConnectoionPool

public class SingletonFix implements Serializable, Cloneable
{
    //private static SingletonFixsoleInstance = new Singleton();
    private static volatile SingletonFix soleInstance = null;

    //volatile write happens before read
    //it will not publish state  untill the object is fullly constructed

    private SingletonFix()
    {
        if (soleInstance != null) {
            System.out.println("reflection fixed");
            throw new RuntimeException("Cannot create, please use getInstance");
        }
        System.out.println("Creating..");
    }

    //    public static  SingletonFix getSoleInstance()
    //    {
    //        if (soleInstance == null) {
    //            synchronized (SingletonFix.class) {
    //                if (soleInstance == null) //double checked locking idiom
    //                soleInstance = new SingletonFix();  //lazy initilization
    //            }
    //
    //        }
    //        return soleInstance;
    //    }

    public static SingletonFix getSoleInstance()
    {
        return Holder.Instance;
    }

    @Override protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    private Object readResolve() throws ObjectStreamException
    {
        System.out.println("... read resolve ...");
        return soleInstance;
    }

    static class Holder
    {

        static final SingletonFix Instance = new SingletonFix();
    }
}

class TestClass1
{
    static void useSingleton()
    {
        SingletonFix singleton = SingletonFix.getSoleInstance();
        print("singleton", singleton);
    }

    public static void main(String[] args) throws Exception
    {
        //        Class clazz = Class.forName("com.kuchi.SingletonFix");
        //        Constructor<SingletonFix> ctor = clazz.getDeclaredConstructor();
        //        ctor.setAccessible(true);
        //        SingletonFix s3 = ctor.newInstance();
        //        print("s3", s3);

        //        SingletonFix s1 = SingletonFix.getSoleInstance();
        //        SingletonFix s2 = SingletonFix.getSoleInstance();
        //
        //        print("s1", s1);
        //        print("s2", s2);

        //        Class clazz = Class.forName("com.kuchi.SingletonFix");
        //        Constructor<SingletonFix> ctor = clazz.getDeclaredConstructor();
        //        ctor.setAccessible(true);
        //        SingletonFix s3 = ctor.newInstance();
        //        print("s3", s3);

        //        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/s2.ser"));
        //        oos.writeObject(s2);
        //
        //
        //        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/s2.ser"));
        //        SingletonFix s4 = (SingletonFix) ois.readObject();
        //        print("s4", s4);
        //        SingletonFix s5 = (SingletonFix) s2.clone();
        //        print("s5", s5);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestClass1::useSingleton);
        service.submit(TestClass1::useSingleton);
        service.shutdown();


    }

    static void print(String name, SingletonFix object)
    {
        System.out.println(String.format("Object: %s, Hashcode: %d", name, object.hashCode()));
    }
}
