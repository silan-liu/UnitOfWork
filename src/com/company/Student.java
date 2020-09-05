package com.company;

public class Student {
    private final Integer id;
    private final String name;
    private final String address;

    /**
     * @param id 学生 id
     * @param name 学生姓名
     * @param address 学生地址
     */
    public Student(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
