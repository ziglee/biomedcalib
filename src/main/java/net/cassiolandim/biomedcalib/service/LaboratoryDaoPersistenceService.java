package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.LaboratoryDao;
import net.cassiolandim.biomedcalib.entity.Laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.Search;

/**
 * @author Cassio Landim
 */
@Transactional
public class LaboratoryDaoPersistenceService implements LaboratoryPersistableService {

	private LaboratoryDao laboratoryDao;

	@Autowired
	public void setLaboratoryDao(LaboratoryDao dao){
		this.laboratoryDao = dao;
	}
	
	public Laboratory find(Long id) {
		return laboratoryDao.find(id);
	}

	public List<Laboratory> findAll() {
		Search search = new Search();
		search.addSort("name", false, true);
		return laboratoryDao.search(search);
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
