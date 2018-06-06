package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.FillInTheBlanksQuestion;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.FillInTheBlanksQuestionRepository;

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
public class FillInTheBlanksQuestionService {

  @Autowired
  ExamRepository examRepository;

  @Autowired
  FillInTheBlanksQuestionRepository fillInTheBlanksQuestionRepository;

  @PostMapping("/api/exam/{eid}/blanks")
  public FillInTheBlanksQuestion createBlanksQuestion(@PathVariable("eid") int examId, @RequestBody FillInTheBlanksQuestion newQuestion) {
    Optional<Exam> data = examRepository.findById(examId);
    if(data.isPresent()) {
      Exam examWidget = data.get();
      newQuestion.setExam(examWidget);
      return fillInTheBlanksQuestionRepository.save(newQuestion);
    }
    return null;
  }

  @PutMapping("/api/blanks/{qid}")
  public FillInTheBlanksQuestion updateBlanksQuestion(@PathVariable("qid") int qid, @RequestBody FillInTheBlanksQuestion newQuestion) {
    Optional<FillInTheBlanksQuestion> data = fillInTheBlanksQuestionRepository.findById(qid);
    if(data.isPresent()) {
      FillInTheBlanksQuestion question = data.get();
      question.setTitle(newQuestion.getTitle());
      question.setSubtitle(newQuestion.getSubtitle());
      question.setPoints(newQuestion.getPoints());
      question.setInputArea(newQuestion.getInputArea());
      return fillInTheBlanksQuestionRepository.save(question);
    }
    return null;
  }

  @DeleteMapping("/api/blanks/{qid}")
  public void deleteBlanksQuestion(@PathVariable("qid") int questionId) {
    fillInTheBlanksQuestionRepository.deleteById(questionId);
  }

  @GetMapping("/api/blanks/{questionId}")
  public FillInTheBlanksQuestion findBlanksQuestionById(@PathVariable("questionId") int questionId) {
    Optional<FillInTheBlanksQuestion> optional = fillInTheBlanksQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }
}
