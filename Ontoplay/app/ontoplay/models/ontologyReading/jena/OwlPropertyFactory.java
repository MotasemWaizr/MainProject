package ontoplay.models.ontologyReading.jena;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntProperty;

import ontoplay.models.InvalidConfigurationException;
import ontoplay.models.ontologyModel.OntoProperty;

public class OwlPropertyFactory {
	private List<OwlPropertyFactory> factories = new ArrayList<OwlPropertyFactory>();
	public void registerPropertyFactory(
			OwlPropertyFactory datatypePropertyFactory) {
		factories.add(datatypePropertyFactory);		
	}
	
	public boolean canCreateProperty(OntProperty ontProperty){
		for(OwlPropertyFactory fact : factories) {
			if (fact.canCreateProperty(ontProperty)) {
				return true;
			}
		}
		return false;
	}

	public OntoProperty createProperty(OntProperty ontProperty){
		for(OwlPropertyFactory fact : factories){
			if(fact.canCreateProperty(ontProperty))
				return fact.createProperty(ontProperty);
		}
		return null;
	}
}
