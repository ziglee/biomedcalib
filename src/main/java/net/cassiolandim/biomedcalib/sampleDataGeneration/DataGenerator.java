package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.service.UserPersistableService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public class DataGenerator implements InitializingBean {
	
	private LaboratoryPersistableService laboratorySimplePersistableService;
	private UserPersistableService userSimplePersistableService;
	
	public void setLaboratorySimplePersistableService(LaboratoryPersistableService laboratorySimplePersistableService) {
		this.laboratorySimplePersistableService = laboratorySimplePersistableService;
	}
	
	public void setUserSimplePersistableService(UserPersistableService userSimplePersistableService) {
		this.userSimplePersistableService = userSimplePersistableService;
	}
	
	@Transactional
	public void afterPropertiesSet() throws Exception {
		List<Laboratory> laboratories = LaboratoryDataGenerator.generateData(laboratorySimplePersistableService);
		UserDataGenerator.generateData(userSimplePersistableService, laboratories);
	}
}
