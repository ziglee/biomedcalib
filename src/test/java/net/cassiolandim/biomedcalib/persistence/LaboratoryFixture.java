package net.cassiolandim.biomedcalib.persistence;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.web.MockContext;

public class LaboratoryFixture {
	
	private static final String[] NAMES = { "CAPC" , "Atalaia", "Padrão", "INGOH", "Saluti", "Citocenter" };

	private final LaboratoryData laboratoryData = new LaboratoryData();

	public void addStubs(MockContext context) {
		context.putBean("laboratorySimplePersistableService", laboratoryData.getLaboratoryService());
		for (int i = 0; i < NAMES.length; i++) {
			Laboratory busLine = new Laboratory();
			busLine.setName(NAMES[i]);
			laboratoryData.newLaboratory(busLine);
		}
	}

	public LaboratoryData getLaboratoryData() {
		return laboratoryData;
	}
}