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

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	public static List<MeasuresAggregate> generateData(MeasuresAggregatePersistableService measuresAggregateService, List<Laboratory> laboratories, List<ControlSerum> controlSerums) throws ParseException{
		List<MeasuresAggregate> list = new ArrayList<MeasuresAggregate>();
		
		MeasuresAggregate entity = new MeasuresAggregate(laboratories.get(0));
		entity.setObservation("observação");
		
		MeasuresPerLevel mpl1 = entity.getMeasures1();
		MeasuresPerLevel mpl2 = entity.getMeasures2();
		MeasuresPerLevel mpl3 = entity.getMeasures3();
		
		mpl1.setControlSerum(controlSerums.get(0));
		mpl2.setControlSerum(controlSerums.get(1));
		mpl3.setControlSerum(controlSerums.get(2));
		
		mpl1.addMeasure(createMeasure(mpl1, "16/12/2009", 110L));
		mpl1.addMeasure(createMeasure(mpl1, "17/12/2009", 101L));
		mpl1.addMeasure(createMeasure(mpl1, "18/12/2009", 99L));
		mpl2.addMeasure(createMeasure(mpl1, "15/12/2009", 102L));
		mpl2.addMeasure(createMeasure(mpl1, "16/12/2009", 103L));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		return list;
	}
	
	private static Measure createMeasure(MeasuresPerLevel mpl, String date, long value) throws ParseException{
		Measure measure = new Measure(mpl);
		measure.setDate(dateFormat.parse(date));
		measure.setValue(value);
		return measure;
	}
}
