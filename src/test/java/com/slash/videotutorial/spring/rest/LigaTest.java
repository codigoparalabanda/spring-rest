package com.slash.videotutorial.spring.rest;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.slash.videotutorial.spring.rest.domain.Equipo;
import com.slash.videotutorial.spring.rest.domain.Liga;
import com.slash.videotutorial.spring.rest.service.LigaService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LigaTest {

	@Autowired
	private LigaService ligaService;
	
	@Test
	@Ignore
	public void findAll() {
		log.info("**** findAll Test ****");
		
		Set<Liga> ligas = ligaService.findAll();
		assertFalse("El objeto ligas está vacío", ligas.isEmpty());
		
		ligas.stream().forEach(liga -> {
			log.info("{}", liga);
			liga.getEquipos().stream().forEach(equipo -> log.info("\t{}", equipo));
		});
	}
	
	@Test
	@Ignore
	public void findById() {
		log.info("**** findById Test ****");
		
		Optional<Liga> liga = ligaService.findById((short) 2);
		assertTrue("El objeto liga es nulo", liga.isPresent());
		
		log.info("{}", liga.get());
		liga.get().getEquipos()
			.stream()
			.sorted(Comparator.comparing(Equipo :: getId))
			.forEach(equipo -> log.info("\t{}", equipo));
	}
	
	@Test
	@Ignore
	public void add() {
		log.info("**** add Test ****");
		
		Liga liga = new Liga("Liga de prueba");
		ligaService.add(liga);
		
		assertNotNull("El id de la liga es nulo", liga.getId());
		log.info("{}", liga);
	}
	
	@Test
	@Ignore
	public void update() {
		log.info("**** update Test ****");
		
		short id = (short) 2;
		
		Liga liga = ligaService.findById(id).get();
		liga.setNombre("Liga Premier de Inglaterra");
		
		ligaService.update(liga);
		
		Liga updatedLiga = ligaService.findById(id).get();
		
		assertEquals(liga.getNombre(), updatedLiga.getNombre());
		log.info("{}", updatedLiga);
	}
	
	@Test
	public void remove() {
		log.info("**** remove Test ****");
		
		short id = (short) 2;
		ligaService.removeById(id);
		
		Optional<Liga> liga = ligaService.findById(id);
		assertFalse("La liga con el id " + id + " no se eliminó", liga.isPresent());
		
		log.info("{}", liga);
	}
}
