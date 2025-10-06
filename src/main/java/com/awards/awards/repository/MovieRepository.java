package com.awards.awards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awards.awards.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
