package com.infosys.module4.service;

import com.infosys.module4.dto.FeedbackDto;
import com.infosys.module4.exception.FeedbackException;
import com.infosys.module4.model.Feedback;
import com.infosys.module4.repository.FeedbackRepository;
import com.infosys.module4.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImplementation implements FeedbackService{

    @Autowired
    FeedbackRepository feedbackRepository;
    @Override
    public FeedbackResponse addFeedback(FeedbackDto feedbackDto, Long eventId) throws FeedbackException{
        Feedback newFeedback=new Feedback();
        newFeedback.setContent(feedbackDto.getContent());
        newFeedback.setEventId(eventId);
        Feedback savedFeedback=feedbackRepository.save(newFeedback);
        if (savedFeedback.getFeedbackId()!=null){
            return new FeedbackResponse(savedFeedback,"Feedback Sent Successfully");
        }
        throw new FeedbackException("Unable to Add Feedback");
    }

    @Override
    public List<Feedback> getFeedbackByEventId(Long eventId) throws FeedbackException{
        return feedbackRepository.findByEventId(eventId);
    }
}
