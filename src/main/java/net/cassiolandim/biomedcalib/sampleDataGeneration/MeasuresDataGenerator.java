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
		entity.addMeasure(createMeasure(entity, "19/12/2009", 105L));
		entity.addMeasure(createMeasure(entity, "20/12/2009", 120L));
		entity.addMeasure(createMeasure(entity, "21/12/2009", 109L));
		entity.addMeasure(createMeasure(entity, "22/12/2009", 111L));
		entity.addMeasure(createMeasure(entity, "23/12/2009", 98L));
		entity.addMeasure(createMeasure(entity, "24/12/2009", 97L));
		entity.addMeasure(createMeasure(entity, "25/12/2009", 120L));
		entity.addMeasure(createMeasure(entity, "26/12/2009", 118L));
		entity.addMeasure(createMeasure(entity, "27/12/2009", 117L));
		entity.addMeasure(createMeasure(entity, "28/12/2009", 114L));
		entity.addMeasure(createMeasure(entity, "29/12/2009", 110L));
		entity.addMeasure(createMeasure(entity, "30/12/2009", 99L));
		entity.addMeasure(createMeasure(entity, "01/01/2010", 90L));
		
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
