package com.wkcto.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

//实体类
public class User {
    //定义属性：属性名和列表中的列名一样
    /**
     * 指定主键的方式：
     * value：主键字段的名称，如果是id，可以不用写
     * type：指定主键的类型   主键的值如何形成。IdType.AUTO 表示自动增长
     */
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private String name;
    private String email;
    //实体类的属性，推荐使用包装类型，可以判断是否为 null
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
