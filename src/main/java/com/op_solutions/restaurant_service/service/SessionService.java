package com.op_solutions.restaurant_service.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.op_solutions.restaurant_service.dtos.SessionResponse;
import com.op_solutions.restaurant_service.exception.InvalidOperationException;
import com.op_solutions.restaurant_service.exception.ResourceNotFoundException;
import com.op_solutions.restaurant_service.models.GuestSession;
import com.op_solutions.restaurant_service.models.Table;
import com.op_solutions.restaurant_service.models.TableStatus;
import com.op_solutions.restaurant_service.repository.SessionRepository;
import com.op_solutions.restaurant_service.repository.TableRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {
	
	private final SessionRepository sessionRepository;
	private final TableRepository tableRepository;

	@Transactional
	public SessionResponse startSession(Long tableId) throws InvalidOperationException {
		
		Table table = tableRepository.findById(tableId).orElseThrow(() -> new ResourceNotFoundException("Table Not found"));
		
		if(!table.getStatus().equals(TableStatus.AVAILABLE))
		{
			throw new InvalidOperationException("Table already occupied");
		}
		
		table.setStatus(TableStatus.OCCUPIED);
		
		GuestSession session = new GuestSession();
		session.setActive(true);
		session.setTableId(tableId);
		session.setStartedAt(LocalDateTime.now());
		session.setSessionId(UUID.randomUUID());
		
		tableRepository.save(table);
		sessionRepository.save(session);
		
		SessionResponse response = new SessionResponse();
        response.setSessionId(session.getSessionId());
        response.setTableId(tableId);
        response.setStartedAt(session.getStartedAt());
        return response;
		
	}

	@Transactional
	public void endSession(UUID sessionId) throws InvalidOperationException {
	
		GuestSession session = getActiveSession(sessionId);
		
		session.setActive(false);
		
		Table table = tableRepository.findById(session.getTableId()).orElseThrow(() -> new ResourceNotFoundException("Table Not found"));
		
		table.setStatus(TableStatus.AVAILABLE);
		
		sessionRepository.save(session);
		tableRepository.save(table);
		
	}

	public GuestSession getActiveSession(UUID sessionId) throws InvalidOperationException {
		
		GuestSession session =  sessionRepository.findById(sessionId).orElseThrow(() -> new ResourceNotFoundException("SessionId not found"));
		
		
		if(session.isActive())
		{
			return session;
		}
		
		throw new InvalidOperationException("Session is Already expired");
		
		
		
	}

}
