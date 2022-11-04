package com.filme.goldenawords.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.filme.goldenawords.model.Movie;
import com.filme.goldenawords.model.Producer;
import com.filme.goldenawords.model.ProducerMovie;
import com.filme.goldenawords.model.Studio;
import com.filme.goldenawords.model.StudioMovie;
import com.filme.goldenawords.repository.MovieRepository;
import com.filme.goldenawords.repository.ProducerMovieRepository;
import com.filme.goldenawords.repository.ProducerRepository;
import com.filme.goldenawords.repository.StudioMovieRepository;
import com.filme.goldenawords.repository.StudioRepository;

import ch.qos.logback.core.Layout;
import lombok.var;

@Service
public class UploadFileServiceAdapter implements ApplicationRunner{
	
	private static final String YEAR = "year";
	
	private static final String TITLE = "title";
	
	private static final String STUDIOS = "studios";
	
	private static final String PRODUCERS = "producers";
	
	private static final String WINNER = "winner";
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private StudioRepository studioRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private StudioMovieRepository studioMovieRepository;
	
	@Autowired
	private ProducerMovieRepository producerMovieRepository;
	
	private static final Logger LOGGER = Logger.getLogger(UploadFileServiceAdapter.class.getName());

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".csv");
			}
		};
		
		FileReader fr = null;
		
		try {
			String path ="src\\main\\resources\\file";
			File dir = new File(path);
			
			LOGGER.log(Level.INFO,"Looking for .csv file");
			File[] file = dir.listFiles(filter);
			
			
			if(file.length > 0) {
				LOGGER.log(Level.INFO,"Read file .csv");
				fr = new FileReader(file[0]);
			}else{
				
				LOGGER.log(Level.INFO,"File .csv not found ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(fr != null) {
			LOGGER.log(Level.INFO,"Created buffer reader");
			try (BufferedReader br = new BufferedReader(fr)) {
				
				LOGGER.log(Level.INFO,"Read first line");
				String line = br.readLine();
				var layout = line.split(";");
				
				if(layout.length == 5 && YEAR.equals(layout[0].toLowerCase().trim())
						&& TITLE.equals(layout[1].toLowerCase().trim())
						&& STUDIOS.equals(layout[2].toLowerCase().trim())
						&& PRODUCERS.equals(layout[3].toLowerCase().trim())
						&& WINNER.equals(layout[4].toLowerCase().trim())
						) {
					
					line = br.readLine();
					LOGGER.log(Level.INFO,"Loop Read line");
					while(line != null) {
						
						String[] movieVet = line.split(";");
						
						Movie mov = movieRepository.findByTitle(movieVet[1]);
						
						if(mov == null) {
							
							Movie m = Movie.builder()
									.year(movieVet[0])
									.title(movieVet[1])
									.winner(movieVet.length ==5? movieVet[4]:"").build();
							
							movieRepository.save(m);
							
							mov =  movieRepository.getMaxId(); 
							
							var stMovList = getStudiosMovieList(movieVet[2], mov);
							
							var pdMovList = getProducersMovieList(movieVet[3], mov);
							
							mov.setStudiosMovieList(stMovList);
							mov.setProducersMovieList(pdMovList);
							
							movieRepository.save(mov);
						}
						
						
						line = br.readLine();
					}
					
					LOGGER.log(Level.INFO,"Finish Loop Read line");
					
				} else {
					
					LOGGER.log(Level.INFO,"Invalid file layout");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				fr.close();
			}
		}
		
	}

	private List<ProducerMovie> getProducersMovieList(String string, Movie mov) {

		List<ProducerMovie> pM = new ArrayList<ProducerMovie>();
		
		var producersList = getListProducers(string);
		
		for(Producer prd : producersList) {
			
			producerMovieRepository.save(ProducerMovie.builder()
					.movie(mov)
					.producer(prd).build());
			
			var pdMv = producerMovieRepository.getMaxId();
			
			if(pdMv != null) {
				
				pM.add(pdMv);
			}
		}
		return pM;
	}

	private List<StudioMovie> getStudiosMovieList(String studioString, Movie movie) {
		
		List<StudioMovie> sM = new ArrayList<StudioMovie>();
		
		var studioList = getStudiosList(studioString);
		
		for (Studio stdMov : studioList) {
			
			studioMovieRepository.save(StudioMovie.builder()
					.movie(movie).studio(stdMov).build());
			
			var stMov = studioMovieRepository.getMaxId();
			
			if(stMov != null) {
				
				sM.add(stMov);	
			}
			
		}
		
		return sM;
	}

	private List<Producer> getListProducers(String producers) {
		
		var listProducers = producers;
		listProducers = listProducers.replace(".", "").replace(" and ",",");
		listProducers = listProducers.replace(",,",",");
		String[] vetProducers = listProducers.split(",");
		
		List<Producer> list = new ArrayList<Producer>();
		
		for (int i = 0; i < vetProducers.length; i++) {

			Producer producer = producerRepository.findByName(vetProducers[i]);
			if (producer == null) {

				producerRepository.save(Producer.builder().name(vetProducers[i]).build());
				producer = producerRepository.getMaxId();
			}
			list.add(producer);

		}
		return list;
	}

	private List<Studio> getStudiosList(String string) {
		
		var listStudio = string;
		listStudio = listStudio.replace("and", ",").replace(".", "");
		String[] vetStudio = listStudio.split(",");
		List<Studio> studioList = new ArrayList<Studio>();
		
		for (int i = 0; i < vetStudio.length; i++) {
			
			Studio studio = studioRepository.findByName(vetStudio[i]);
			if(studio == null) {
				
				studioRepository.save(Studio.builder().name(vetStudio[i]).build());
				studio = studioRepository.getMaxId();
			}
			
			studioList.add(studio);
		}
		return studioList;
	}
}
