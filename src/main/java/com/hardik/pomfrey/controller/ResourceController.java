package com.hardik.pomfrey.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.pomfrey.request.ResourceCreationRequest;
import com.hardik.pomfrey.request.ResourceDetailUpdationRequest;
import com.hardik.pomfrey.request.ResourceStateUpdationRequest;
import com.hardik.pomfrey.service.ResourceService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/resource")
public class ResourceController {

	private final ResourceService resourceService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Creates A New Resource In The Database")
	public ResponseEntity<?> resourceCreationHandler(
			@Valid @RequestBody(required = true) final ResourceCreationRequest resourceCreationRequest) {
		return resourceService.create(resourceCreationRequest,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Updates Resource Details In The Database")
	public ResponseEntity<?> resourceDetailUpdationRequest(
			@Valid @RequestBody(required = true) final ResourceDetailUpdationRequest resourceDetailUpdationRequest) {
		return resourceService.update(resourceDetailUpdationRequest,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@PutMapping(value = "/state", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Updates Resource State (In-Active) In The Database")
	public ResponseEntity<?> resourceStateUpdationHandler(
			@Valid @RequestBody(required = true) final ResourceStateUpdationRequest resourceStateUpdationRequest) {
		return resourceService.update(resourceStateUpdationRequest,
				SecurityContextHolder.getContext().getAuthentication().getName());
	}

}