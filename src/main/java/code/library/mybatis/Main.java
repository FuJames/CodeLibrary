package code.library.mybatis;

import java.util.*;

/**
 * Created by fuqianzhong on 17/9/22.
 */
public class Main {
    public static void main(String[] args) {
//        SqlOperation sqlOperation = new SqlOperation();
//        sqlOperation.addUser();
        Map<String,Integer> tagsCountMap = new HashMap<String, Integer>();
        tagsCountMap.put("复古",2);
        tagsCountMap.put("欧美",4);
        tagsCountMap.put("大气",2);
        tagsCountMap.put("小气",3);
        tagsCountMap.put("复古2",7);
        tagsCountMap.put("欧美2",8);
        tagsCountMap.put("大气2",9);
        tagsCountMap.put("小气2",1);

        List<Map.Entry<String,Integer>> mapList = new ArrayList<Map.Entry<String, Integer>>(tagsCountMap.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        System.out.println(mapList);


    }
}
