package com.company;

/**
 * unitOfWork 接口类
 * @param <T> 泛型
 */
public interface IUnitOfWork<T> {
    String INSERT = "INSERT";
    String DELETE = "DELETE";
    String MODIFY = "MODIFY";

    /**
     * 注册新增对象
     * @param entity 实体
     */
    void registerNew(T entity);

    /**
     * 注册修改对象
     * @param entity 实体
     */
    void registerModified(T entity);

    /**
     * 注册删除对象
     * @param entity 实体
     */
    void registerDeleted(T entity);

    /**
     * 提交所有修改
     */
    void commit();
}
