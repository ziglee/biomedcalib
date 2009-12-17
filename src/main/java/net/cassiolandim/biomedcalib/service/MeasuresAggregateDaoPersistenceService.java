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
		measuresAggregateDao.persist(entity);
	}

	public boolean remove(MeasuresAggregate entity) {
		return measuresAggregateDao.remove(entity);
	}

	public void save(MeasuresAggregate entity) {
		save(entity.getMeasures1());
		save(entity.getMeasures2());
		save(entity.getMeasures3());
		measuresAggregateDao.save(entity);
	}

	private void save(MeasuresPerLevel measurePerLevel) {
		for(Measure measure : measurePerLevel.getMeasures()){
			save(measure);
		}
		measuresPerLevelDao.save(measurePerLevel);
	}

	private void save(Measure measure) {
		measureDao.save(measure);
	}
}
