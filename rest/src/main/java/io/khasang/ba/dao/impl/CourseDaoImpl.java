package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.CourseDao;
import io.khasang.ba.entity.Course;

public class CourseDaoImpl extends BasicDaoImpl<Course> implements CourseDao {
    public CourseDaoImpl(Class<Course> entityClass) {
        super(entityClass);
    }
}
