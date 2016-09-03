package models.owlGeneration.restrictionFactories;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import models.ConfigurationException;
import models.owlGeneration.RestrictionFactory;
import models.propertyConditions.DatatypePropertyCondition;

public class DatetimeRestrictionFactoryDecorator extends DatatypeRestrictionFactory{

	private final RestrictionFactory<DatatypePropertyCondition> restrictionFactory;

	public DatetimeRestrictionFactoryDecorator(RestrictionFactory<DatatypePropertyCondition> restrictionFactory) {
		this.restrictionFactory = restrictionFactory;		
	}

	@Override
	public OWLClassExpression createRestriction(DatatypePropertyCondition condition) throws ConfigurationException {
		DateTime datetime = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm").parseDateTime(condition.getDatatypeValue().trim());
		
		//10/26/2001 00:00 
		condition.setDatatypeValue(datetime.toString("s")); //toString(ISODateTimeFormat.dateHourMinuteSecond()));
		return restrictionFactory.createRestriction(condition);
	}
	
	@Override
	public List<OWLAxiom> createIndividualValue(DatatypePropertyCondition condition, OWLIndividual individual) throws ConfigurationException {
		DateTime datetime = DateTimeFormat.forPattern("yy-mm-dd").parseDateTime(condition.getDatatypeValue().trim());
		
		//10/26/2001 00:00 
		condition.setDatatypeValue(datetime.toString(ISODateTimeFormat.dateHourMinuteSecond()));
		return restrictionFactory.createIndividualValue(condition,individual);
	}

}