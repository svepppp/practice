package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.FeedbackDao;
import io.khasang.ba.entity.Feedback;

public class FeedbackDaoImpl extends BasicDaoImpl<Feedback> implements FeedbackDao {
    public FeedbackDaoImpl(Class<Feedback> entityClass) {
        super(entityClass);
    }
}
