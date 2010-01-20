package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.Date;

import net.cassiolandim.biomedcalib.Constants;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregateBinding;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasureDetailsEditPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureDetailsEditPage(final Long measuresAggregateId) {
		addHomeLink();
		
		final MeasuresAggregate measuresAggregate = measuresAggregatePersistableService.find(measuresAggregateId);
		
		MeasuresAggregateBinding binding = new MeasuresAggregateBinding();
		
		Form<MeasuresAggregate> form = new Form<MeasuresAggregate>("form");
		add(form);
		
		DateTextField creationDate = new DateTextField("creationDate", 
				new PropertyModel<Date>(measuresAggregate, binding.creationDate().getPath()), 
				Constants.DATE_PATTERN);
		creationDate.setRequired(true);
		form.add(creationDate);
		
		TextArea<String> observationTextArea = new TextArea<String>("observation", 
				new PropertyModel<String>(measuresAggregate, binding.observation().getPath()));
		form.add(observationTextArea);
		
		Label laboratoryName = new Label("laboratory.name", 
				new PropertyModel<String>(measuresAggregate, binding.laboratory().name().getPath()));
		form.add(laboratoryName);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				measuresAggregatePersistableService.save(measuresAggregate);
			}
		};
		form.add(save);
		
		add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
		
		add(new MeasuresPerLevelEditPanel("measures1", measuresAggregate.getMeasures1().getId()));
		add(new MeasuresPerLevelEditPanel("measures2", measuresAggregate.getMeasures2().getId()));
		add(new MeasuresPerLevelEditPanel("measures3", measuresAggregate.getMeasures3().getId()));
	}
}
