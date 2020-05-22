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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class EtramApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration configurationSource;
	
	public static void main(String[] args) {
		SpringApplication.run(EtramApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		configurationSource.exposeIdsFor(Station.class,Horaire.class,Tram.class,Voyage.class,Voyageur.class,Ticket.class,Solde.class,ResGuichet.class,Admin.class);
	}

	
}
