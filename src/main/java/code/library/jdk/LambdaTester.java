package code.library.jdk;

import org.springframework.util.AntPathMatcher;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fuqianzhong
 * @date 18/9/13
 */
public class LambdaTester {
    public static void main(String[] args) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean s = pathMatcher.match("/orders/*/6","/orders/sub/61?pageSize=20&pageIndex=1&moduleId=2");
        System.out.println(s);

        BigDecimal b = BigDecimal.ZERO;
        b = b.add(new BigDecimal("12"));
        b = b.add(new BigDecimal("10"));
        System.out.println(b);
//        testFilter();
        testSort();
    }

    private static void testFilter(){
        List<Integer> list = Arrays.asList(1,3,5,3,2,7,9,10);
        List<Integer> list2 = list.stream()
                                  .filter(x->x>4)
                                  .collect(Collectors.toList());
        System.out.println(list2);
    }

    private static void testSort(){
        List<Student> students = Arrays.asList(new Student(20,"f"),new Student(10,"b"),new Student(50,"g"),new Student(60,"c"),new Student(30,"a"));

        //自然顺序,升序排列
        students.stream().sorted().collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
        //自然顺序,倒序排列
        students.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();
        //根据对象某个属性,自然排序
        students.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();

        //根据对象某个属性,倒序排序
        students.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList())
                .forEach(System.out::print);
        System.out.println();

        long count = students.stream().filter(x->x.getAge()>20).count();
        System.out.println(count);

        Student maxStudent = students.stream().max(Comparator.comparing(Student::getAge)).orElse(null);
        System.out.println(maxStudent);

        int totalAge = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(totalAge);

        Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
        System.out.println(map);

        Student student = students.stream().findFirst().orElse(null);
        Student student2 = students.stream().findAny().orElse(null);
        boolean result = students.stream().allMatch(x->x.getAge()>0);
        boolean result2 = students.stream().noneMatch(x->x.getAge()==10);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(student);
        System.out.println(student2);


    }

    private static class Student implements Comparable<Student>{
        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Student o) {
            return this.name.compareTo(o.getName());
        }

        @Override
        public String toString(){
            return "name:" + name + ", age:" + age;
        }


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
