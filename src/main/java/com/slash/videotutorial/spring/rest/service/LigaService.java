package com.slash.videotutorial.spring.rest.service;

import java.util.Optional;
import java.util.Set;

import com.slash.videotutorial.spring.rest.domain.Liga;

public interface LigaService {

	public Set<Liga> findAll();
	
	public Optional<Liga> findById(Short id);
	
	public Liga add(Liga liga);
	
	public Liga update(Liga ligaUpdate);
	
	public void removeById(Short id);
}
