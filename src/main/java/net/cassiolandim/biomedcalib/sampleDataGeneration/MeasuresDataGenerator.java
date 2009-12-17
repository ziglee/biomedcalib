package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.entity.MeasuresPerLevel;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;

/**
 * @author Cassio Landim
 */
public class MeasuresDataGenerator {

	public static List<MeasuresAggregate> generateData(MeasuresAggregatePersistableService measuresAggregateService, List<Laboratory> laboratories, List<ControlSerum> controlSerums) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		List<MeasuresAggregate> list = new ArrayList<MeasuresAggregate>();
		
		MeasuresAggregate entity = new MeasuresAggregate(laboratories.get(0));
		entity.setObservation("observação");
		
		MeasuresPerLevel mpl1 = entity.getMeasures1();
		MeasuresPerLevel mpl2 = entity.getMeasures2();
		MeasuresPerLevel mpl3 = entity.getMeasures3();
		
		mpl1.setControlSerum(controlSerums.get(0));
		mpl2.setControlSerum(controlSerums.get(0));
		mpl3.setControlSerum(controlSerums.get(0));
		
		Measure measure = new Measure(mpl1);
		measure.setDate(dateFormat.parse("16/12/2009"));
		measure.setValue(110L);
		
		mpl1.addMeasure(measure);
		
		measuresAggregateService.save(entity);
		list.add(entity);
		
		return list;
	}
}
