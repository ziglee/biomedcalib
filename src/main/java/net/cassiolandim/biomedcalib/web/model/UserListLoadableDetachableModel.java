package net.cassiolandim.biomedcalib.web.model;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserPersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class UserListLoadableDetachableModel extends LoadableDetachableModel<List<User>> {
	
	private final UserPersistableService service;

	public UserListLoadableDetachableModel(UserPersistableService service) {
		this.service = service;
	}

	@Override
	protected List<User> load() {
		return service.findAll();
	}
}
