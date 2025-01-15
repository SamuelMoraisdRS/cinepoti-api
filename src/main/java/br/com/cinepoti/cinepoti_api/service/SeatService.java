package br.com.cinepoti.cinepoti_api.service;


import org.springframework.stereotype.Service;

import br.com.cinepoti.cinepoti_api.model.Seat;
import br.com.cinepoti.cinepoti_api.repository.SeatRepository;

@Service
public class SeatService {

  private final SeatRepository seatRepository;

  public SeatService(SeatRepository seatRepository) {
    this.seatRepository = seatRepository;
  }

  public Seat getObjById(Long seatId) {
    return seatRepository.findById(seatId).orElseThrow();
  }

}
