package br.com.cinepoti.cinepoti_api.service;

import org.springframework.stereotype.Service;

import br.com.cinepoti.cinepoti_api.dto.response.PaymentResponseDTO;
import br.com.cinepoti.cinepoti_api.enums.PaymentStatus;
import br.com.cinepoti.cinepoti_api.mapper.PaymentMapper;
import br.com.cinepoti.cinepoti_api.model.Payment;
import br.com.cinepoti.cinepoti_api.repository.PaymentRepository;

@Service
public class PaymentService {
  private final PaymentRepository paymentRepository;

  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  // Supondo que Payments não serão registrados isoladamente de Bookings
  public PaymentResponseDTO create(Payment payment) {
    // Não existe Payment sem estar assoicado a um Booking, logo, supomos que já
    // existe o booking no banco de dados
    paymentRepository.save(payment);
    return PaymentMapper.toResponseDTO(payment);
  }

  public Payment getPaymentObjById(Long id) {
    return paymentRepository.findById(id).orElseThrow();
  }

  public void pay(Long id) {
    Payment payment = getPaymentObjById(id);
    payment.setPaymentStatus(PaymentStatus.PAID);
    paymentRepository.save(payment);
  }
}
