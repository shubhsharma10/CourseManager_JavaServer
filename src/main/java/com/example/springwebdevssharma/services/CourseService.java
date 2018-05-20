package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Course;
import com.example.springwebdevssharma.models.Module;
import com.example.springwebdevssharma.repositories.CourseRepository;
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


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
  @Autowired
  CourseRepository courseRepository;
  @Autowired
  ModuleRepository moduleRepository;

  @GetMapping("/api/course")
  public Iterable<Course> findAllCourses() {
    return courseRepository.findAll();
  }

  @PostMapping("/api/course")
  public Course createCourse(@RequestBody Course course) {
    return courseRepository.save(course);
  }

  @DeleteMapping("/api/course/{courseId}")
  public void deleteCourse(@PathVariable("courseId") int id) {
    List<Module> moduleList = (List<Module>)moduleRepository.findAll();

    for(Module module: moduleList)
    {
      if(module.getCourse().getId() == id)
        moduleRepository.deleteById(module.getId());
    }
    courseRepository.deleteById(id);
  }
}