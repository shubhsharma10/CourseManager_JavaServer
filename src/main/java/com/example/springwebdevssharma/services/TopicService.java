package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Course;
import com.example.springwebdevssharma.models.Lesson;
import com.example.springwebdevssharma.models.Module;
import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.repositories.CourseRepository;
import com.example.springwebdevssharma.repositories.LessonRepository;
import com.example.springwebdevssharma.repositories.ModuleRepository;
import com.example.springwebdevssharma.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
  @Autowired
  CourseRepository courseRepository;

  @Autowired
  ModuleRepository moduleRepository;

  @Autowired
  LessonRepository lessonRepository;

  @Autowired
  TopicRepository topicRepository;

  @PostMapping("/api/course/{courseId}/module/{mid}/lesson/{lid}/topic")
  public Topic createTopic(
          @PathVariable("courseId") int courseId,
          @PathVariable("mid") int mid,
          @PathVariable("lid") int lid,
          @RequestBody Topic newTopic) {
    Optional<Course> courseData = courseRepository.findById(courseId);

    if(courseData.isPresent()) {
      Optional<Module> moduleData = moduleRepository.findById(mid);

      if(moduleData.isPresent()) {
        Optional<Lesson> lessonData = lessonRepository.findById(lid);
        if(lessonData.isPresent()) {
          Lesson lesson = lessonData.get();
          newTopic.setLesson(lesson);
          return  topicRepository.save(newTopic);
        }

      }
    }
    return null;
  }

  @DeleteMapping("/api/topic/{topicId}")
  public void deleteTopic(@PathVariable("topicId") int topicId)
  {
    topicIdRepository.deleteById(topicId);
  }


  @GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid/topic")
  public List<Topic> findAllTopicsForLesson(
          @PathVariable("cid") int courseId,
          @PathVariable("mid") int moduleId,
          @PathVariable("lid") int lessonId)
  {
    Optional<Course> courseData = courseRepository.findById(courseId);
    Optional<Module> moduleData = moduleRepository.findById(moduleId);
    Optional<Lesson> lessonData = lessonRepository.findById(lessonId);
    if(moduleData.isPresent()
            && courseData.isPresent()
            && lessonData.isPresent())
    {
      Lesson lesson = lessonData.get();
      return lesson.getTopics();

    }
    return null;
  }
}
