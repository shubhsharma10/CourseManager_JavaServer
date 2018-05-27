package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Course;
import com.example.springwebdevssharma.models.Lesson;
import com.example.springwebdevssharma.models.Module;
import com.example.springwebdevssharma.repositories.CourseRepository;
import com.example.springwebdevssharma.repositories.LessonRepository;
import com.example.springwebdevssharma.repositories.ModuleRepository;

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
@CrossOrigin(origins = "*")
public class LessonService {
  @Autowired
  CourseRepository courseRepository;

  @Autowired
  ModuleRepository moduleRepository;

  @Autowired
  LessonRepository lessonRepository;

  @PostMapping("/api/course/{courseId}/module/{mid}/lesson")
  public Lesson createLesson(
          @PathVariable("courseId") int courseId,
          @PathVariable("mid") int mid,
          @RequestBody Lesson newLesson) {
    Optional<Course> data = courseRepository.findById(courseId);

    if(data.isPresent()) {
      Course course = data.get();
      Optional<Module> modData = moduleRepository.findById(mid);

      if(modData.isPresent()) {
        Module module = modData.get();
        newLesson.setModule(module);
        return  lessonRepository.save(newLesson);
      }
    }
    return null;
  }

  @DeleteMapping("/api/lesson/{lessonId}")
  public void deleteLesson(@PathVariable("lessonId") int lessonId)
  {
    lessonRepository.deleteById(lessonId);
  }

  @GetMapping("/api/lesson/{lid}")
  public Lesson findCourseById(@PathVariable("lid") int lessonId) {
    Optional<Lesson> data = lessonRepository.findById(lessonId);
    if(data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("/api/lesson")
  public List<Lesson> findAllLesson()
  {
    return (List<Lesson>) lessonRepository.findAll();
  }


  @GetMapping("/api/course/{cid}/module/{mid}/lesson")
  public List<Lesson> findAllLessonsForModule(
          @PathVariable("cid") int courseId,
          @PathVariable("mid") int moduleId)
  {
    Optional<Course> courseData = courseRepository.findById(courseId);
    Optional<Module> moduleData = moduleRepository.findById(moduleId);
    if(moduleData.isPresent() && courseData.isPresent())
    {
      Module module = moduleData.get();
      return module.getLessons();

    }
    return null;
  }
}