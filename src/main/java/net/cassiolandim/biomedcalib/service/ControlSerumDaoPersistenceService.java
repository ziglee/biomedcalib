package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.ControlSerumDao;
import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;

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

	@Override
	public ControlSerum find(Long id) {
		return controlSerumDao.find(id);
	}

	@Override
	public List<ControlSerum> findAll() {
		Search search = new Search();
		search.addSort("name", false, true);
		return controlSerumDao.search(search);
	}

	@Override
	public List<ControlSerum> findAllActive() {
		Search search = new Search();
		search.addFilterNotEqual("status", ControlSerum.STATUS_INACTIVE);
		return controlSerumDao.search(search);
	}

	@Override
	public List<ControlSerum> findByLaboratory(Laboratory laboratory) {
		Search search = new Search();
		search.addFilterEqual("laboratory.id", laboratory.getId());
		return controlSerumDao.search(search);
	}

	@Override
	public void persist(ControlSerum entity) {
		controlSerumDao.persist(entity);
	}

	@Override
	public boolean remove(ControlSerum entity) {
		return controlSerumDao.remove(entity);
	}

	@Override
	public void save(ControlSerum entity) {
		controlSerumDao.save(entity);
	}
}
