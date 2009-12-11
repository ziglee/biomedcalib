package net.cassiolandim.biomedcalib.web.page.laboratory;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class LaboratoryEditPage extends AdminBasePage {

	@SpringBean(name = "laboratorySimplePersistableService")
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	
	public LaboratoryEditPage(final Laboratory laboratory) {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		EntityLoadableDetachableModel<Laboratory> model = new EntityLoadableDetachableModel<Laboratory>(laboratory, laboratorySimplePersistableService);
		
		Form<Laboratory> form = new Form<Laboratory>("form", model);
		add(form);
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(laboratory, "name"));
		name.setLabel(new ResourceModel("name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(50));
		form.add(name);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				laboratorySimplePersistableService.save(laboratory);
				info(getString("laboratory.save.success"));
			}
			
		};
		form.add(save);
		
		Link list = new Link("listLink") {
			@Override
			public void onClick() {
				setResponsePage(LaboratoryListPage.class);
			}
		};
		add(list);
		
		addAdminHomeLink();
	}
}
