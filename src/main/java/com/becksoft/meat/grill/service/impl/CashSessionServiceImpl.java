package com.becksoft.meat.grill.service.impl;

import com.becksoft.meat.grill.entity.CashSession;
import com.becksoft.meat.grill.entity.Employee;
import com.becksoft.meat.grill.entity.WorkWeek;
import com.becksoft.meat.grill.enums.CashSessionStatus;
import com.becksoft.meat.grill.enums.MovementType;
import com.becksoft.meat.grill.enums.PaymentMethod;
import com.becksoft.meat.grill.enums.WorkWeekStatus;
import com.becksoft.meat.grill.repository.*;
import com.becksoft.meat.grill.service.CashSessionService;
import com.becksoft.meat.grill.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CashSessionServiceImpl implements CashSessionService {

    private final CashSessionRepository cashSessionRepository;
    private final PaymentRepository paymentRepository;
    private final MovementRepository movementRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkWeekService workWeekService;

    @Override
    @Transactional
    public CashSession openSession(Long employeeId, BigDecimal openingCash) {
        cashSessionRepository.findByStatus(CashSessionStatus.OPEN)
                .ifPresent(s -> {throw new IllegalStateException("Ya existe una sesión de caja abierta.");});
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el employee con el id: " + employeeId));
        WorkWeek validWeek = workWeekService.getOrCreateValidWorkWeek();
        CashSession newSession = new CashSession(validWeek, employee, openingCash);
        return cashSessionRepository.save(newSession);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateExpectedCash(Long sessionId) {
        CashSession session = cashSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el cash session con el id: " + sessionId));
        BigDecimal expected = session.getOpeningCash();
        BigDecimal cashVentas = paymentRepository.sumAmountBySessionAndMethod(sessionId, PaymentMethod.EFECTIVO);
        expected = expected.add(cashVentas);
        BigDecimal ingresosManuales = movementRepository.sumAmountBySessionAndType(sessionId, MovementType.INGRESO);
        expected = expected.add(ingresosManuales);
        BigDecimal egresosManuales = movementRepository.sumAmountBySessionAndType(sessionId, MovementType.EGRESO);
        expected = expected.subtract(egresosManuales);
        return expected;
    }

    @Override
    @Transactional
    public CashSession closeSession(Long sessionId, BigDecimal realClosingCash) {
        CashSession session = cashSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Sesión de caja no encontrada"));

        if (session.getStatus() == CashSessionStatus.CLOSED) {
            throw new IllegalStateException("Esta sesión ya se encuentra cerrada");
        }

        BigDecimal expectedCash = calculateExpectedCash(sessionId);

        session.cerrarCaja(realClosingCash, expectedCash);

        return cashSessionRepository.save(session);
    }
}
