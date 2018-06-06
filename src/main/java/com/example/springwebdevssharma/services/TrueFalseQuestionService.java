package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.TrueFalseQuestion;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.TrueFalseQuestionRepository;

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
public class TrueFalseQuestionService {

  @Autowired
  ExamRepository examRepository;

  @Autowired
  TrueFalseQuestionRepository trueFalseQuestionRepository;

  @PostMapping("/api/exam/{eid}/truefalse")
  public TrueFalseQuestion createTrueFalseQuestion(@PathVariable("eid") int examId, @RequestBody TrueFalseQuestion newQuestion) {
    Optional<Exam> data = examRepository.findById(examId);
    if(data.isPresent()) {
      Exam examWidget = data.get();
      newQuestion.setExam(examWidget);
      return trueFalseQuestionRepository.save(newQuestion);
    }
    return null;
  }

  @PutMapping("/api/truefalse/{qid}")
  public TrueFalseQuestion updateTrueFalseQuestion(@PathVariable("qid") int qid, @RequestBody TrueFalseQuestion newQuestion) {
    Optional<TrueFalseQuestion> data = trueFalseQuestionRepository.findById(qid);
    if(data.isPresent()) {
      TrueFalseQuestion question = data.get();
      question.setTitle(newQuestion.getTitle());
      question.setSubtitle(newQuestion.getSubtitle());
      question.setPoints(newQuestion.getPoints());
      question.setTrue(newQuestion.getTrue());
      return trueFalseQuestionRepository.save(question);
    }
    return null;
  }

  @DeleteMapping("/api/truefalse/{qid}")
  public void deleteTrueFalseQuestion(@PathVariable("qid") int questionId) {
    trueFalseQuestionRepository.deleteById(questionId);
  }

  @GetMapping("/api/truefalse/{questionId}")
  public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
    Optional<TrueFalseQuestion> optional = trueFalseQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

}
