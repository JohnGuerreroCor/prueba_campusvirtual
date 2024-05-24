/**
 * Clase AreaRestController para consumir el servicio web de listar las areas
 */
package co.edu.usco.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usco.inscripciones.dao.ArchivosAdjuntosDao;
import co.edu.usco.inscripciones.model.ArchivosAdjuntos;
import co.edu.usco.inscripciones.utilidad.FileUtil;

/**
 * 
 * @author Jankarlos Diaz Vieda
 * @version 1.0
 */

@RestController
public class ArchivosRestController {
	@Autowired
	DataSource dataSourceAcademiaInvitado;

	@Autowired
	FileUtil fileUtil;
	
	@Autowired
	Environment environment;
	
	@Autowired
	ArchivosAdjuntosDao archivosAdjuntosDao;
	
	@RequestMapping(value = "api/imagen/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getProductoImagen(@PathVariable("id") String nombreEncriptado, HttpServletResponse reponse)
			throws IOException {
		
		ArchivosAdjuntos archivosAdjuntos = archivosAdjuntosDao.consultarRegistro(nombreEncriptado);
		if(archivosAdjuntos!=null){
			
			Path imagePath = Paths.get(fileUtil.paths[1] + archivosAdjuntos.getRegistro() + "/" + nombreEncriptado);// + ".jpg"
			final HttpHeaders headers = new HttpHeaders();
			//headers.setContentType(MediaType.IMAGE_JPEG);
			headers.add("Content-Disposition", "attachment; filename=" + archivosAdjuntos.getNombreCompleto());
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			byte[] img;
			try{
				img = Files.readAllBytes(imagePath);
			} catch(Exception e) {
				e.printStackTrace();
				img = Files.readAllBytes(Paths.get(fileUtil.paths[1] + "default.pdf"));
			}
			return new ResponseEntity<byte[]>(img, headers, HttpStatus.OK);			
		}
		return null;
	}
	
	@RequestMapping(value = "guias/sakai/{tipo}/{nombreCarpeta}/{nombreSubCarpeta}/{nombreEncriptado}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getGuiasSakaiSub(@PathVariable("tipo") String tipo, @PathVariable("nombreCarpeta") String nombreCarpeta,@PathVariable("nombreSubCarpeta") String nombreSubCarpeta, @PathVariable("nombreEncriptado") String nombreEncriptado, HttpServletResponse reponse)
		throws IOException {
			Path imagePath = Paths.get(fileUtil.paths[4] + "sakai-guias/" + tipo + "/" + nombreCarpeta + "/" + nombreSubCarpeta + "/" + nombreEncriptado + ".pdf");
			final HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "filename=" + nombreEncriptado + ".pdf");
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			byte[] img;
		try{
			img = Files.readAllBytes(imagePath);
		} catch(Exception e) {
			e.printStackTrace();
			img = Files.readAllBytes(Paths.get(fileUtil.paths[2] + "default.pdf"));
		}
		return new ResponseEntity<byte[]>( img, headers, HttpStatus.OK);			
	}
	
	@RequestMapping(value = "videos/sakai/{tipo}/{nombreCarpeta}/{nombreEncriptado}", method = RequestMethod.GET)
	public void getVideosSakai(@PathVariable("tipo") String tipo, @PathVariable("nombreCarpeta") String nombreCarpeta, @PathVariable("nombreEncriptado") String nombreEncriptado, HttpServletResponse response)

			throws IOException {
				Path videoPath = Paths.get(fileUtil.paths[4] + "sakai-videos/" + tipo + "/" + nombreCarpeta + "/" + nombreEncriptado + ".mp4");
				/*final HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "filename=" + nombreEncriptado + ".mp4");
				headers.setContentType(MediaType.parseMediaType("video/mp4"));
				byte[] video;*/
			try{
				//img = Files.readAllBytes(imagePath);		
				response.setContentType("application/octet-stream"); 
				response.setContentLength((int) Files.size(videoPath));
				Files.copy(videoPath, response.getOutputStream());
				
			} catch(Exception e) {
				e.printStackTrace();
				//video = Files.readAllBytes(Paths.get(fileUtil.paths[2] + "default.pdf"));
			}
			//return new ResponseEntity<byte[]>( video, headers, HttpStatus.OK);			
	}
	
	@RequestMapping(value = "guias/sakai/{tipo}/{nombreCarpeta}/{nombreEncriptado}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getGuiasSakai(@PathVariable("tipo") String tipo, @PathVariable("nombreCarpeta") String nombreCarpeta, @PathVariable("nombreEncriptado") String nombreEncriptado, HttpServletResponse reponse)
		throws IOException {
			Path imagePath = Paths.get(fileUtil.paths[4] + "sakai-guias/" + tipo + "/" + nombreCarpeta + "/" + nombreEncriptado + ".pdf");
			final HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "filename=" + nombreEncriptado + ".pdf");
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			byte[] img;
		try{
			img = Files.readAllBytes(imagePath);
		} catch(Exception e) {
			e.printStackTrace();
			img = Files.readAllBytes(Paths.get(fileUtil.paths[2] + "default.pdf"));
		}
		return new ResponseEntity<byte[]>( img, headers, HttpStatus.OK);
	}
}