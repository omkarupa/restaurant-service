package com.op_solutions.restaurant_service.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op_solutions.restaurant_service.dtos.SessionResponse;
import com.op_solutions.restaurant_service.exception.InvalidOperationException;
import com.op_solutions.restaurant_service.service.SessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {
	
	private final SessionService sessionService;
	
	
	@PostMapping("/start")
	public SessionResponse startSession(@RequestParam Long tableId) throws InvalidOperationException
	{
		return sessionService.startSession(tableId);
	}

	@PostMapping("/end/{sessionId}")
	public void endSession(@PathVariable UUID sessionId) throws InvalidOperationException
	{
		sessionService.endSession(sessionId);
	}
}
