package com.courses.forum.repository;

import com.courses.forum.module.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String courseName);
}
