package com.awards.awards.dto;

import java.util.List;

public class IntervalResponse {
  private List<ProducerInterval> min;
  private List<ProducerInterval> max;

  public IntervalResponse(List<ProducerInterval> min, List<ProducerInterval> max) {
    this.min = min;
    this.max = max;
  }

  public List<ProducerInterval> getMin() { return min; }
  public List<ProducerInterval> getMax() { return max; }
}
