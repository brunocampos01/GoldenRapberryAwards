package com.awards.awards.service;

import com.awards.awards.dto.IntervalResponse;
import com.awards.awards.dto.ProducerInterval;
import com.awards.awards.model.Movie;
import com.awards.awards.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AwardService {

  private final MovieRepository movieRepository;

  public AwardService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public IntervalResponse calculateIntervals() {
    List<Movie> winners = movieRepository.findAll()
        .stream()
        .filter(Movie::getWinner)
        .collect(Collectors.toList());
    //  System.out.println("==== Winners ====");
    //  winners.forEach(w -> System.out.println(w.getYear() + " - " + w.getTitle() + " - " + w.getProducers()));

    // Map producers -> list of winning years
    Map<String, List<Integer>> producerWins = new HashMap<>();

    for (Movie movie : winners) {
      String[] producers = movie.getProducers().split(",| and "); // handle multiple producers
      for (String p : producers) {
        String producer = p.trim();
        producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
      }
    }

    // Calculate intervals for each producer
    List<ProducerInterval> intervals = new ArrayList<>();
    for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
      List<Integer> years = entry.getValue().stream().sorted().collect(Collectors.toList());
      for (int i = 1; i < years.size(); i++) {
        int interval = years.get(i) - years.get(i - 1);
        intervals.add(new ProducerInterval(entry.getKey(), interval, years.get(i - 1), years.get(i)));
      }
    }

    if (intervals.isEmpty()) {
      return new IntervalResponse(Collections.emptyList(), Collections.emptyList());
    }

    // find min and max
    int minInterval = intervals.stream().mapToInt(ProducerInterval::getInterval).min().orElse(0);
    int maxInterval = intervals.stream().mapToInt(ProducerInterval::getInterval).max().orElse(0);

    List<ProducerInterval> minList = intervals.stream()
        .filter(i -> i.getInterval() == minInterval)
        .collect(Collectors.toList());

    List<ProducerInterval> maxList = intervals.stream()
        .filter(i -> i.getInterval() == maxInterval)
        .collect(Collectors.toList());

    return new IntervalResponse(minList, maxList);
  }
}
