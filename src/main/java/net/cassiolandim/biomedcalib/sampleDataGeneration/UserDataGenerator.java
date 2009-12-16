package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserPersistableService;

/**
 * @author Cassio Landim
 */
public class UserDataGenerator {

	public static final String[] NAMES = { "Cássio" , "Cíntia", "Landmary", "Maclemes" };
	
	public static List<User> generateData(UserPersistableService userService, List<Laboratory> laboratories){
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < NAMES.length; i++){
			User user = new User();
			user.setName(NAMES[i]);
			user.setLaboratory(laboratories.get(i % laboratories.size()));
			userService.persist(user);
			list.add(user);
		}
		return list;
	}
}
