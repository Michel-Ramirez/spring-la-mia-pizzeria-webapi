package org.java.db.serv;

import java.util.List;

import org.java.db.pojo.Offert;
import org.java.db.repo.OffertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertService {

	@Autowired
	private OffertRepository offertRepository;

	public List<Offert> findAll() {
		return offertRepository.findAll();
	}

	public Offert findById(int id) {
		return offertRepository.findById(id).get();
	}

	public void save(Offert offert) {
		offertRepository.save(offert);
	}

	public void delete(Offert offert) {
		offertRepository.delete(offert);
	}
}
