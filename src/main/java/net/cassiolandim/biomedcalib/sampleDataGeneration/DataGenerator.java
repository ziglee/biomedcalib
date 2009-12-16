package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.service.UserPersistableService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public class DataGenerator implements InitializingBean {
	
	private LaboratoryPersistableService laboratoryPersistableService;
	private UserPersistableService userPersistableService;
	private ControlSerumPersistableService controlSerumPersistableService;
	
	public void setLaboratoryPersistableService(LaboratoryPersistableService laboratoryPersistableService) {
		this.laboratoryPersistableService = laboratoryPersistableService;
	}
	
	public void setUserPersistableService(UserPersistableService userPersistableService) {
		this.userPersistableService = userPersistableService;
	}
	
	public void setControlSerumPersistableService(ControlSerumPersistableService controlSerumPersistableService) {
		this.controlSerumPersistableService = controlSerumPersistableService;
	}
	
	@Transactional
	public void afterPropertiesSet() throws Exception {
		List<Laboratory> laboratories = LaboratoryDataGenerator.generateData(laboratoryPersistableService);
		UserDataGenerator.generateData(userPersistableService, laboratories);
		ControlSerumDataGenerator.generateData(controlSerumPersistableService);
	}
}
