package com.becksoft.meat.grill.service;

import com.becksoft.meat.grill.entity.CashSession;

import java.math.BigDecimal;

public interface CashSessionService {

    CashSession openSession(Long employeeId, BigDecimal openingCash);

    BigDecimal calculateExpectedCash(Long sessionId);

    CashSession closeSession(Long sessionId, BigDecimal realClosingCash);

}
