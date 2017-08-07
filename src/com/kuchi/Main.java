package com.kuchi;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum SingletonENUM
{
    INSTANCE;
    //    public String getConfiguration() {
    //        return  "asdfasd";
    //    }
}

public class Main
{

    static void useSingleton()
    {
        SingletonENUM singleton = SingletonENUM.INSTANCE;
        print("singleton", singleton);
    }

    public static void main(String[] args) throws Exception
    {

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(Main::useSingleton);
        service.submit(Main::useSingleton);
        service.shutdown();


    }

    static void print(String name, SingletonENUM object)
    {
        System.out.println(String.format("Object: %s, Hashcode: %d", name, object.hashCode()));
    }
}
