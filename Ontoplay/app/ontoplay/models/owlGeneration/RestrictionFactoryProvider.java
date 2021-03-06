package ontoplay.models.owlGeneration;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import ontoplay.models.ConfigurationException;
import ontoplay.models.PropertyValueCondition;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * Created by michal on 30.11.2016.
 */
public class RestrictionFactoryProvider {

    private Injector injector;

    @Inject
    public RestrictionFactoryProvider(Injector injector){
        this.injector = injector;
    }

    public <U extends PropertyValueCondition> RestrictionFactory<U> getRestrictionFactory(U condition) throws ConfigurationException {
        return injector.getInstance(Key.get(new TypeLiteral<RestrictionFactory<U>>(){}));
    }
}
