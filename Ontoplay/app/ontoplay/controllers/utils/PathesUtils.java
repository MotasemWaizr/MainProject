package ontoplay.controllers.utils;

import play.Play;

public class PathesUtils {
	//TODO: Move these into configuration file.
	public static final String CONFIGURATION_PATH=Play.application().path().getAbsolutePath()+"/OntoPlay/configuration/";
	public static final String UPLOADS_PATH=CONFIGURATION_PATH+"uploads/";
	public static final String ONTOLOGY_XML_FILE_PATH=CONFIGURATION_PATH+"OntologyCF.xml";
	public static final String Annotation_XML_FILE_PATH=CONFIGURATION_PATH+"AnnotationCF.xml";
	public static final String Annotation_xml_original=CONFIGURATION_PATH+"AnnotationCFOriginal.xml";
}
