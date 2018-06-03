package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Assignment;
import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.repositories.AssignmentRepository;
import com.example.springwebdevssharma.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
  @Autowired
  AssignmentRepository assignmentRepository;

  @Autowired
  TopicRepository topicRepository;

  @PostMapping("/api/topic/{tid}/assignment")
  public Assignment createAssignment(@PathVariable("tid") int topicId, @RequestBody Assignment newAssignment) {
    Optional<Topic> data = topicRepository.findById(topicId);
    if(data.isPresent()) {
      Topic topic = data.get();
      newAssignment.setTopic(topic);
      return assignmentRepository.save(newAssignment);
    }
    return null;
  }

  @GetMapping("/api/assignment/{aid}")
  public Assignment findAssignmentById(@PathVariable("aid") int aid) {
    Optional<Assignment> data = assignmentRepository.findById(aid);
    if(data.isPresent()) {
      return data.get();
    }
    return null;
  }
}
