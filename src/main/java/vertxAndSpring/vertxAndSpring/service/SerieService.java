package vertxAndSpring.vertxAndSpring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vertxAndSpring.vertxAndSpring.entity.Serie;
import vertxAndSpring.vertxAndSpring.repository.SerieRepository;



@Service
public class SerieService  {

	@Autowired
	private SerieRepository serieRepository;
	
	public List<Serie> findAll(){
		return serieRepository.findAll();
	}
	
	public void saveSerie(Serie serie) {
		serieRepository.save(serie);
	}
	

	
}
