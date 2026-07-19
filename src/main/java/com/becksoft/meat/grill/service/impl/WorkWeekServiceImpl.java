package com.becksoft.meat.grill.service.impl;

import com.becksoft.meat.grill.entity.WorkWeek;
import com.becksoft.meat.grill.entity.WorkWeekTemplate;
import com.becksoft.meat.grill.enums.WorkWeekStatus;
import com.becksoft.meat.grill.repository.WorkWeekRepository;
import com.becksoft.meat.grill.repository.WorkWeekTemplateRepository;
import com.becksoft.meat.grill.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkWeekServiceImpl implements WorkWeekService {

    private final WorkWeekRepository workWeekRepository;
    private final WorkWeekTemplateRepository workWeekTemplateRepository;

    @Override
    @Transactional
    public WorkWeek getOrCreateValidWorkWeek() {

        LocalDateTime now = LocalDateTime.now();

        Optional<WorkWeek> weekActiveOpt = workWeekRepository.findByStatus(WorkWeekStatus.ABIERTA);

        if(weekActiveOpt.isPresent()) {

            WorkWeek weekActual = weekActiveOpt.get();

            if (now.isBefore(weekActual.getEndDate())) {
                return weekActual;
            }

            weekActual.cerrarSemana();
            workWeekRepository.save(weekActual);

        }

        WorkWeekTemplate config = workWeekTemplateRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("La configuración de la semana no esta creada."));

        LocalDateTime inicioCalculado = now.with(TemporalAdjusters.previousOrSame(config.getStartDay()))
                .with(config.getStartTime())
                .withSecond(0).withNano(0);

        if (weekActiveOpt.isPresent()) {
            if (now.isBefore(inicioCalculado)) {
                inicioCalculado = inicioCalculado.minusDays(1);
            }
        } else {
            if (now.isBefore(inicioCalculado)) {
                inicioCalculado = now;
            }
        }

        LocalDateTime finalCalculado = inicioCalculado.plusWeeks(1).with(config.getEndTime());

        WorkWeek nuevaWeek = new WorkWeek(inicioCalculado, finalCalculado);

        return workWeekRepository.save(nuevaWeek);

    }
}
