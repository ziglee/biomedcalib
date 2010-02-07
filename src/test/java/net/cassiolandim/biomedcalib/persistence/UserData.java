package net.cassiolandim.biomedcalib.persistence;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserPersistableService;

/**
 * @author Cassio Landim
 */
public class UserData {

	private static long idIncrement = 0;
	private final List<User> list = new ArrayList<User>();
	private final MockUserService userService = new MockUserService(list);

	public void newUser(User user) {
		user.setId(++idIncrement);
		list.add(user);
	}

	public MockUserService getUserService() {
		return userService;
	}

	public boolean isUserDaoDeleteCalled() {
		return userService.getDeleteCalled();
	}

	public boolean isUserDaoSaveCalled() {
		return userService.getSaveCalled();
	}

	/**
	 * @author Cassio Landim
	 */
	private final class MockUserService extends MockListPersistenceService<User> implements UserPersistableService {

		public MockUserService(List<User> list) {
			super(list);
		}

		public void save(User user) {
			User userFound = find(user.getId());
			if(userFound != null){
				userFound.setName(user.getName());
				userFound.setLaboratory(user.getLaboratory());
			}else{
				persist(user);
			}

			setSaveCalled(true);
		}

		@Override
		public User findByLogin(String login) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
