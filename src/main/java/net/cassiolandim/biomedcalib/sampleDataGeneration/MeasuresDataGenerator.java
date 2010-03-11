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
		entity.setObservation("observação\nsegunda linha\nterceira linha");
		entity.setControlSerum(controlSerums.get(0));
		entity.addMeasure(createMeasure(entity, "01/12/2009", 4));
		entity.addMeasure(createMeasure(entity, "02/12/2009", 4.4));
		entity.addMeasure(createMeasure(entity, "03/12/2009", 4.3));
		entity.addMeasure(createMeasure(entity, "04/12/2009", 4.1));
		entity.addMeasure(createMeasure(entity, "05/12/2009", 4.5));
		entity.addMeasure(createMeasure(entity, "06/12/2009", 4.2));
		entity.addMeasure(createMeasure(entity, "07/12/2009", 4.1));
		entity.addMeasure(createMeasure(entity, "08/12/2009", 4.2));
		entity.addMeasure(createMeasure(entity, "09/12/2009", 4.6));
		entity.addMeasure(createMeasure(entity, "10/12/2009", 4.2));
		entity.addMeasure(createMeasure(entity, "11/12/2009", 4.3));
		entity.addMeasure(createMeasure(entity, "12/12/2009", 4.4));
		entity.addMeasure(createMeasure(entity, "13/12/2009", 4.3));
		entity.addMeasure(createMeasure(entity, "14/12/2009", 4));
		entity.addMeasure(createMeasure(entity, "15/12/2009", 4.3));
		entity.addMeasure(createMeasure(entity, "16/01/2010", 4.5));
		entity.addMeasure(createMeasure(entity, "17/01/2010", 4.6));
		entity.addMeasure(createMeasure(entity, "18/01/2010", 4.5));
		entity.addMeasure(createMeasure(entity, "19/01/2010", 4));
		entity.addMeasure(createMeasure(entity, "20/01/2010", 4.5));
		entity.addMeasure(createMeasure(entity, "21/01/2010", 4.3));
		entity.addMeasure(createMeasure(entity, "22/01/2010", 4.4));
		entity.addMeasure(createMeasure(entity, "23/01/2010", 4.4));
		entity.addMeasure(createMeasure(entity, "24/01/2010", 4.4));
		entity.addMeasure(createMeasure(entity, "25/01/2010", 4.3));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		entity = new MeasuresAggregate(laboratories.get(1));
		entity.setObservation("bla bla bla bla blalaaa blalaieiaee boijser \ns oeiurpoweiru wui skjcvn skr");
		entity.setControlSerum(controlSerums.get(1));
		entity.addMeasure(createMeasure(entity, "16/01/2010", 9.2));
		entity.addMeasure(createMeasure(entity, "17/01/2010", 10));
		entity.addMeasure(createMeasure(entity, "18/01/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "19/01/2010", 9.4));
		entity.addMeasure(createMeasure(entity, "20/01/2010", 10));
		entity.addMeasure(createMeasure(entity, "21/01/2010", 9.5));
		entity.addMeasure(createMeasure(entity, "22/01/2010", 9.3));
		entity.addMeasure(createMeasure(entity, "23/01/2010", 10));
		entity.addMeasure(createMeasure(entity, "24/02/2010", 9.9));
		entity.addMeasure(createMeasure(entity, "25/02/2010", 10));
		entity.addMeasure(createMeasure(entity, "26/02/2010", 10));
		entity.addMeasure(createMeasure(entity, "27/02/2010", 10));
		entity.addMeasure(createMeasure(entity, "28/02/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "29/02/2010", 10.2));
		entity.addMeasure(createMeasure(entity, "30/02/2010", 9.6));
		entity.addMeasure(createMeasure(entity, "01/03/2010", 10.2));
		entity.addMeasure(createMeasure(entity, "02/03/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "03/03/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "04/03/2010", 9.6));
		entity.addMeasure(createMeasure(entity, "05/03/2010", 9.8));
		entity.addMeasure(createMeasure(entity, "06/03/2010", 10.2));
		entity.addMeasure(createMeasure(entity, "07/03/2010", 10));
		entity.addMeasure(createMeasure(entity, "08/03/2010", 10.1));
		entity.addMeasure(createMeasure(entity, "09/03/2010", 10.1));
		entity.addMeasure(createMeasure(entity, "10/03/2010", 9.4));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		entity = new MeasuresAggregate(laboratories.get(1));
		entity.setObservation("aopisuf aosidf j,cnv xjncv iuwyoeir qiua sdfk\n oasupeoir qiopoasd xcvn,\n ofsiuperjkhsdf");
		entity.setControlSerum(controlSerums.get(1));
		entity.addMeasure(createMeasure(entity, "01/02/2010", 9.2));
		entity.addMeasure(createMeasure(entity, "02/02/2010", 10));
		entity.addMeasure(createMeasure(entity, "03/02/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "04/02/2010", 9.4));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		entity = new MeasuresAggregate(laboratories.get(1));
		entity.setObservation("oiup soidu eruiy xn nkdjkj \nos oueroi oiuhd");
		entity.setControlSerum(controlSerums.get(2));
		entity.addMeasure(createMeasure(entity, "02/03/2010", 9.2));
		entity.addMeasure(createMeasure(entity, "03/03/2010", 10));
		entity.addMeasure(createMeasure(entity, "04/03/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "05/03/2010", 9.4));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		entity = new MeasuresAggregate(laboratories.get(1));
		entity.setObservation("iuqiqoiwiuoe quuweo jkjdjfd jsjkdf");
		entity.setControlSerum(controlSerums.get(3));
		entity.addMeasure(createMeasure(entity, "11/02/2010", 9.2));
		entity.addMeasure(createMeasure(entity, "13/03/2010", 10));
		entity.addMeasure(createMeasure(entity, "14/03/2010", 9.7));
		entity.addMeasure(createMeasure(entity, "15/03/2010", 9.4));
		
		measuresAggregateService.persist(entity);
		list.add(entity);
		
		return list;
	}
	
	private static Measure createMeasure(MeasuresAggregate ma, String date, double value) throws ParseException{
		Measure measure = new Measure(ma);
		measure.setDate(dateFormat.parse(date));
		measure.setValue(value);
		return measure;
	}
}
