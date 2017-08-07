package com.kuchi;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Examples of Singleton in Real Life
//Runtime - Singleton  -- Every thread able to see the changes done by others
//LogManager -- uniform logging
//CacheManager
//ResourceManager - DatabaseConnectoionPool

public class Singleton implements Serializable, Cloneable
{
    //private static Singleton soleInstance = new Singleton();
    private static Singleton soleInstance = null;

    private Singleton()
    {
        System.out.println("Creating..");
    }

    public static Singleton getSoleInstance()
    {
        if (soleInstance == null) {
            soleInstance = new Singleton();  //lazy initilization
        }
        return soleInstance;
    }

    @Override protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

class TestClass
{
    static void useSingleton()
    {
        Singleton singleton = Singleton.getSoleInstance();
        print("singleton", singleton);
    }

    public static void main(String[] args) throws Exception
    {
        //        Singleton s1 = Singleton.getSoleInstance();
        //        Singleton s2 = Singleton.getSoleInstance();
        //
        //        print("s1", s1);
        //        print("s2", s2);
        //
        //        Class clazz = Class.forName("com.kuchi.Singleton");
        //        Constructor<Singleton> ctor = clazz.getDeclaredConstructor();
        //        ctor.setAccessible(true);
        //        Singleton s3 = ctor.newInstance();
        //        print("s3", s3);
        //
        //        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/s2.ser"));
        //        oos.writeObject(s2);
        //
        //
        //        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/s2.ser"));
        //        Singleton s4 = (Singleton) ois.readObject();
        //        print("s4", s4);
        //        Singleton s5 = (Singleton) s2.clone();
        //        print("s5", s5);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestClass::useSingleton);
        service.submit(TestClass::useSingleton);
        service.shutdown();


    }

    static void print(String name, Singleton object)
    {
        System.out.println(String.format("Object: %s, Hashcode: %d", name, object.hashCode()));
    }
}
