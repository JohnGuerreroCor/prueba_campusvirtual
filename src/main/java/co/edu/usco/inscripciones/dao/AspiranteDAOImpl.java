package co.edu.usco.inscripciones.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.usco.inscripciones.model.Certificado;

@Repository("aspiranteDAO")
public class AspiranteDAOImpl implements AspiranteDao {

	@Autowired
	DataSource dataSourceAcademiaInvitado;

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource() {
		this.jdbcTemplateObject = new JdbcTemplate(dataSourceAcademiaInvitado);
	}
	@Override
	public void guardarCertificados(final List<Certificado> certificados) {
		// String SQL = "INSERT INTO certificado (cert_nombre_original,
		// cert_nombre_enc, cert_extension, cert_ubicacion) VALUES (?,?,?,?);";

		String SQL = "INSERT INTO inscripciones.documentosEstudiosAnteriores (nombre_completo, nombre_encriptado, tipo, ruta, registro) VALUES (?,?,?,?,?);";

		jdbcTemplateObject.batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Certificado certificado = certificados.get(i);
				ps.setString(1, certificado.getName());
				ps.setString(2, certificado.getNameEnc());
				ps.setString(3, certificado.getFileExtension());
				ps.setString(4, certificado.getFileLocation());
				ps.setLong(5, certificado.getCodigoRegistro());
			}

			public int getBatchSize() {
				// TODO Auto-generated method stub
				return certificados.size();
			}
		});
	}
	
}
