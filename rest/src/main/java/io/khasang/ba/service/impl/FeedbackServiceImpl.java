package io.khasang.ba.service.impl;

import io.khasang.ba.dao.FeedbackDao;
import io.khasang.ba.entity.Feedback;
import io.khasang.ba.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackDao.add(feedback);
    }

    @Override
    public Feedback getFeedbackById(long id) {
        return feedbackDao.getById(id);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackDao.getAll();
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return feedbackDao.update(feedback);
    }

    @Override
    public Feedback deleteFeedback(long id) {
        return feedbackDao.delete(getFeedbackById(id));
    }
}
