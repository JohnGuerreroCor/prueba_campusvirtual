package co.edu.usco.inscripciones.utilidad;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import co.edu.usco.inscripciones.model.FileBucket;
import co.edu.usco.inscripciones.model.MultiFileBucket;

@Component
public class MultiFileValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return MultiFileBucket.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MultiFileBucket multiBucket = (MultiFileBucket) obj;
		
		int index=0;
		
		for(FileBucket file : multiBucket.getFiles()){
			if(file.getFile()!=null){
				if (file.getFile().getSize() == 0) {
					//errors.rejectValue("files["+index+"].file", "missing.file");
					errors.rejectValue("files["+index+"].file", "missing.file");
					System.out.println("No se enviaron archivos");				
				}
			}
			
			if (!file.getFile().getContentType().equalsIgnoreCase("application/pdf")) {
				errors.rejectValue("files["+index+"].file", "error.file");
				System.out.println("No se enviaron archivos con extension pdf");	
			}
			
			if (file.getFile().getSize() > 4194304) {
				errors.rejectValue("files["+index+"].file", "error.file");
				System.out.println("Archivo mas grande de lo esperado");
			}
				
			index++;
		}
		
	}
}