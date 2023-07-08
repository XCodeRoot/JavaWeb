package com.atguigu.json;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTest {

    //JavaBean 和 Json的互转
    @Test
    public void test1(){
        Person person = new Person(1,"国哥好帅!");
        // 创建 Gson 对象实例
        Gson gson = new Gson();
        // toJson 方法可以把 java 对象转换成为 json 字符串
        String personJsonString = gson.toJson(person);
        System.out.println(personJsonString);
        // fromJson 把 json 字符串转换回 Java 对象
        // 第一个参数是 json 字符串
        // 第二个参数是转换回去的 Java 对象类型
        Person person1 = gson.fromJson(personJsonString, Person.class);
        System.out.println(person1);
    }

    // 1.2.2、List 和 json 的互转
    @Test
    public void test2() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "国哥"));
        personList.add(new Person(2, "康师傅"));
        Gson gson = new Gson();
        // 把 List 转换为 json 字符串
        String personListJsonString = gson.toJson(personList);
        System.out.println(personListJsonString);
        //把json字符串转为java对象的列表 ,需要提前建一个类 这个类继承gson里的TypeToken<> , 泛型参数为 List<Person>
        // PersonListType extends TypeToken<ArrayList<Person>> ,这样我们才能让gson.fromJson()方法 知道我们需要转的是哪个类型
        List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);
    }


    // 1.2.3、map 和 json 的互转
    @Test
    public void test3(){
        Map<Integer,Person> personMap = new HashMap<>();
        personMap.put(1, new Person(1, "国哥好帅"));
        personMap.put(2, new Person(2, "康师傅也好帅"));
        Gson gson = new Gson();
        // 把 map 集合转换成为 json 字符串
        String personMapJsonString = gson.toJson(personMap);
        System.out.println(personMapJsonString);
        //Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());

        //把 Json字符串转为 Map集合 , 使用匿名内部类 而不是 另外建一个类继承TypeToken<HashMap<Integer,Person>>
        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new
                TypeToken<HashMap<Integer,Person>>(){}.getType());
        System.out.println(personMap2);
        Person p = personMap2.get(1);
        System.out.println(p);
    }
}
