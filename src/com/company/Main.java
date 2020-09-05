package com.company;

import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Student susan = new Student(1, "Susan", "HuBei");
        Student james = new Student(2, "James", "BeiJing");
        Student lucy = new Student(3, "Lucy", "HuNan");

        HashMap<String, List<Student>> context = new HashMap();
        StudentDatabase studentDatabase = new StudentDatabase();

        IUnitOfWork uow = new UnitOfWork(context, studentDatabase);

        uow.registerNew(susan);
        uow.registerDeleted(james);
        uow.registerModified(lucy);

        uow.commit();
    }
}
