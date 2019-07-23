package com.curtain.shardingjdbc;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author Curtain
 * @date 2019/4/23 16:34
 */
public class TestHash {

    @Test
    public void test() {

        String x = "51239";

        String s = x.intern();

        Integer a = 1;
        Integer aa = 1;
        Integer aaa = 128;
        Integer aaaa = 128;

        System.out.println(a == aa);
        System.out.println(aaa == aaaa);
        System.out.println(aaa.equals(aaaa));

        String y = new String("51239");

        boolean q = x == y;
        boolean p = x == s;

        System.out.println(x.hashCode());
        System.out.println(s.hashCode());
        System.out.println(y.hashCode());

        System.out.println(Integer.toBinaryString(x.hashCode()));

        int h;
        h = (x == null) ? 0 : (h = x.hashCode()) ^ (h >>> 16);
        System.out.println(Integer.toBinaryString(h));
        System.out.println(h);


        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(2);

        objectObjectHashMap.put("1", "1");
        objectObjectHashMap.put("2", "1");

        System.out.println(objectObjectHashMap);

    }


    @Test
    public void t2() {

        int i = 1 + (1 >> 1);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(list.size());
        ArrayList<Integer> objects2 = new ArrayList<>();

        list.add(1);

        list.add(2);
        list.add(3);
        list.add(4);

        Vector<Integer> integers = new Vector<>(list);
        Enumeration<Integer> elements = integers.elements();


        Integer[] aa = new Integer[]{
                1, 1, 1, 1, 5, 6, 7};

        //这种方式有bug  具体拷贝内容参考源码
        list.toArray(aa);

        System.out.println(aa);

        Iterator<Integer> iterator = list.listIterator();


        iterator.next();
        ((ListIterator<Integer>) iterator).add(1);
        iterator.next();
        iterator.remove();

        System.out.println(list);


        list.removeIf(o -> (o == null));

        objects2.add(1);

//        System.out.println(list.containsAll(1));

        list.forEach(e -> System.out.println(e));
    }


    @Test
    public void t3() {

        int h = 0;
        HashMap<Object, Object> map = new HashMap<>(17);

        map.put("xxx", "X");
        map.remove("xxx");
        map.put("x", "X");
        map.put("q", "X");
        map.put("xw", "X");
        map.put("ex", "X");

        map.get("x");

        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Set<Object> objects = map.keySet();


        List<String> strings = Arrays.asList("1", "2");

        StringBuffer stringBuffer = new StringBuffer("12345哈哈哈");

        stringBuffer = stringBuffer.reverse();

        StringBuffer xx = null;

        stringBuffer.append(xx);

        CharSequence charSequence = stringBuffer.subSequence(0, 1);

        char[] x = {'x', 'z', 'y'};

        int i = stringBuffer.codePointAt(2);

        int i1 = stringBuffer.codePointBefore(3);

        int count = stringBuffer.codePointCount(0, 3);

        int i2 = stringBuffer.offsetByCodePoints(0, 5);
        stringBuffer.append(new Integer(1));

        stringBuffer.getChars(0, 2, x, 1);

        System.out.println("Xx");


    }


    @Test
    public void t5() {

        try {
            Socket socket = new Socket("10.128.4.79", 20108);
            System.out.println(socket.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void t4() {


        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
        System.out.println(objects.remainingCapacity());
        objects.add(1);
        objects.add(1);
        objects.add(1);
        objects.add(1);
        System.out.println(objects.remainingCapacity());

        Supplier<TestHash> s = TestHash::new;
        TestHash testHash = s.get();


        System.out.println(get(2, a -> a + a, a -> a * a));

        System.out.println(getSum(1, 2, (a, b) -> a + b, a -> a * a));

        System.out.println(predicate(1, a -> a < 2, a -> a > 2));

        new ArrayList<>().forEach(value -> {
            System.out.println(value);
            value.getClass();
        });

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        HashMap<Object, Object> map = new HashMap<>();

        linkedHashMap.put("1", "2");
        linkedHashMap.put("0", "2");
        linkedHashMap.put("18", "2");
        linkedHashMap.put("8", "2");

        map.put("1", "2");
        map.put("0", "2");
        map.put("18", "2");
        map.put("8", "2");


        System.out.println();
    }


    public static Integer getSum(Integer a, Integer b, BiFunction<Integer, Integer, Integer> f1, Function<Integer, Integer> f2) {
        return f1.andThen(f2).apply(a, b);
    }

    public static Integer get(Integer a, Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        return f1.andThen(f2).apply(a);
    }

    public static boolean predicate(Integer t, Predicate<Integer> predicate) {
        return predicate.negate().test(t);
    }

    public static boolean predicate(Integer t, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        return predicate1.or(predicate2).test(t);
    }


    @Test
    public void test6() throws Exception {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 10, 1, TimeUnit.MILLISECONDS, queue);

        new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                int finalI = i;
                try {
                    queue.put(() -> {
                        System.out.println(Thread.currentThread().getName() + "执行任务啦" + finalI);
                        try {
                            Thread.currentThread().sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        pool.execute(queue.take());
        pool.execute(queue.take());
        pool.execute(queue.take());
        pool.execute(queue.take());

        System.out.println("开始运行" + pool.toString());

        pool.shutdown();

        while (!pool.awaitTermination(1, TimeUnit.MILLISECONDS)) {
            System.out.println("运行中" + pool.toString());
        }
        System.out.println("结束后" + pool.toString());
//        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
