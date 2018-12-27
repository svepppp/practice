package io.khasang.ba.service;

import io.khasang.ba.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * Method for add Feedback
     *
     * @param feedback - feedback for add
     * @return created feedback
     */
    Feedback addFeedback(Feedback feedback);

    /**
     * Method for getting Feedback by specific id
     *
     * @param id - feedback's id
     * @return feedback by id
     */
    Feedback getFeedbackById(long id);

    /**
     * Method for getting all Feedback
     *
     * @return all feedbacks
     */
    List<Feedback> getAllFeedback();

    /**
     * Method for update Feedback
     *
     * @param feedback - feedback with updated params
     * @return updated feedback
     */
    Feedback updateFeedback(Feedback feedback);

    /**
     * Method for delete Feedback by id
     *
     * @param id - feedback's id for delete
     * @return deleted feedback
     */
    Feedback deleteFeedback(long id);
}
