package com.infosys.module4.controller;

import com.infosys.module4.dto.FeedbackDto;
import com.infosys.module4.model.Feedback;
import com.infosys.module4.response.FeedbackResponse;
import com.infosys.module4.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    @PostMapping("/addFeedback/{eventId}")
    public FeedbackResponse addFeedback(@RequestHeader("Authorization") String jwt,@RequestBody FeedbackDto feedbackDto, @PathVariable Long eventId){
        return feedbackService.addFeedback(feedbackDto,eventId);
    }
    @GetMapping("/getFeedback/{eventId}")
    public List<Feedback> getFeedbackByEventId(@RequestHeader("Authorization") String jwt,@PathVariable Long eventId){
        return feedbackService.getFeedbackByEventId(eventId);
    }
}
