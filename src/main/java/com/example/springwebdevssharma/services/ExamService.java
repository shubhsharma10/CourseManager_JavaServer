package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.MultipleChoiceQuestion;
import com.example.springwebdevssharma.models.Question;
import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.models.TrueFalseQuestion;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.MultipleChoiceQuestionRepository;
import com.example.springwebdevssharma.repositories.TopicRepository;
import com.example.springwebdevssharma.repositories.TrueFalseQuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  TrueFalseQuestionRepository trueFalseQuestionRepository;

  @Autowired
  MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

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

  @GetMapping("/api/multi/{questionId}")
  public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
    Optional<MultipleChoiceQuestion> optional = multipleChoiceQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  @GetMapping("/api/truefalse/{questionId}")
  public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
    Optional<TrueFalseQuestion> optional = trueFalseQuestionRepository.findById(questionId);
    if(optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

  @GetMapping("/api/exam/{examId}/question")
  public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
    Optional<Exam> optionalExam = examRepository.findById(examId);
    if(optionalExam.isPresent()) {
      Exam exam = optionalExam.get();
      List<Question> questions = exam.getQuestions();
      int count = questions.size();
      return questions;
    }
    return null;
  }
}
