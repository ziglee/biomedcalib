package net.cassiolandim.biomedcalib.service;

import java.util.Date;
import java.util.List;

import net.cassiolandim.biomedcalib.dao.MeasureDao;
import net.cassiolandim.biomedcalib.dao.MeasuresAggregateDao;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.Search;

/**
 * @author Cassio Landim
 */
@Transactional
public class MeasuresAggregateDaoPersistenceService implements MeasuresAggregatePersistableService {

	private MeasuresAggregateDao measuresAggregateDao;
	private MeasureDao measureDao;

	@Autowired
	public void setMeasuresAggregateDao(MeasuresAggregateDao dao){
		this.measuresAggregateDao = dao;
	}
	
	@Autowired
	public void setMeasureDao(MeasureDao measureDao) {
		this.measureDao = measureDao;
	}
	
	public MeasuresAggregate find(Long id) {
		return measuresAggregateDao.find(id);
	}

	public List<MeasuresAggregate> findAll() {
		Search search = new Search();
		search.addSort("laboratory.name", false, true);
		search.addSort("firstDate", false, true);
		search.addSort("controlSerum.manufacturer", false, true);
		search.addSort("controlSerum.name", false, true);
		return measuresAggregateDao.search(search);
	}
	
	public List<MeasuresAggregate> findByLaboratory(Laboratory laboratory) {
		Search search = new Search();
		search.addFilterEqual("laboratory.id", laboratory.getId());
		search.addSort("laboratory.name", false, true);
		search.addSort("firstDate", false, true);
		search.addSort("controlSerum.manufacturer", false, true);
		search.addSort("controlSerum.name", false, true);
		return measuresAggregateDao.search(search);
	}
	
	public List<MeasuresAggregate> findActiveByLaboratory(Laboratory laboratory, Date firstDate, Date lastDate) {
		Search search = new Search();
		
		search.addFilterEqual("active", true);
		search.addFilterEqual("laboratory.id", laboratory.getId());
		
		if(firstDate != null) search.addFilterGreaterOrEqual("firstDate", firstDate);
		if(lastDate != null) search.addFilterLessOrEqual("lastDate", lastDate);
		
		search.addSort("laboratory.name", false, true);
		search.addSort("firstDate", false, true);
		search.addSort("controlSerum.manufacturer", false, true);
		search.addSort("controlSerum.name", false, true);
		return measuresAggregateDao.search(search);
	}

	public void persist(MeasuresAggregate entity) {
		for(Measure measure : entity.getMeasures()){
			persist(measure);
		}
		entity.setFirstAndLastDate();
		measuresAggregateDao.persist(entity);
	}

	public boolean remove(MeasuresAggregate entity) {
		return measuresAggregateDao.remove(entity);
	}

	public void save(MeasuresAggregate entity) {
		for(Measure measure : entity.getMeasures()){
			save(measure);
		}
		entity.setFirstAndLastDate();
		measuresAggregateDao.save(entity);
	}

	private void persist(Measure measure) {
		measureDao.persist(measure);
	}

	private void save(Measure measure) {
		measureDao.save(measure);
	}
	
	public MeasuresAggregate archive(MeasuresAggregate entity) {
		entity.setActive(Boolean.FALSE);
		measuresAggregateDao.save(entity);
		return entity;
	}
	
	public MeasuresAggregate unarchive(MeasuresAggregate entity) {
		entity.setActive(Boolean.TRUE);
		measuresAggregateDao.save(entity);
		return entity;
	}
}
