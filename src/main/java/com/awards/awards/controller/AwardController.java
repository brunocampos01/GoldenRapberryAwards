package com.awards.awards.controller;

import com.awards.awards.dto.IntervalResponse;
import com.awards.awards.service.AwardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards")
public class AwardController {

  private final AwardService awardService;

  public AwardController(AwardService awardService) {
    this.awardService = awardService;
  }

  @GetMapping("/intervals")
  public IntervalResponse getIntervals() {
    return awardService.calculateIntervals();
  }
}
