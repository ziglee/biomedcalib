package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.MeasureDao;
import net.cassiolandim.biomedcalib.dao.MeasuresAggregateDao;
import net.cassiolandim.biomedcalib.dao.MeasuresPerLevelDao;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.Search;

/**
 * @author Cassio Landim
 */
@Transactional
public class MeasuresAggregateDaoPersistenceService implements MeasuresAggregatePersistableService {

	private MeasuresAggregateDao measuresAggregateDao;
	private MeasuresPerLevelDao measuresPerLevelDao;
	private MeasureDao measureDao;

	@Autowired
	public void setMeasuresAggregateDao(MeasuresAggregateDao dao){
		this.measuresAggregateDao = dao;
	}
	
	@Autowired
	public void setMeasuresPerLevelDao(MeasuresPerLevelDao measuresPerLevelDao) {
		this.measuresPerLevelDao = measuresPerLevelDao;
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
		search.addSort("creationDate", false, true);
		return measuresAggregateDao.search(search);
	}

	public void persist(MeasuresAggregate entity) {
		persist(entity.getMeasures1());
		persist(entity.getMeasures2());
		persist(entity.getMeasures3());
		measuresAggregateDao.persist(entity);
	}

	public boolean remove(MeasuresAggregate entity) {
		return measuresAggregateDao.remove(entity);
	}

	public void save(MeasuresAggregate entity) {
		measuresAggregateDao.save(entity);
	}

	private void persist(MeasuresPerLevel measurePerLevel) {
		for(Measure measure : measurePerLevel.getMeasures()){
			persist(measure);
		}
		measuresPerLevelDao.persist(measurePerLevel);
	}

	private void persist(Measure measure) {
		measureDao.persist(measure);
	}
}
