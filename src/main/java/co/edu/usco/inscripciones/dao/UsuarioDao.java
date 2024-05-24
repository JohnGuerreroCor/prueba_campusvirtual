package co.edu.usco.inscripciones.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.edu.usco.configuration.UscoGrantedAuthority;
import co.edu.usco.configuration.User;

public interface UsuarioDao {

	public User obtenerPorNombre(String username) throws UsernameNotFoundException;

	public List<UscoGrantedAuthority> listarRoles(int usuario);

}
