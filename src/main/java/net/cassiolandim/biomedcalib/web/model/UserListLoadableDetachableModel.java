package net.cassiolandim.biomedcalib.web.model;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserSimplePersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class UserListLoadableDetachableModel extends LoadableDetachableModel<List<User>> {
	
	private final UserSimplePersistableService service;

	public UserListLoadableDetachableModel(UserSimplePersistableService service) {
		this.service = service;
	}

	@Override
	protected List<User> load() {
		return service.findAll();
	}
}
