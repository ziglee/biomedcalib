package net.cassiolandim.biomedcalib.web.page.laboratory;


import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LaboratoryListPage extends BasePage {

	@SpringBean(name = "laboratorySimplePersistableService")
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	
	public LaboratoryListPage() {
		add(new ListView<Laboratory>("laboratories", laboratorySimplePersistableService.findAll()){
			@Override
			protected void populateItem(ListItem<Laboratory> item){
				Laboratory laboratory = item.getModelObject();
				item.add(new Label("name", laboratory.getName()));
			}
		});
	}
}
