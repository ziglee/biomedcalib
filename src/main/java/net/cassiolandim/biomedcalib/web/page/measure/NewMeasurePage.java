package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class NewMeasurePage extends WebPage {

	MeasuresAggregate measuresAggregate;
	
	public NewMeasurePage() {
		Laboratory laboratory = new Laboratory();
		laboratory.name = "CAPC";
		measuresAggregate = new MeasuresAggregate(laboratory);

		Label labelLaboratory = new Label("laboratory"){
			
		};
	}
	
}
