package net.cassiolandim.biomedcalib.sampleDataGeneration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;

/**
 * @author Cassio Landim
 */
public class MeasuresDataGenerator {

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	public static List<MeasuresAggregate> generateData(MeasuresAggregatePersistableService measuresAggregateService, List<Laboratory> laboratories, List<ControlSerum> controlSerums) throws ParseException{
		List<MeasuresAggregate> list = new ArrayList<MeasuresAggregate>();
		
		MeasuresAggregate entity = new MeasuresAggregate(laboratories.get(0));
		entity.setObservation("observação");
		entity.setControlSerum(controlSerums.get(0));
		entity.addMeasure(createMeasure(entity, "16/12/2009", 110L));
		entity.addMeasure(createMeasure(entity, "17/12/2009", 101L));
		entity.addMeasure(createMeasure(entity, "18/12/2009", 99L));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		return list;
	}
	
	private static Measure createMeasure(MeasuresAggregate ma, String date, long value) throws ParseException{
		Measure measure = new Measure(ma);
		measure.setDate(dateFormat.parse(date));
		measure.setValue(value);
		return measure;
	}
}
