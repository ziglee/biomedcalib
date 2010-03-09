package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;

/**
 * @author Cassio Landim
 */
public interface MeasuresAggregatePersistableService extends PersistableService<MeasuresAggregate> {

	List<MeasuresAggregate> findByLaboratory(Laboratory laboratory);
}
