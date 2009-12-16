package net.cassiolandim.biomedcalib.web.page.controlSerum;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class ControlSerumEditPage extends AdminBasePage {

	@SpringBean(name = "controlSerumPersistableService")
	private ControlSerumPersistableService controlSerumPersistableService;
	
	public ControlSerumEditPage() {
		this(new ControlSerum());
	}
	
	public ControlSerumEditPage(final ControlSerum controlSerum) {
		RangeValidator<Double> doubleRangeValidator = new RangeValidator<Double>(ControlSerum.RANGE_MINIMUM, ControlSerum.RANGE_MAXIMUM);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		final EntityLoadableDetachableModel<ControlSerum> model = new EntityLoadableDetachableModel<ControlSerum>(controlSerum, controlSerumPersistableService);
		
		Form<ControlSerum> form = new Form<ControlSerum>("form", model);
		add(form);
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(controlSerum, "name"));
		name.setLabel(new ResourceModel("name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(50));
		form.add(name);
		
		TextField<Double> minimum = new TextField<Double>("minimum", new PropertyModel<Double>(controlSerum, "minimum"));
		minimum.setLabel(new ResourceModel("minimum"));
		minimum.setRequired(true);
		minimum.add(doubleRangeValidator);
		form.add(minimum);
		
		TextField<Double> maximum = new TextField<Double>("maximum", new PropertyModel<Double>(controlSerum, "maximum"));
		maximum.setLabel(new ResourceModel("maximum"));
		maximum.setRequired(true);
		maximum.add(doubleRangeValidator);
		form.add(maximum);
		
		Label mean = new Label("mean", new PropertyModel<Double>(controlSerum, "mean"));
		form.add(mean);
		
		TextField<Double> standardDeviation = new TextField<Double>("standardDeviation", new PropertyModel<Double>(controlSerum, "standardDeviation"));
		standardDeviation.setLabel(new ResourceModel("standardDeviation"));
		standardDeviation.setRequired(true);
		standardDeviation.add(doubleRangeValidator);
		form.add(standardDeviation);
		
		TextField<Double> coefficientOfVariation = new TextField<Double>("coefficientOfVariation", new PropertyModel<Double>(controlSerum, "coefficientOfVariation"));
		coefficientOfVariation.setLabel(new ResourceModel("coefficientOfVariation"));
		coefficientOfVariation.setRequired(true);
		coefficientOfVariation.add(doubleRangeValidator);
		form.add(coefficientOfVariation);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				controlSerumPersistableService.save(controlSerum);
				model.setId(controlSerum.getId());
				info(getString("controlSerum.save.success"));
			}
		};
		form.add(save);
		
		add(new ControlSerumListLink("listLink"));
		addAdminHomeLink();
	}
}
