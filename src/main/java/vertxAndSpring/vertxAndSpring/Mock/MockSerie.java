package vertxAndSpring.vertxAndSpring.Mock;

import org.springframework.beans.factory.annotation.Autowired;

import vertxAndSpring.vertxAndSpring.entity.Serie;
import vertxAndSpring.vertxAndSpring.repository.SerieRepository;

public class MockSerie {
	
	private SerieRepository serieRepository;
	
	public MockSerie (SerieRepository serieRepository){
		this.serieRepository=serieRepository;
	}
	
	
	public void savemock(){
		serieRepository.save(new Serie(1,"Breaking bad",5));
		serieRepository.save(new Serie(2,"Walking dead",5));
		serieRepository.save(new Serie(3,"Game of thrones",7));
	}
}
