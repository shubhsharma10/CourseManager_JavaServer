package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.MultipleChoiceQuestion;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.MultipleChoiceQuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceQuestionService {

  @Autowired
  ExamRepository examRepository;

  @Autowired
  MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

  @PostMapping("/api/exam/{eid}/choice")
  public MultipleChoiceQuestion createMultipleChoiceQuestion(@PathVariable("eid") int examId, @RequestBody MultipleChoiceQuestion newQuestion) {
    Optional<Exam> data = examRepository.findById(examId);
    if(data.isPresent()) {
      Exam examWidget = data.get();
      newQuestion.setExam(examWidget);
      return multipleChoiceQuestionRepository.save(newQuestion);
    }
    return null;
  }

  @PutMapping("/api/choice/{qid}")
  public MultipleChoiceQuestion updateMultipleChoiceQuestion(@PathVariable("qid") int qid, @RequestBody MultipleChoiceQuestion newQuestion) {
    Optional<MultipleChoiceQuestion> data = multipleChoiceQuestionRepository.findById(qid);
    if(data.isPresent()) {
      MultipleChoiceQuestion question = data.get();
      question.setTitle(newQuestion.getTitle());
      question.setSubtitle(newQuestion.getSubtitle());
      question.setPoints(newQuestion.getPoints());
      question.setCorrectOption(newQuestion.getCorrectOption());
      question.setOptions(newQuestion.getOptions());
      return multipleChoiceQuestionRepository.save(question);
    }
    return null;
  }

  @DeleteMapping("/api/choice/{qid}")
  public void deleteMultipleChoiceQuestion(@PathVariable("qid") int questionId) {
    multipleChoiceQuestionRepository.deleteById(questionId);
  }

  @GetMapping("/api/choice/{questionId}")
  public MultipleChoiceQuestion findMultipleChoiceQuestionById(@PathVariable("questionId") int questionId) {
    Optional<MultipleChoiceQuestion> optional = multipleChoiceQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

}
