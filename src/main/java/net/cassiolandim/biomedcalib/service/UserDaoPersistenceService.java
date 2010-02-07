package net.cassiolandim.biomedcalib.service;

import java.util.List;

import net.cassiolandim.biomedcalib.dao.UserDao;
import net.cassiolandim.biomedcalib.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.Search;

/**
 * @author Cassio Landim
 */
@Transactional
public class UserDaoPersistenceService implements UserPersistableService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao dao){
		this.userDao = dao;
	}

	@Override
	public User find(Long id) {
		return userDao.find(id);
	}

	@Override
	public User findByLogin(String login) {
		Search search = new Search();
		search.addFilterEqual("login", login);
		return userDao.searchUnique(search);
	}

	@Override
	public List<User> findAll() {
		Search search = new Search();
		search.addSort("name", false, true);
		return userDao.search(search);
	}

	@Override
	public void persist(User entity) {
		userDao.persist(entity);
	}

	@Override
	public boolean remove(User entity) {
		return userDao.remove(entity);
	}

	@Override
	public void save(User entity) {
		userDao.save(entity);
	}
}
