package net.cassiolandim.biomedcalib.web.page.laboratory;


import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;
import net.cassiolandim.biomedcalib.web.model.LaboratoryListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class LaboratoryListPage extends AdminBasePage {

	@SpringBean(name = "laboratorySimplePersistableService")
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	
	public LaboratoryListPage() {
		add(new ListView<Laboratory>("laboratories", new LaboratoryListLoadableDetachableModel(laboratorySimplePersistableService)){
			@Override
			protected void populateItem(ListItem<Laboratory> item){
				final Laboratory laboratory = item.getModelObject();
				
				item.add(new Label("name", laboratory.getName()));
				
				item.add(new Link("editLink"){
					@Override
					public void onClick() {
						setResponsePage(new LaboratoryEditPage(laboratory));
					}
				});
				
				item.add(new Link("deleteLink"){
					@Override
					public void onClick() {
						laboratorySimplePersistableService.remove(laboratory);
						info("Laborat�rio exclu�do com sucesso!");
					}
				});
			}
		});
		
		add(new Link("newLink"){
			@Override
			public void onClick() {
				setResponsePage(new LaboratoryNewPage());
			}
		});
		
		addAdminHomeLink();
	}
}