package net.cassiolandim.biomedcalib.service;

import net.cassiolandim.biomedcalib.entity.User;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public interface UserPersistableService extends PersistableService<User> {

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED, readOnly = true, timeout = 30)
	public User findByLogin(String login);
	
}
