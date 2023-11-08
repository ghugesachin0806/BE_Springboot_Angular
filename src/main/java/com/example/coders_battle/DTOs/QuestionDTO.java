package com.example.coders_battle.DTOs;

import java.util.List;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class QuestionDTO {

    private Long id;
    private String title;
    private String body;
    private List<String> tags;
    private Long userId;
    private String username;

}
