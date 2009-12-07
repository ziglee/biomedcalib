package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.LaboratoryDao;
import net.cassiolandim.biomedcalib.entity.Laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LaboratorySimplePersistenceService implements LaboratorySimplePersistableService {

	private LaboratoryDao laboratoryDao;

	@Autowired
	public void setLaboratoryDao(LaboratoryDao dao){
		this.laboratoryDao = dao;
	}
	
	public Laboratory find(Long id) {
		return laboratoryDao.find(id);
	}

	public List<Laboratory> findAll() {
		return laboratoryDao.findAll();
	}

	public void persist(Laboratory entity) {
		laboratoryDao.persist(entity);
	}

	public boolean remove(Laboratory entity) {
		return laboratoryDao.remove(entity);
	}

	public void save(Laboratory entity) {
		laboratoryDao.save(entity);
	}
}
