package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.BaseEntity;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public interface PersistableService<T extends BaseEntity<T>> {

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public void save(T entity);
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public void persist(T entity);

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
	public boolean remove(T entity);

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = 30)
	public List<T> findAll();
	
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = 30)
	public T find(Long id);
}
