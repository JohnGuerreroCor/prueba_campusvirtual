package co.edu.usco.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.edu.usco.inscripciones.model.Certificado;
import co.edu.usco.inscripciones.model.MultiFileBucket;
import co.edu.usco.inscripciones.service.AspiranteService;
import co.edu.usco.inscripciones.utilidad.FileUtil;
import co.edu.usco.inscripciones.utilidad.GenerarHash;
import co.edu.usco.inscripciones.utilidad.MultiFileValidator;

@Controller
public class CertificadosController {

	public CertificadosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	AspiranteService aspiranteService;

	@Autowired
	Environment environment;

	// private static String
	// UPLOAD_LOCATION="C:/Users/ANDRES-GPIE/Desktop/wildfly-10.0.0.Final/standalone/deployments/certificados/";
	// private static String UPLOAD_LOCATION="/var/certificados/";

	@RequestMapping(value = "/certificados/{cantidad}/{codigo}", method = RequestMethod.GET)
	public String certificados(ModelMap model, @PathVariable int cantidad) {
		System.out.println("Entrooo");
		MultiFileBucket filesModel = new MultiFileBucket(cantidad);
		model.addAttribute("multiFileBucket", filesModel);
		return "certificados";
	}

	@Autowired
	MultiFileValidator multiFileValidator;

	@InitBinder("multiFileBucket")
	protected void initBinderMultiFileBucket(WebDataBinder binder) {
		binder.setValidator(multiFileValidator);
	}

	@RequestMapping(value = "/certificados/{cantidad}/{codigo}", method = RequestMethod.POST)
	public String multiFileUpload(@RequestParam("file") List<MultipartFile> files,
			@Valid MultiFileBucket multiFileBucket, BindingResult result, ModelMap model, @PathVariable long codigo,
			@PathVariable long cantidad) throws IOException {

		String location = environment.getProperty("certificadosPath") + "/" + codigo + "/";
		System.out.println("Location:" + location);
		System.out.println("Cantidad: " + cantidad);

		// esto para que genere la carpeta con el id de la inscripcion
		File folder = new File(location);
		if (!folder.exists()) {
			if (folder.mkdir()) {
				System.out.println("El directorio para la inscripcion " + codigo + "ha sido creado");
			} else {
				System.out.println("No fue posible crear el directorio para la inscripcion " + codigo);
			}
		}

		GenerarHash generateHash = new GenerarHash();

		if (result.hasErrors()) {
			System.out.println("Se detectaron errores en los archivos");
			return "certificados";
		} else {

			System.out.println("Procesando Archivos");
			List<String> fileNames = new ArrayList<String>();
			List<String> fileExtensions = new ArrayList<String>();
			List<String> fileLocation = new ArrayList<String>();

			byte[] salt = null;
			try {
				salt = generateHash.getSalt();
			} catch (Exception e) {
				e.toString();
			}

			// Now do something with file...
			// FileUtil fileUtil = new FileUtil(location);
			FileUtil fileUtil = new FileUtil();
			for (MultipartFile file : files) {
				System.out.println("Nombre archivo: " + file.getOriginalFilename());

				String tempFileNameCon = file.getOriginalFilename();
				// String tempFileNameSin = tempFileNameCon.substring(0,
				// tempFileNameCon.length()-4);

				System.out.println("salt: " + salt);
				String encName = generateHash.get_SHA_1_SecurePassword(tempFileNameCon, salt);

				System.out.println("encName " + encName);
				System.out.println("Name file" + file.getName());
				System.out.println("New Name:" + codigo + "/" + encName);
				String ruta = fileUtil.SaveFile(file, codigo + "/" + encName, 1);// file.getOriginalFilename()
				System.out.println("La ruta return es:" + ruta);
				fileNames.add(tempFileNameCon);
				// application/pdf
				fileExtensions.add(file.getContentType().split("/")[1]);
				fileLocation.add(environment.getProperty("certificadosPath"));

			}

			List<String> fileNamesEnc = new ArrayList<String>();

			for (String name : fileNames) {
				System.out.println("-name- " + name + " --- " + salt);
				fileNamesEnc.add(generateHash.get_SHA_1_SecurePassword(name, salt));
			}

			// imprimir en consolapara revisar
			for (int i = 0; i < fileNames.size(); i++) {
				System.out.println("registro 0 : Nombre Original : " + fileNames.get(i) + " Nombre Encriptado: "
						+ fileNamesEnc.get(i) + " Extension del archivo: " + fileExtensions.get(i)
						+ " Ubicacion del archivo : " + fileLocation.get(i));
			}

			// formamos la lista de certificados
			List<Certificado> certificados = new ArrayList<Certificado>();
			for (int i = 0; i < fileNames.size(); i++) {
				Certificado cert = new Certificado();
				cert.setName(fileNames.get(i));
				cert.setNameEnc(fileNamesEnc.get(i));
				cert.setFileExtension(fileExtensions.get(i));
				cert.setFileLocation(fileLocation.get(i));
				cert.setCodigoRegistro(codigo);
				certificados.add(cert);
				System.out.println("Ruta:" + fileLocation.get(i));
			}

			// se envia a guardar en la base de datos
			System.out.println("Cantidad de archivos:" + certificados.size());
			if (certificados.size() > 0) {
				aspiranteService.guardarCertificados(certificados);
			}

			return "CargaCorrecta";
		}

	}

	@RequestMapping(value = "/CargaIncorrecta", method = RequestMethod.GET)
	public String cargaIncorrecta(ModelMap model) {
		String mensaje = "Ocurrio un Error";
		model.addAttribute("mensaje", mensaje);
		return "CargaIncorrecta";
	}

}
