package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

/**
 * @author Cassio Landim
 */
public class NewMeasurePage extends BasePage {

	MeasuresAggregate measuresAggregate;
	
	public NewMeasurePage() {
		Laboratory laboratory = new Laboratory();
		laboratory.setName("CAPC");
		measuresAggregate = new MeasuresAggregate(laboratory);

		Form form = new Form("form");
        add(form);
        
        form.add(new Label("laboratory.name", new PropertyModel(measuresAggregate, "laboratory.name")));
	}
	
}
