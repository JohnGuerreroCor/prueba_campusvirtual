/**
 * 
 */
package co.edu.usco.inscripciones.dao;

import java.util.List;

import co.edu.usco.inscripciones.model.Departamento;
import co.edu.usco.inscripciones.model.Eps;
import co.edu.usco.inscripciones.model.EpsTipoAfiliacion;
import co.edu.usco.inscripciones.model.EstadoCivil;
import co.edu.usco.inscripciones.model.EstratoSocioeconomico;
import co.edu.usco.inscripciones.model.GrupoSanguineo;
import co.edu.usco.inscripciones.model.InstitucionEducativa;
import co.edu.usco.inscripciones.model.LenguaNativa;
import co.edu.usco.inscripciones.model.Municipio;
import co.edu.usco.inscripciones.model.NivelAcademico;
import co.edu.usco.inscripciones.model.Pais;
import co.edu.usco.inscripciones.model.TipoIdentificacion;

/**
 * @author ANDRES-GPIE
 *
 */
public interface DatosInscripcionDao {
	/**
	 * Crear Lista de la Eps
	 * @return Lista de todas las eps
	 */
	public List<Eps> crearListaEps();
	
	/**
	 * Crear lista de los Tipos de Afiliacion de las EPS
	 * @return Lista de todos los tipos de afiliaciones
	 */
	public List<EpsTipoAfiliacion> crearListaEpsTipoAfiliacion();
	
	/**
	 * Crear Lista de los grupos Sanguineos
	 * @return Lista de todos los grupos sanguineos
	 */
	public List<GrupoSanguineo> crearListaGrupoSanguineo();
	
	/**
	 * Crear lista de los Estados Civiles
	 * @return Lista de todos los estados civiles
	 */
	public List<EstadoCivil> crearListaEstadoCivil();
	
	/**
	 * Crear lista con todos los estratos socioeconomicos
	 * @return Lista con todos los estratos socioeconomicos
	 */
	public List<EstratoSocioeconomico> crearListaEstratoSocioeconomico();
	
	/**
	 * Crear lista con todas las lenguas Nativas
	 * @return Lista con todas las lenguas Nativas
	 */
	public List<LenguaNativa> crearListaLenguaNativa();
	
	/**
	 * Crear lista con todas las instituciones educativas
	 * @return Lista con todas las instituciones educativas
	 */
	public List<InstitucionEducativa> crearListaInstitucionEducativa();
	
	/**
	 * Crear lista de los Paises
	 * @return listado de todos los paises
	 */
	public List<Pais> crearListaPais();
	
	/**
	 * Crear Lista de todos los departamentos
	 * @param idPais Se obtiene el codigo del pais
	 * @return Listado de los departamentos del pais seleccionado
	 */
	public List<Departamento> crearListaDepartamento(int idPais);
	
	/**
	 * Crear lista con todos los municipios
	 * @param idDep se obtiene el codigo del departamento
	 * @return Listado de todos los municipios del departamento seleccionado
	 */
	public List<Municipio> crearListaMunicipio(int idDep);
	
	/**
	 * Crear lista de los tipos de identificacion
	 * @return Lista con todos los tipos de identificacion
	 */
	public List<TipoIdentificacion> crearListaTipoIdentificacion();
	
	/**
	 * Crear lista de los niveles academicos 
	 * @return lista con los noveles academicos
	 */
	public List<NivelAcademico> crearListaNivelAcademico();
	
}
