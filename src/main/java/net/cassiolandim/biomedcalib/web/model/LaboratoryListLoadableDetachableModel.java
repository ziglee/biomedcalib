package net.cassiolandim.biomedcalib.web.model;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

public class LaboratoryListLoadableDetachableModel extends LoadableDetachableModel<List<Laboratory>> {
	
	private final LaboratorySimplePersistableService service;

	public LaboratoryListLoadableDetachableModel(LaboratorySimplePersistableService service) {
		this.service = service;
	}

	@Override
	protected List<Laboratory> load() {
		return service.findAll();
	}
}
