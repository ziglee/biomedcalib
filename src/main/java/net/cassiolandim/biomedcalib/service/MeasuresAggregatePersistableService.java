package net.cassiolandim.biomedcalib.service;

import java.util.Date;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;

/**
 * @author Cassio Landim
 */
public interface MeasuresAggregatePersistableService extends PersistableService<MeasuresAggregate> {

	MeasuresAggregate archive(MeasuresAggregate measuresAggregate);
	MeasuresAggregate unarchive(MeasuresAggregate measuresAggregate);
	List<MeasuresAggregate> findByLaboratory(Laboratory laboratory);
	List<MeasuresAggregate> findActiveByLaboratory(Laboratory laboratory, Date firstDate, Date lastDate);
}
