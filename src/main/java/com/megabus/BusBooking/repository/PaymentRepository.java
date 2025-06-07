package com.megabus.BusBooking.repository;

import com.megabus.BusBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
