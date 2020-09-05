package com.company;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement;

import java.awt.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnitOfWork implements IUnitOfWork<Student> {

    private Map<String, List<Student>> context;
    private StudentDatabase studentDatabase;

    public UnitOfWork(Map<String, List<Student>> context, StudentDatabase studentDatabase) {
        this.context = context;
        this.studentDatabase = studentDatabase;
    }

    /**
     * 注册对象
     *
     * @param student   对象
     * @param operation 操作类型
     */
    private void register(Student student, String operation) {
        List<Student> studentToOperate = context.get(operation);
        if (studentToOperate == null) {
            studentToOperate = new ArrayList<Student>();
        }

        studentToOperate.add(student);
        context.put(operation, studentToOperate);
    }

    /**
     * 注册新增对象
     *
     * @param student 实体
     */
    @Override
    public void registerNew(Student student) {
        System.out.println("register new student:" + student.getName());
        register(student, INSERT);
    }

    /**
     * 注册修改对象
     *
     * @param student 实体
     */
    @Override
    public void registerModified(Student student) {
        System.out.println("modify student:" + student.getName());
        register(student, MODIFY);
    }

    /**
     * 注册删除对象
     *
     * @param student 实体
     */
    @Override
    public void registerDeleted(Student student) {
        System.out.println("delete student:" + student.getName());
        register(student, DELETE);
    }

    /**
     * 提交所有修改
     */
    @Override
    public void commit() {
        if (context == null || context.size() == 0) {
            return;
        }

        System.out.println("Commit started");

        if (context.containsKey(INSERT)) {
            commitInsert();
        }

        if (context.containsKey(DELETE)) {
            commitDelete();
        }

        if (context.containsKey(MODIFY)) {
            commitModify();
        }
    }

    /**
     * 提交新增对象
     */
    private void commitInsert() {
        List<Student> studentsToBeInserted = context.get(INSERT);
        for (Student student : studentsToBeInserted) {
            System.out.println("Saving " + student.getName() + " to database.");
            studentDatabase.insert(student);
        }
    }

    /**
     * 提交删除对象
     */
    private void commitDelete() {
        List<Student> studentToBeDeleted = context.get(DELETE);
        for (Student student : studentToBeDeleted) {
            System.out.println("Deleting " + student.getName() + " to database.");
            studentDatabase.delete(student);
        }
    }

    /**
     * 提交修改对象
     */
    private void commitModify() {
        List<Student> studentToBeModified = context.get(MODIFY);
        for (Student student: studentToBeModified) {
            System.out.println("Modify " + student.getName() + " to database.");
            studentDatabase.modify(student);
        }
    }
}
