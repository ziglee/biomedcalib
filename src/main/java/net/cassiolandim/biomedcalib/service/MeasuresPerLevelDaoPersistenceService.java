package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.MeasureDao;
import net.cassiolandim.biomedcalib.dao.MeasuresPerLevelDao;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
@Transactional
public class MeasuresPerLevelDaoPersistenceService implements MeasuresPerLevelPersistableService {

	private MeasuresPerLevelDao measuresPerLevelDao;
	private MeasureDao measureDao;

	@Autowired
	public void setMeasuresPerLevelDao(MeasuresPerLevelDao measuresPerLevelDao) {
		this.measuresPerLevelDao = measuresPerLevelDao;
	}
	
	@Autowired
	public void setMeasureDao(MeasureDao measureDao) {
		this.measureDao = measureDao;
	}
	
	public MeasuresPerLevel find(Long id) {
		return measuresPerLevelDao.find(id);
	}

	public boolean remove(MeasuresPerLevel entity) {
		return measuresPerLevelDao.remove(entity);
	}

	public void save(MeasuresPerLevel measurePerLevel) {
		for(Measure measure : measurePerLevel.getMeasures()){
			save(measure);
		}
		measuresPerLevelDao.save(measurePerLevel);
	}

	private void save(Measure measure) {
		measureDao.save(measure);
	}

	@Override
	@Deprecated
	public List<MeasuresPerLevel> findAll() {
		return null;
	}

	@Override
	@Deprecated
	public void persist(MeasuresPerLevel entity) {
		// TODO Auto-generated method stub
	}
}
