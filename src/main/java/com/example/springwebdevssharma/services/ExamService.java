package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.Question;
import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {

  @Autowired
  ExamRepository examRepository;

  @Autowired
  TopicRepository topicRepository;

  @PostMapping("/api/topic/{tid}/exam")
  public Exam createExam(@PathVariable("tid") int topicId, @RequestBody Exam newExam) {
    Optional<Topic> data = topicRepository.findById(topicId);
    if(data.isPresent()) {
      Topic topic = data.get();
      newExam.setTopic(topic);
      return examRepository.save(newExam);
    }
    return null;
  }

  @PutMapping("/api/exam/{eid}")
  public Exam updateExam(@PathVariable("eid") int eid, @RequestBody Exam newExam) {
    Optional<Exam> data = examRepository.findById(eid);
    if(data.isPresent()) {
      Exam exam = data.get();
      exam.setTitle(newExam.getTitle());
      return examRepository.save(exam);
    }
    return null;
  }

  @DeleteMapping("/api/exam/{eid}")
  public void deleteExam(@PathVariable("eid") int widgetId) {
    examRepository.deleteById(widgetId);
  }

  @GetMapping("/api/exam/{examId}/question")
  public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
    Optional<Exam> optionalExam = examRepository.findById(examId);
    if(optionalExam.isPresent()) {
      Exam exam = optionalExam.get();
      List<Question> questions = exam.getQuestions();
      return questions;
    }
    return null;
  }

  @GetMapping("/api/exam")
  public List<Exam> FindAllExams() {
    return (List<Exam>)examRepository.findAll();
  }

  @GetMapping("/api/exam/{examId}")
  public Exam FindExamById(@PathVariable("examId") int examId) {
    Optional<Exam> examData = examRepository.findById(examId);
    if(examData.isPresent())
    {
      return examData.get();
    }
    return null;
  }

  @GetMapping("/api/topic/{topicId}/exam")
  public void FindExamsForTopic(@PathVariable("topicId") int topicId) {
    Optional<Topic> topicData = topicRepository.findById(topicId);
    if(topicData.isPresent())
    {
      Topic topic = topicData.get();
      System.out.println(topic.getWidgets());
    }
  }
}
