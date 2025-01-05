package com.infosys.module4.response;

import com.infosys.module4.model.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private Feedback feedback;
    private String message;
}
