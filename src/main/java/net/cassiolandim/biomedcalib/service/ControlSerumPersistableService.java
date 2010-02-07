package net.cassiolandim.biomedcalib.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;

/**
 * @author Cassio Landim
 */
public interface ControlSerumPersistableService extends PersistableService<ControlSerum> {
	
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = 30)
	public List<ControlSerum> findAllActive();
	
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = 30)
	public List<ControlSerum> findByLaboratory(Laboratory laboratory);
	
}
