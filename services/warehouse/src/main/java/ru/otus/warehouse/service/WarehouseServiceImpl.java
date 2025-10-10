package ru.otus.warehouse.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.warehouse.api.dto.ReserveRequest;
import ru.otus.warehouse.api.dto.ReserveResponse;
import ru.otus.warehouse.api.dto.RollbackReserveRequest;
import ru.otus.warehouse.entity.Product;
import ru.otus.warehouse.entity.Reservation;
import ru.otus.warehouse.repository.ProductRepository;
import ru.otus.warehouse.repository.ReservationRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public ReserveResponse reserve(ReserveRequest reserveRequest) {
        log.info("reserve({})", reserveRequest);
        // Идемпотентность проверка
        if( reservationRepository.findByOrderId(reserveRequest.getOrderId()).isPresent()) {
            throw new IllegalArgumentException("Order already processed orderId: " + reserveRequest.getOrderId());
        }
        UUID productId = reserveRequest.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("Product not found: " + productId));
        int available = product.getQuantity() - product.getReservedQuantity();
        if (available >= reserveRequest.getQuantity()) {
            product.setReservedQuantity(product.getReservedQuantity() + reserveRequest.getQuantity());
            Reservation reservation = new Reservation(reserveRequest.getOrderId(), reserveRequest.getProductId(), reserveRequest.getQuantity());
            var savedReservation = reservationRepository.save(reservation);
            product.getReservations().add(savedReservation);
            var savedProduct = productRepository.save(product);
            return new ReserveResponse(reserveRequest.getOrderId(), savedReservation.getCreatedAt(), savedReservation.getId(), true, null);
        } else {
            throw new IllegalArgumentException("Product is not available: " + productId);
        }
    }

    @Override
    @Transactional
    public void rollbackReserve(RollbackReserveRequest rollbackReserveRequest) {
        log.info("rollbackReserve({})", rollbackReserveRequest);
            Reservation reservation = reservationRepository.findByOrderId(rollbackReserveRequest.getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Reservation not found: " + rollbackReserveRequest.getOrderId()));
            if (!reservation.getIsActive()) {
                throw new IllegalArgumentException("Order is not active: " + reservation.getOrderId());
            }
            reservation.setIsActive(false);
            Product product = productRepository.findById(reservation.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + reservation.getProductId()));
            product.setReservedQuantity(product.getReservedQuantity() - reservation.getQuantity());
    }


}
