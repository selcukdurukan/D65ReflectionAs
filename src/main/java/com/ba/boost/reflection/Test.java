package com.ba.boost.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    /*
     * 1. constructor reflection
     * 2. field reflection
     * 3. method reflection
     * */

    /*
    * Test için test directory'nin içine bak.
    * */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException {


        Class<?> birdClass = Class.forName("com.ba.boost.reflection.Bird");

        Constructor<?>[] birdConstructor = birdClass.getDeclaredConstructors();

        /*
         * var' atadığımız değere göre değer alır aynı javascript'de ki gibi. Java 5'den sonra geldi.
         *
         * var a = 1; -> int
         * var b = "Ali"; -> String
         * var c = 'c'; -> char
         * */

        //Public olan constructor'ları getirir.
        for (var cons : birdConstructor) {
            System.out.println(cons.getName());
        }

        System.out.println("----------------------------------------------------------------------");

        Constructor<?>[] birdDeclaredConstructor = birdClass.getDeclaredConstructors();

        //Tüm declare edilmiş olan, public veya private  constructor'ları getirir.
        for (Constructor c : birdDeclaredConstructor) {
            System.out.println(c.getName());
        }


        System.out.println("----------------------------------------------------------------------");

        //Kalıtım aldıgı üst sınıftan ve kendisinden sadece görünür olan (public vs.) fiedları getirir.
        Field[] birdField = birdClass.getFields();

        for (Field f : birdField) {
            System.out.println(f.getName());
        }

        System.out.println("----------------------------------------------------------------------");

        //Sadece kendi sınıfında olan, tüm declare edilmiş, public veya private field'ları getirir.
        Field[] birdDeclaredField = birdClass.getDeclaredFields();

        for (var c : birdDeclaredField) {
            System.out.println(c.getName());
        }

        System.out.println("----------------------------------------------------------------------");

        //Kalıtım aldıgı üst sınıftan ve kendisinden sadece görünür olan (public vs.) method'ları getirir.
        Method[] birdMethod = birdClass.getMethods();

        for (Method m : birdMethod) {
            System.out.println(m.getName());
        }

        System.out.println("----------------------------------------------------------------------");

        //Sadece kendi sınıfında olan, tüm declare edilmiş, public veya private method'ları getirir.
        Method[] birdDeclareMethod = birdClass.getDeclaredMethods();

        for (Method m : birdDeclareMethod) {
            System.out.println(m.getName());
        }


        System.out.println("----------------------------------------------------------------------");

        //Bird nesenesinin default false olan walks field'ını true'ya çevirelim.
        Bird bird = new Bird();

        Field[] bird1Field = birdClass.getDeclaredFields();

        System.out.println(bird.isWalks());

        for (Field f : bird1Field) {
            if (f.getName().equals("walks")) {
                f.setAccessible(true);
                f.set(bird, true);
            }
        }

        System.out.println(bird.isWalks());


    }
}
