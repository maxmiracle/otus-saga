package ru.otus.warehouse.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.warehouse.entity.Product;
import ru.otus.warehouse.entity.Reservation;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Optional<Reservation> findByOrderId(@NotNull UUID orderId);
}
