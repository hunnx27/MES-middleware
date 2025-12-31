package com.kongju.middleware.alarmLog.service;

import com.kongju.middleware.alarmLog.repository.AlarmLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmLogService {

    private final AlarmLogRepository repository;


}