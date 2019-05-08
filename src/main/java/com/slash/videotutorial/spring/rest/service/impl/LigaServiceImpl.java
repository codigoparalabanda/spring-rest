package com.slash.videotutorial.spring.rest.service.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.slash.videotutorial.spring.rest.domain.Equipo;
import com.slash.videotutorial.spring.rest.domain.Liga;
import com.slash.videotutorial.spring.rest.service.LigaService;

@Service
public class LigaServiceImpl implements LigaService {

	private static final Set<Liga> ligas = new HashSet<>();
	
	private static final String[] equiposMx = {"Atlas", "América", "Cruz Azul", "Guadalajara", "León"
			, "Lobos BUAP", "Monterrey", "Morelia", "Necaxa", "Pachuca"
			, "Puebla", "Querétaro", "Santos", "Tijuana", "Toluca"
			, "U.A.N.L.", "U.N.A.M.", "Veracruz"};
	
	private static final String[] equiposPremierLeague = {"Manchester City", "Liverpool", "Chelsea", "Tottenham Hotspur", "Arsenal"
			,"Manchester United", "Wolverhampton Wanderers", "Everton", "Leicester City", "Watford"
			,"West Ham United", "Crystal Palace", "Bournemouth", "Newcastle United", "Burnley", "Southampton"
			,"Brighton & Hove Albion", "Cardiff City", "Fulham"};
	
	public LigaServiceImpl() {
		if(ligas.isEmpty()) {
			popularLigas();
		}
	}
	
	private void popularLigas() {
		Liga ligaMx = new Liga((short) 1, "Liga MX");
		popularEquiposLiga(ligaMx, equiposMx, (short) 0);
		ligas.add(ligaMx);
		
		Liga ligaPremierInglaterra = new Liga((short) 2, "Premier League");
		popularEquiposLiga(ligaPremierInglaterra, equiposPremierLeague, (short) 20);
		ligas.add(ligaPremierInglaterra);
	}
	
	private void popularEquiposLiga(Liga liga, String[] equipos, Short idInicial) {
		IntStream
			.range(0, equipos.length - 1)
			.forEach(idx -> {
				Short id = (short) (idx + idInicial);
				liga.addEquipo(new Equipo(id, equipos[idx], liga));
			});
	}
	
	public Set<Liga> findAll() {
		return ligas
				.stream()
				.sorted(Comparator.comparing(Liga :: getId))
				.collect(Collectors.toSet());
	}
	
	public Optional<Liga> findById(Short id) {
		return ligas
				.stream()
				.filter(liga -> liga.getId() == id)
				.findFirst();
	}
	
	public Liga add(Liga liga) {
		Optional<Liga> ligaMaxId = ligas.stream().max(Comparator.comparing(Liga :: getId));
		
		if(ligaMaxId.isPresent()) {
			short id = (short) (ligaMaxId.get().getId() + 1);
			liga.setId(id);
		} else {
			liga.setId((short) 1);
		}
		
		ligas.add(liga);
		
		return liga;
	}
	
	public Liga update(Liga ligaUpdate) {
		Optional<Liga> ligaOptional = findById(ligaUpdate.getId());
		
		ligaOptional.ifPresent(liga -> ligas.remove(liga));
		
		ligas.add(ligaUpdate);
		
		return ligaUpdate;
	}
	
	public void removeById(Short id) {
		Optional<Liga> ligaOptional = findById(id);
		
		ligaOptional.ifPresent(liga -> ligas.remove(liga));
	}
}
