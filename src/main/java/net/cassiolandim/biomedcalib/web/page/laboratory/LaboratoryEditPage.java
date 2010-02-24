package net.cassiolandim.biomedcalib.web.page.laboratory;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.upload.FileItemFactory;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class LaboratoryEditPage extends AdminBasePage {

	@SpringBean(name = "laboratoryPersistableService")
	private LaboratoryPersistableService laboratoryPersistableService;
	
	public LaboratoryEditPage() {
		this(new Laboratory());
	}
	
	public LaboratoryEditPage(final Laboratory laboratory) {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		final EntityLoadableDetachableModel<Laboratory> model = new EntityLoadableDetachableModel<Laboratory>(laboratory, laboratoryPersistableService);
		
		Form<Laboratory> form = new Form<Laboratory>("form", model);
		add(form);
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(laboratory, "name"));
		name.setLabel(new ResourceModel("name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(50));
		form.add(name);

		final FileUploadField logomark = new FileUploadField("logomark");
		form.add(logomark);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				if(logomark.getFileUpload()!=null){
					laboratory.setLogomark(logomark.getFileUpload().getBytes());
				}
				laboratoryPersistableService.save(laboratory);
				model.setId(laboratory.getId());
				info(getString("laboratory.save.success"));
			}
		};
		form.add(save);
		
		add(new LaboratoryListLink("listLink"));
		addAdminHomeLink();
	}
}
