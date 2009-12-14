package net.cassiolandim.biomedcalib.persistence;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.web.MockContext;

public class UserFixture {

	public static final String[] NAMES = { "Cássio" , "Cíntia", "Landmary", "Maclemes" };
	
	private final UserData userData = new UserData();

	public void addStubs(MockContext context, List<Laboratory> labs) {
		context.putBean("userPersistableService", userData.getUserService());
		for (int i = 0; i < NAMES.length; i++) {
			User user = new User();
			user.setName(NAMES[i]);
			user.setLaboratory(labs.get(i % 4));
			userData.newUser(user);
		}
	}

	public UserData getUserData() {
		return userData;
	}
}
