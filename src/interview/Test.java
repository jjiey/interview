package interview;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        int a = 0;
//        int b = 5;
//        int i = (a + b) / 2;
//        System.out.println(i);

//        Map<String, Long> map = new HashMap(){{
//            put("c", 33333L);
//            put("a", 11111L);
//            put("d", 44444L);
//            put("e", 55555L);
//            put("b", 22222L);
//        }};
//        //将map.entrySet()转换成list
//        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
//        //降序排序
//        Collections.sort(list, (o1, o2) -> {
//            //return o1.getValue().compareTo(o2.getValue());
//            return o2.getValue().compareTo(o1.getValue());
//        });
//        for (Map.Entry<String, Long> mapping : list) {
//            System.out.println(mapping.getKey() + ":" + mapping.getValue());
//        }

        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{2, 3, 8, 9};
        int[] c = new int[]{6, 7, 8, 9, 10};
        Set<Integer> res = new HashSet();
//        https://www.cnblogs.com/ASPNET2008/p/6034561.html
        //4

        //3

        //2
//        Arrays.sort(a);
//        Arrays.sort(b);
//        int indexA = 0;
//        int indexB = 0;
//        while(indexA < a.length) {
//            for (int i = indexB; i < b.length; i ++) {
//                if (a[indexA] == b[indexB]) {
//                    res.add(a[indexA]);
//                    indexA ++;
//                    indexB ++;
//                    break;
//                } else if (i == b.length - 1) {
//                    indexA ++;
//                }
//            }
//        }
        //1
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < b.length; j++) {
//                if (a[i] == b[j]) {
//                    res.add(a[i]);
//                }
//            }
//        }
        //res
        res.forEach(i -> System.out.println(i));
    }
    
}
