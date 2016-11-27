import java.lang.reflect.Method;

import ontoplay.controllers.utils.OntologyUtils;
import controllers.configuration.utils.OntoplayOntologyUtils;
import ontoplay.models.ontologyReading.jena.JenaOwlReaderConfig;
import ontoplay.jobs.JenaOwlReaderConfiguration;
import ontoplay.jobs.OntologyGeneratorConfiguration;
import ontoplay.jobs.PropertyTypeConfiguration;
import play.*;
import play.mvc.Action;
import play.mvc.Http.Request;

public class Global extends GlobalSettings{
    @Override
    public void onStart(Application app)
    {
    	//new JenaOwlReaderConfiguration().initialize(TANHelper.file,new JenaOwlReaderConfig().useLocalMapping(TANHelper.iriString,TANHelper.fileName));
    	try {
    		OntoplayOntologyUtils.setOntologyCF();
			new PropertyTypeConfiguration().doJob();

			new JenaOwlReaderConfiguration().initialize(OntologyUtils.file,new JenaOwlReaderConfig().useLocalMapping(OntologyUtils.iriString,OntologyUtils.fileName));

			//Logger.info("\n\n\n REGISTERED \n\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Logger.info("Application has started");
    }

    @Override
    public Action onRequest(Request arg0, Method arg1) {
    	try {

    		new JenaOwlReaderConfiguration().initialize(OntologyUtils.file,new JenaOwlReaderConfig().useLocalMapping(OntologyUtils.iriString,OntologyUtils.fileName));
			new OntologyGeneratorConfiguration().doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return super.onRequest(arg0, arg1);
    }
}