package org.sid;





import org.sid.Etram2.Entities.Admin;
import org.sid.Etram2.Entities.Horaire;
import org.sid.Etram2.Entities.ResGuichet;
import org.sid.Etram2.Entities.Solde;
import org.sid.Etram2.Entities.Station;
import org.sid.Etram2.Entities.Ticket;
import org.sid.Etram2.Entities.Tram;
import org.sid.Etram2.Entities.Voyage;
import org.sid.Etram2.Entities.Voyageur;
import org.sid.Etram2.dao.AdminRepository;
import org.sid.Etram2.dao.HoraireRepository;
import org.sid.Etram2.dao.ResGuichetRepository;
import org.sid.Etram2.dao.StationRepository;
import org.sid.Etram2.dao.TramRepository;
import org.sid.Etram2.dao.VoyageRepository;
import org.sid.Etram2.dao.VoyageurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class EtramApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration configurationSource;
	
	@Autowired
	private TramRepository tramRepository;
	
	@Autowired
	private HoraireRepository horaireRepository;
	
	@Autowired
	private VoyageRepository voyageRepository;
	
	@Autowired
	private StationRepository stationRepository;
	
	@Autowired
	private ResGuichetRepository resGuichetRepository;
	
	@Autowired 
	private AdminRepository adminRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EtramApplication.class, args);
		
	}




	@Override
	public void run(String... args) throws Exception {
		configurationSource.exposeIdsFor(Station.class,Horaire.class,Tram.class,Voyage.class,Voyageur.class,Ticket.class,Solde.class,ResGuichet.class,Admin.class);
		/*Tram T = tramRepository.getOne("T145");
		T.setNom("Tram145");
		T.setRole("tram");
		tramRepository.save(T);
		Admin A = adminRepository.getOne("JT77690");
		A.setEmail("mohamedelmimouni@student.emi.ac.ma");
		A.setTel("0671990829");
		adminRepository.save(A);*/
		/*Horaire H = horaireRepository.getOne((long)1);
				H.setHeure("00:30");
				H.setDate("2020-05-23");
		horaireRepository.save(H);*/
		/*
		Voyage V = voyageRepository.getOne((long)1);
		Station S = stationRepository.getOne((long)1);
		horaireRepository.save( new Horaire((long)1,"2020/05/22","12:00",S,V,null) );
		*/
		//resGuichetRepository.save(new ResGuichet("H177013","Zarrouk","Abdellah","Agdal Rabat","abdellahzarrouk@student.emi.ac.ma","anime","0607080910","respo",0.00,null));
	}

	
}
