package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;
import net.cassiolandim.biomedcalib.service.UserSimplePersistableService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public class DataGenerator implements InitializingBean {
	
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	private UserSimplePersistableService userSimplePersistableService;
	
	public void setLaboratorySimplePersistableService(LaboratorySimplePersistableService laboratorySimplePersistableService) {
		this.laboratorySimplePersistableService = laboratorySimplePersistableService;
	}
	
	public void setUserSimplePersistableService(UserSimplePersistableService userSimplePersistableService) {
		this.userSimplePersistableService = userSimplePersistableService;
	}
	
	@Transactional
	public void afterPropertiesSet() throws Exception {
		List<Laboratory> laboratories = LaboratoryDataGenerator.generateData(laboratorySimplePersistableService);
		UserDataGenerator.generateData(userSimplePersistableService, laboratories);
	}
}
