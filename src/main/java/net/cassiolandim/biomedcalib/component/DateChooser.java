package net.cassiolandim.biomedcalib.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * DateChooser
 * <p/>
 * Created by: Andrew Lombardi
 * Copyright 2006 Mystic Coders, LLC
 *
 * With helpful contributions from:
 *  <a href="http://www.systemmobile.com">mr_smith</a>
 *  <a href="http://www.almaw.com">AlMaw</a>
 *  and ivaynberg
 */
public class DateChooser extends FormComponent<Date> {

	private final DropDownChoice<Integer> day;
	private final DropDownChoice<Integer> month;
	private final DropDownChoice<Integer> year;
	
    public DateChooser(final String id, IModel<Date> model) {
        super(id);
        setType(Date.class);
        
        if (model == null) {
            model = new Model<Date>(new Date());
        } else if (model.getObject() == null) {
            model.setObject(new Date());
        } else if (!(model.getObject() instanceof Date)) {
            throw new WicketRuntimeException(
                    "DateChooser ["
                            + getPath()
                            + "] contains an invalid model object, must be an object of type java.util.Date");
        }

        setModel(model);

        month = new DropDownChoice<Integer>("month", new DateModel(model, Calendar.MONTH), getMonths(), new IChoiceRenderer<Integer>() {

            public Object getDisplayValue(Integer object) {
                SimpleDateFormat format = new SimpleDateFormat("MMM");

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.MONTH, ((Integer) object).intValue());

                return format.format(cal.getTime());
            }

            public String getIdValue(Integer integer, int index) {
                return String.valueOf(index);
            }
        });
        add(month);

        day = new DropDownChoice<Integer>("day", new DateModel(model, Calendar.DAY_OF_MONTH), getDays());
        add(day);
        
        year = new DropDownChoice<Integer>("year", new DateModel(model, Calendar.YEAR), getYears());
        add(year);
    }

    @Override
    protected void convertInput() {
    	Integer day = this.day.getConvertedInput();
		Integer month = this.month.getConvertedInput();
		Integer year = this.year.getConvertedInput();
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		setConvertedInput(calendar.getTime());
    }
    
	private class DateModel extends Model<Integer> {
        private final IModel<Date> dateModel;
        private final int calendarField;

        public DateModel(IModel<Date> dateModel, int calendarField) {
            this.dateModel = dateModel;
            this.calendarField = calendarField;
        }

        public void detach() {
            dateModel.detach();
        }

        public Integer getObject() {
            if (dateModel.getObject() == null) return null;

            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) dateModel.getObject());

            return cal.get(calendarField);
        }

        public void setObject(Integer object) {
            Date date = (Date)dateModel.getObject();
            if(date==null) {
                date = new Date();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(calendarField, object.intValue());

            dateModel.setObject(cal.getTime());
        }
    }

    private List<Integer> getMonths() {
        List<Integer> months = new ArrayList<Integer>(12);

        for (int i = 0; i < 12; i++) {
            months.add(new Integer(i));
        }

        return months;
    }

    protected List<Integer> getDays() {
        List<Integer> days = new ArrayList<Integer>(31);

        for (int i = 1; i <= 31; i++) {
            days.add(new Integer(i));
        }

        return days;
    }

    protected List<Integer> getYears() {
        List<Integer> years = new ArrayList<Integer>(10);

        Calendar cal = Calendar.getInstance();

        for (int i = cal.get(Calendar.YEAR) - 2; i < cal.get(Calendar.YEAR) + 8; i++) {
            years.add(new Integer(i));
        }

        return years;
    }
}
