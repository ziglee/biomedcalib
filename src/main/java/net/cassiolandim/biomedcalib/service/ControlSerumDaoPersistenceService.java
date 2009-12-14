package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.ControlSerumDao;
import net.cassiolandim.biomedcalib.entity.ControlSerum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.Search;

/**
 * @author Cassio Landim
 */
@Transactional
public class ControlSerumDaoPersistenceService implements ControlSerumPersistableService {

	private ControlSerumDao controlSerumDao;

	@Autowired
	public void setControlSerumDao(ControlSerumDao dao){
		this.controlSerumDao = dao;
	}
	
	public ControlSerum find(Long id) {
		return controlSerumDao.find(id);
	}

	public List<ControlSerum> findAll() {
		Search search = new Search();
		search.addSort("name", false, true);
		return controlSerumDao.search(search);
	}

	public void persist(ControlSerum entity) {
		controlSerumDao.persist(entity);
	}

	public boolean remove(ControlSerum entity) {
		return controlSerumDao.remove(entity);
	}

	public void save(ControlSerum entity) {
		controlSerumDao.save(entity);
	}
}
