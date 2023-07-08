package com.atguigu.json;

import com.atguigu.pojo.Person;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

//把json字符串转为java对象的列表 ,需要提前建一个类 这个类继承gson里的TypeToken<> , 泛型参数为 List<Person>
public class PersonListType extends TypeToken<ArrayList<Person>> {

}
