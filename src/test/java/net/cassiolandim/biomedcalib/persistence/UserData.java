package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserSimplePersistableService;

/**
 * @author Cassio Landim
 */
public class UserData {

	private static long idIncrement = 0;
	private final List<User> users = new ArrayList<User>();
	private final MockUserService userService = new MockUserService();

	public void newUser(User user) {
		user.setId(++idIncrement);
		users.add(user);
	}

	public MockUserService getUserService() {
		return userService;
	}

	public boolean isUserDaoDeleteCalled() {
		return userService.deleteCalled;
	}

	public boolean isUserDaoSaveCalled() {
		return userService.saveCalled;
	}

	/**
	 * @author Cassio Landim
	 */
	private final class MockUserService implements UserSimplePersistableService {

		private boolean deleteCalled;
		private boolean saveCalled;

		public boolean remove(User user) {
			deleteCalled = true;
			users.remove(find(user.getId()));
			return true;
		}

		public User find(Long id) {
			for (User user : users) {
				if (user.getId() == id) {
					return user;
				}
			}
			return null;
		}

		public void persist(User user) {
			user.setId(++idIncrement);
			users.add(user);
			saveCalled = true;
		}

		public void save(User user) {
			User userFound = find(user.getId());
			if(userFound != null){
				userFound.setName(user.getName());
			}else{
				persist(user);
			}

			saveCalled = true;
		}
		
		public List<User> findAll() {
			Collections.sort(users);
			return users;
		}
	}
}
