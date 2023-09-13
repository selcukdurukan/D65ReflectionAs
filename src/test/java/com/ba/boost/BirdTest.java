package com.ba.boost;

import com.ba.boost.reflection.Animal;
import com.ba.boost.reflection.Bird;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Assert sınıfının içindeki tüm static methodlara ulaşabiliriz. Static importlar en alta koyulur.
import static org.junit.Assert.*;

public class BirdTest {

    /*
     *
     * Testler private olmz public olur.
     * Test'in yazılma amacı case'ler, olası senaryolar ile, projenin denenmesi.
     * Bu denemeye ek olarak, yapılacak sonra ki geliştirmelerde bu caselerin iyi bir şekilde çalışabilmesi.
     * Eğer case'lerden biri bile çalışmıyorsa yaptıgımız geliştirme projeye uygun değildir.
     * */

    @Test
    public void birdConstructor() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.ba.boost.reflection.Bird");
        Constructor<?>[] birdConstructor = birdClass.getConstructors();

        //3 constructor olması gerekir diye belirttik. Eğer 3 tane yoksa testten geçemeyiz.
        assertEquals(3, birdConstructor.length);
    }


    @Test
    public void birdDeclareField() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.ba.boost.reflection.Bird");

        Field[] birdDeclareField = birdClass.getDeclaredFields();
        assertEquals(1, birdDeclareField.length);

        Field[] birdField = birdClass.getFields();
        assertEquals(1, birdField.length);
    }


    private Class<?> getBirdClass() throws ClassNotFoundException {
        return Class.forName("com.ba.boost.reflection.Bird");
    }

    @Test
    public void birdConstructorParams() throws Exception {
        Class<?> birdClass = getBirdClass();

        Constructor<?> birdConstructor1 = birdClass.getConstructor();
        Constructor<?> birdConstructor2 = birdClass.getConstructor(boolean.class);
        Constructor<?> birdConstructor3 = birdClass.getConstructor(String.class, boolean.class);

        Bird bird1 = (Bird) birdConstructor1.newInstance();
        Bird bird2 = (Bird) birdConstructor2.newInstance(true);
        Bird bird3 = (Bird) birdConstructor3.newInstance("Cake", false);

        assertEquals("Bird", bird1.getName());
        assertEquals("Nice Bird", bird2.getName());
        assertEquals("Cake", bird3.getName());
        assertFalse(bird3.isWalks());

        //bird1 ve bird2 nin isWalk'ını test et.
        assertFalse(bird1.isWalks());
        assertTrue(bird2.isWalks());

    }

    @Test
    public void birdFieldTest() throws ClassNotFoundException {
        Class<?> birdClass = Class.forName("com.ba.boost.reflection.Bird");

        Field[] birdDeclareField = birdClass.getDeclaredFields();
        assertEquals("walks", birdDeclareField[0].getName());

        Field[] birdField = birdClass.getFields();
        assertEquals("BASE_NAME", birdField[0].getName());
    }

    @Test
    public void birdFieldTest2() throws Exception {

        Class<?> birdClass = getBirdClass();

        Bird bird = (Bird) birdClass.getConstructor().newInstance();
        bird.setWalks(false);
        assertFalse(bird.isWalks());

        Field birdFieldWalks = birdClass.getDeclaredField("walks");

        //Private oldugu için
        birdFieldWalks.setAccessible(true);
        birdFieldWalks.set(bird, true);
        assertTrue(bird.isWalks());

    }

    @Test
    public void nameTest() throws Exception {
        Class<?> birdClass = getBirdClass();

        Bird bird = new Bird();

        /*
        * Animal'ın BASE_NAME'ini not animal yaptık.
        * Bird'in parent class'ı oldugu için bird class'dan ulaşıp değiştirdik.
        * */
        Field baseNameField = birdClass.getField("BASE_NAME");
        assertEquals("Animal", Animal.getBaseName());

        //Bird obj'sinin animal BASE_NAME field'ına ulaştık.
        assertEquals("Animal", baseNameField.get(bird));


        Animal.setBaseName("Not animal");
        assertEquals("Not animal", Animal.getBaseName());

    }

    @Test
    public void birdMethod() throws Exception {
        Class<?> birdClass = getBirdClass();

        Method[] birdDeclareMethods = birdClass.getDeclaredMethods();

        List<String> methodNames = getMethodNames(birdDeclareMethods);

        assertTrue(methodNames.containsAll(Arrays.asList("setWalks", "isWalks", "eat", "getSounds", "secretMethod")));
    }

    private List<String> getMethodNames(Method[] birdDeclareMethods) {
        List<String> methodNames = new ArrayList<>();

        for (Method m: birdDeclareMethods) {
            methodNames.add(m.getName());
        }
        return methodNames;
    }


}
