package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;

/**
 * @author Cassio Landim
 */
public class LaboratoryDataGenerator {

	public static final String[] NAMES = { "CAPC" , "Atalaia", "Padrão", "INGOH", "Saluti", "Citocenter" };
	
	public static List<Laboratory> generateData(LaboratoryPersistableService laboratoryService){
		List<Laboratory> list = new ArrayList<Laboratory>();
		for (int i = 0; i < NAMES.length; i++){
			Laboratory laboratory = new Laboratory();
			laboratory.setName(NAMES[i]);
			laboratoryService.persist(laboratory);
			list.add(laboratory);
		}
		return list;
	}
}
