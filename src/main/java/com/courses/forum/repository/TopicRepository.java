package com.courses.forum.repository;

import com.courses.forum.module.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findByCourse_Name(String courseName, Pageable pageable);
}
