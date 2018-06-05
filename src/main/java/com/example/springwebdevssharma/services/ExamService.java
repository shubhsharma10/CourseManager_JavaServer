package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.EssayQuestion;
import com.example.springwebdevssharma.models.Exam;
import com.example.springwebdevssharma.models.FillInTheBlanksQuestion;
import com.example.springwebdevssharma.models.MultipleChoiceQuestion;
import com.example.springwebdevssharma.models.Question;
import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.models.TrueFalseQuestion;
import com.example.springwebdevssharma.repositories.EssayQuestionRepository;
import com.example.springwebdevssharma.repositories.ExamRepository;
import com.example.springwebdevssharma.repositories.FillInTheBlanksQuestionRepository;
import com.example.springwebdevssharma.repositories.MultipleChoiceQuestionRepository;
import com.example.springwebdevssharma.repositories.TopicRepository;
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
  EssayQuestionRepository essayQuestionRepository;

  @Autowired
  MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

  @Autowired
  FillInTheBlanksQuestionRepository fillInTheBlanksQuestionRepository;

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

  /////////////////////
  // True False Question functions
  ////////////////////

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




  /////////////////////
  // Essay Question functions
  ////////////////////

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

  ///////////////////////////////////
  /// Multiple choice Question methods
  ///////////////////////////////////

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

  ///////////////////////////////////
  /// Fill in the blanks Question methods
  ///////////////////////////////////

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
      question.setParameters(newQuestion.getParameters());
      question.setValues(newQuestion.getValues());
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

  /////////////////////////////////////////



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
}
