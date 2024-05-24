package co.edu.usco.archivos;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilePortal {

	private String nombre;
	private String ruta;
	private String tipo;
	private List<FilePortal> archivos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<FilePortal> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<FilePortal> archivos) {
		this.archivos = archivos;
	}
	
	public static List<FilePortal> loadFiles(File file) {
		List<FilePortal> filesPortal = new ArrayList<FilePortal>();
		File[] archivos = file.listFiles();
		Arrays.sort(archivos);
		for (File f : archivos) {
			FilePortal filePortal = new FilePortal();
			filePortal.setNombre(f.getName());
			filePortal.setRuta(f.getAbsolutePath());
			if (f.isDirectory()) {
				filePortal.setArchivos(loadFiles(f));
				filePortal.setTipo("folder");
			}
			
			if (f.isFile()) {
				filePortal.setTipo("file");
			}
			

			filesPortal.add(filePortal);
			
		}
		return filesPortal;
	}
	
	public static void printFilePortal(FilePortal fp, int tab) {
		System.out.println();
		for(short i = 0; i < tab; i++) {
			System.out.print("--");
		}
		System.out.print(fp.getNombre());
		if (fp.getArchivos() != null && fp.getArchivos().size() > 0) {
			System.out.print(">>>>");
			for (FilePortal f : fp.getArchivos()) {
				printFilePortal(f, (tab + 1));
			}
		}
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
