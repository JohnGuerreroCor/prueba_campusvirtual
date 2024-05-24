package co.edu.usco.inscripciones.model;

import java.util.ArrayList;
import java.util.List;

public class MultiFileBucket {
	
	public MultiFileBucket() {
		super();
		// TODO Auto-generated constructor stub
	}

	List<FileBucket> files = new ArrayList<FileBucket>();
	
	public MultiFileBucket(int cantidad){
		for(int i = 1; i <= cantidad; i++){
			files.add(new FileBucket());
		}
	}
	
	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}
}

