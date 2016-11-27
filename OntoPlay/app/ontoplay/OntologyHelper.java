package ontoplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import jadeOWL.base.OntologyManager;
import ontoplay.jobs.JenaOwlReaderConfiguration;
import ontoplay.models.ontologyReading.OntologyReader;
import ontoplay.models.ontologyReading.jena.JenaOwlReaderConfig;

public class OntologyHelper {

	public static String ontologyName = "";

	public static String file = "file:" + Pathes.UPLOADS_PATH + ontologyName;

	public static String fileName = Pathes.UPLOADS_PATH + ontologyName;

	public static String checkFile = "file:" + Pathes.UPLOADS_PATH + "Check" + ontologyName;

	public static String checkFileName = Pathes.UPLOADS_PATH + "Check" + ontologyName;

	public static String nameSpace = "";

	public static String iriString = "";

	public static boolean saveOntology(OWLOntology generatedOntology) {
		OWLOntologyManager OWLmanager = OWLManager.createOWLOntologyManager();
		OWLOntology originalOntology = null;

		try {
			originalOntology = OWLmanager.loadOntologyFromOntologyDocument(new File(fileName));
		} catch (OWLOntologyCreationException e1) {
			System.out.print("Error at OntologyHelper.saveOntology 47" + e1.getMessage());
		}

		OntologyManager manager = new OntologyManager();
		IRI test = IRI.create(iriString);
		OWLOntology newOntology = null;

		try {
			newOntology = manager.mergeOntologies(test, originalOntology, generatedOntology);
		} catch (org.semanticweb.owlapi.model.OWLOntologyCreationException | OWLOntologyStorageException
				| IOException e) {
			System.out.print("Error at OntologyHelper.saveOntology 58 " + e.getMessage());
		}

		OutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			OWLmanager.saveOntology(newOntology, out);
		} catch (Exception e) {
			System.out.print("Error at OntologyHelper.saveOntology 66" + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					System.out.print("Error at OntologyHelper.saveOntology 73" + e.getMessage());
				}
			}
		}
		return true;
	}

	public static boolean checkOntology(OWLOntology generatedOntology) {
		try {
			OWLOntologyManager OWLmanager = OWLManager.createOWLOntologyManager();
			OWLOntology originalOntology = null;
			try {
				originalOntology = OWLmanager.loadOntologyFromOntologyDocument(new File(fileName));
			} catch (OWLOntologyCreationException e1) {
				System.out.print("Error at OntologyHelper.checkOntology 83" + e1.getMessage());
				return false;
			}

			OntologyManager manager = new OntologyManager();
			IRI test = IRI.create(iriString);
			OWLOntology newOntology = null;
			try {
				newOntology = manager.mergeOntologies(test, originalOntology, generatedOntology);
			} catch (org.semanticweb.owlapi.model.OWLOntologyCreationException | OWLOntologyStorageException
					| IOException e) {
				System.out.print("Error at OntologyHelper.checkOntology 94" + e.getMessage());
				return false;
			}
			checkFileName = Pathes.UPLOADS_PATH + "Check" + ontologyName;
			OutputStream out = null;
			try {
				out = new FileOutputStream(checkFileName);
				OWLmanager.saveOntology(newOntology, out);

			} catch (FileNotFoundException e) {
				System.out.print("Error at OntologyHelper.checkOntology 104" + e.getMessage());
				return false;
			} catch (OWLOntologyStorageException e) {
				System.out.print("Error at OntologyHelper.checkOntology 107" + e.getMessage());
				return false;
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.out.print("Error at OntologyHelper.checkOntology 120" + e.getMessage());
			return false;
		}
	}

	public static OntologyReader checkOwlReader() {
		checkFile = "file:" + Pathes.UPLOADS_PATH + "Check" + ontologyName;
		new JenaOwlReaderConfiguration().initialize(checkFile,
				new JenaOwlReaderConfig().useLocalMapping(iriString, checkFileName));
		return OntologyReader.getGlobalInstance();
	}

	public static String getComponentIriByName(String name) {
		return nameSpace + name;
	}

}
