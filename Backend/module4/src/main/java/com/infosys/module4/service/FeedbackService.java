package com.infosys.module4.service;


import com.infosys.module4.dto.FeedbackDto;
import com.infosys.module4.exception.FeedbackException;
import com.infosys.module4.model.Feedback;
import com.infosys.module4.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    public FeedbackResponse addFeedback(FeedbackDto feedbackDto,Long eventId) throws FeedbackException;
    public List<Feedback> getFeedbackByEventId(Long eventId) throws FeedbackException;
}
