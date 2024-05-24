/**
 * 
 */
package co.edu.usco.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.archivos.FilePortal;
import co.edu.usco.inscripciones.utilidad.FileUtil;

@RestController
public class ManualesController {

	@Autowired
	FileUtil fileUtil;

	@RequestMapping(value = "/manualesSakaiEstudiantes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FilePortal> manualesSakaiEstudiantes() {

		FilePortal filePortal = new FilePortal();
		filePortal.setRuta(fileUtil.paths[4] + "sakai-guias/estudiantes/");
		File file = new File(filePortal.getRuta());
		filePortal.setNombre("Raiz");

		List<FilePortal> listOfFiles = new ArrayList<FilePortal>();

		listOfFiles = FilePortal.loadFiles(file);

		return listOfFiles;

	}

	@RequestMapping(value = "/manualesSakaiDocentes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FilePortal> manualesSakaiDocentes() {

		FilePortal filePortal = new FilePortal();
		filePortal.setRuta(fileUtil.paths[4] + "sakai-guias/docentes/");
		File file = new File(filePortal.getRuta());
		filePortal.setNombre("Raiz");

		List<FilePortal> listOfFiles = new ArrayList<FilePortal>();

		listOfFiles = FilePortal.loadFiles(file);

		return listOfFiles;

	}

	@RequestMapping(value = "/videosSakaiDocentes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FilePortal> videosSakaiDocentes() {

		FilePortal filePortal = new FilePortal();
		filePortal.setRuta(fileUtil.paths[4] + "sakai-videos/docentes/");
		File file = new File(filePortal.getRuta());
		filePortal.setNombre("Raiz");

		List<FilePortal> listOfFiles = new ArrayList<FilePortal>();

		listOfFiles = FilePortal.loadFiles(file);

		return listOfFiles;

	}

	@RequestMapping(value = "/videosSakaiEstudiantes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FilePortal> videosSakaiEstudiantes() {

		FilePortal filePortal = new FilePortal();
		filePortal.setRuta(fileUtil.paths[4] + "sakai-videos/estudiantes/");
		File file = new File(filePortal.getRuta());
		filePortal.setNombre("Raiz");

		List<FilePortal> listOfFiles = new ArrayList<FilePortal>();

		listOfFiles = FilePortal.loadFiles(file);

		return listOfFiles;

	}

}
