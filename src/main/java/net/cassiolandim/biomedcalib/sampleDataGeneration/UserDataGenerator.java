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
	public static final String[] LOGINS = { "cassio" , "cintia", "landmary", "maclemes" };
	public static final Boolean[] ADMIN = { true , false, false, false };
	public static final String[] HASHES = { "E10ADC3949BA59ABBE56E057F20F883E" , "E10ADC3949BA59ABBE56E057F20F883E", "E10ADC3949BA59ABBE56E057F20F883E", "aaa" };
	
	public static List<User> generateData(UserPersistableService userService, List<Laboratory> laboratories){
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < NAMES.length; i++){
			User user = new User();
			user.setName(NAMES[i]);
			user.setLogin(LOGINS[i]);
			user.setAdmin(ADMIN[i]);
			user.setActive(true);
			user.setPasswordHash(HASHES[i]);
			user.setLaboratory(laboratories.get(i % laboratories.size()));
			userService.persist(user);
			list.add(user);
		}
		return list;
	}
}
