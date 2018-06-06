package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.EssayQuestion;
import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.repositories.EssayQuestionRepository;
import com.example.springwebdevssharma.repositories.ExamRepository;

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
public class EssayQuestionService {

  @Autowired
  ExamRepository examRepository;

  @Autowired
  EssayQuestionRepository essayQuestionRepository;


  @PostMapping("/api/exam/{eid}/essay")
  public EssayQuestion createEssayQuestion(@PathVariable("eid") int examId, @RequestBody EssayQuestion newQuestion) {
    Optional<Exam> data = examRepository.findById(examId);
    if(data.isPresent()) {
      Exam examWidget = data.get();
      newQuestion.setExam(examWidget);
      return essayQuestionRepository.save(newQuestion);
    }
    return null;
  }

  @PutMapping("/api/essay/{qid}")
  public EssayQuestion updateEssayQuestion(@PathVariable("qid") int qid, @RequestBody EssayQuestion newQuestion) {
    Optional<EssayQuestion> data = essayQuestionRepository.findById(qid);
    if(data.isPresent()) {
      EssayQuestion question = data.get();
      question.setTitle(newQuestion.getTitle());
      question.setSubtitle(newQuestion.getSubtitle());
      question.setPoints(newQuestion.getPoints());
      return essayQuestionRepository.save(question);
    }
    return null;
  }

  @DeleteMapping("/api/essay/{qid}")
  public void deleteEssayQuestion(@PathVariable("qid") int questionId) {
    essayQuestionRepository.deleteById(questionId);
  }

  @GetMapping("/api/essay/{questionId}")
  public EssayQuestion findEssayQuestionById(@PathVariable("questionId") int questionId) {
    Optional<EssayQuestion> optional = essayQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }
}
