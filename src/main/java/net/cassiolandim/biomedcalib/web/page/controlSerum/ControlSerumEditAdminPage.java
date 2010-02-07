package net.cassiolandim.biomedcalib.web.page.controlSerum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class ControlSerumEditAdminPage extends AdminBasePage {

	@SpringBean(name = "laboratoryPersistableService")
	private LaboratoryPersistableService laboratoryPersistableService;

	@SpringBean(name = "controlSerumPersistableService")
	private ControlSerumPersistableService controlSerumPersistableService;
	
	public ControlSerumEditAdminPage() {
		this(new ControlSerum());
	}
	
	public ControlSerumEditAdminPage(final ControlSerum controlSerum) {
		RangeValidator<Double> doubleRangeValidator = new RangeValidator<Double>(ControlSerum.RANGE_MINIMUM, ControlSerum.RANGE_MAXIMUM);
		
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		final EntityLoadableDetachableModel<ControlSerum> model = new EntityLoadableDetachableModel<ControlSerum>(controlSerum, controlSerumPersistableService);
		
		Form<ControlSerum> form = new Form<ControlSerum>("form", model);
		add(form);
		
		EntityListLoadableDetachableModel<Laboratory, List<Laboratory>> labListModel = new EntityListLoadableDetachableModel<Laboratory, List<Laboratory>>(laboratoryPersistableService);
		
		ChoiceRenderer<Laboratory> labChoiceRenderer = new ChoiceRenderer<Laboratory>("name","id");
		DropDownChoice<Laboratory> labs = new DropDownChoice<Laboratory>("laboratory", new PropertyModel<Laboratory>(controlSerum, "laboratory"), labListModel);
		labs.setLabel(new ResourceModel("laboratory"));
		labs.setRequired(true);
		labs.setChoiceRenderer(labChoiceRenderer);
		form.add(labs);
		
		IChoiceRenderer<Integer> statusChoiceRenderer = new IChoiceRenderer<Integer>() {
			@Override
			public String getDisplayValue(Integer object) {
				return ControlSerum.getStatusMap().get(object);
			}
			@Override
			public String getIdValue(Integer object, int index) {
				return object.toString();
			}
		};
		
		IModel dropDownModel = new Model(){
			@Override
			public Serializable getObject() {
				return new ArrayList(ControlSerum.getStatusMap().keySet());
			}
		};
		
		DropDownChoice<Integer> status = new DropDownChoice<Integer>("status", new PropertyModel<Integer>(controlSerum, "status"), dropDownModel, statusChoiceRenderer);
		status.setLabel(new ResourceModel("status"));
		status.setRequired(true);
		form.add(status);
		
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
		
		add(new ControlSerumListAdminLink("listLink"));
		addAdminHomeLink();
	}
}
