package net.cassiolandim.biomedcalib.component;

import java.util.Date;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * DateChooserPanel
 * <p/>
 * Created by: Andrew Lombardi
 * Copyright 2006 Mystic Coders, LLC
 */
public class DateChooserPanel extends Panel {

    public DateChooserPanel(String id, IModel<Date> model) {
        super(id, model);
        add(new DateChooser("date", model).setRenderBodyOnly(true));
    }
}
