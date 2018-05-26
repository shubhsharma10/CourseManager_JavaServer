package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Course;
import com.example.springwebdevssharma.repositories.CourseRepository;

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
public class CourseService {
  @Autowired
  CourseRepository courseRepository;

  @GetMapping("/api/course")
  public Iterable<Course> findAllCourses() {
    return courseRepository.findAll();
  }

  @GetMapping("/api/course/{courseId}")
  public Course findCourseById(@PathVariable("courseId") int courseId) {
    Optional<Course> data = courseRepository.findById(courseId);
    if(data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @PutMapping("/api/course/{courseId}")
  public Course updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course newCourse) {
    Optional<Course> data = courseRepository.findById(courseId);
    if(data.isPresent()) {
      Course course = data.get();
      course.setModified(newCourse.getModified());
      courseRepository.save(course);
      return course;
    }
    return null;
  }

  @PostMapping("/api/course")
  public Course createCourse(@RequestBody Course course) {
    return courseRepository.save(course);
  }

  @DeleteMapping("/api/course/{courseId}")
  public void deleteCourse(@PathVariable("courseId") int id) {
    courseRepository.deleteById(id);
  }
}