package com.wtix.wtix.repository;

import com.wtix.wtix.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public List<Ticket> findByBookingId(Integer id);
}
