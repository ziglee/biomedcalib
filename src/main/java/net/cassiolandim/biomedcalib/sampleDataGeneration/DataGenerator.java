package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cassio Landim
 */
public class DataGenerator implements InitializingBean {
	
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	
	public void setLaboratorySimplePersistableService(LaboratorySimplePersistableService laboratorySimplePersistableService) {
		this.laboratorySimplePersistableService = laboratorySimplePersistableService;
	}
	
	@Transactional
	public void afterPropertiesSet() throws Exception {
//		List<Laboratory> laboratories = 
		LaboratoryDataGenerator.generateData(laboratorySimplePersistableService);
			
		List<Laboratory> laboratories = laboratorySimplePersistableService.findAll();
		for(Laboratory laboratory : laboratories){
			System.out.println("######### Laboratory: " + laboratory.getName());
		}
	}
}
