package com.courses.forum.controller;

import com.courses.forum.controller.dto.DetailedTopicDTO;
import com.courses.forum.controller.dto.TopicDTO;
import com.courses.forum.controller.form.TopicForm;
import com.courses.forum.controller.form.UpdateTopicForm;
import com.courses.forum.module.Topic;
import com.courses.forum.repository.CourseRepository;
import com.courses.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value="topicsList")
    public Page<TopicDTO> list(@RequestParam(required = false) String courseName,
                               @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {

        if( courseName == null){
            Page<Topic> topics = topicRepository.findAll(pageable);
            return TopicDTO.converter(topics);
        } else {

            Page<Topic> topics = topicRepository.findByCourse_Name(courseName, pageable);
            return TopicDTO.converter(topics);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value="topicsList", allEntries = true)
    public ResponseEntity<TopicDTO> register(@RequestBody  @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder){
        Topic topic = topicForm.convert(courseRepository);

        topicRepository.save(topic);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedTopicDTO> details(@PathVariable("id") Long id){

        Optional<Topic> topic = topicRepository.findById(id);

        if(topic.isPresent()){
            return ResponseEntity.ok(new DetailedTopicDTO(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value="topicsList", allEntries = true)
    public ResponseEntity<TopicDTO> update( @PathVariable Long id, @RequestBody @Valid UpdateTopicForm form){
        Optional<Topic> optional = topicRepository.findById(id);
        if(optional.isPresent()){
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDTO(topic));
        }


        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value="topicsList", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Topic> optional = topicRepository.findById(id);
        if(optional.isPresent()){
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
