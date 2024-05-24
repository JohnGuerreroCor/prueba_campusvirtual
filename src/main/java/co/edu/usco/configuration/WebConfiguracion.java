package co.edu.usco.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import co.edu.usco.inscripciones.utilidad.Constantes;
import co.edu.usco.inscripciones.utilidad.FileUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "co.edu.usco")

// @PropertySource(value = { "classpath:configuraciones.properties" })
//@PropertySource(value = "file:/var/configCampus/portalCampus.properties")
@PropertySource(value = "file:C:\\var\\configCampus\\portalCampus.properties")
public class WebConfiguracion extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Autowired
	Environment environment;
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	// }

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultiPartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean
	public JasperReportsViewResolver jasperReportsViewResolver() throws Exception {
		JasperReportsViewResolver bean = new JasperReportsViewResolver();
		bean.setPrefix("/WEB-INF/reports/");
		bean.setSuffix(".jasper");
		bean.setJdbcDataSource(dataSourceAcademiaInvitado());
		bean.setViewNames("*_report");
		bean.setViewClass(JasperReportsMultiFormatView.class);
		bean.setOrder(1);
		return bean;
	}

	@Bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		bean.setOrder(2);
		return bean;
	}
	
	@Bean(name = "constantes")
	public Constantes constantes() {

		Constantes constantes = new Constantes();
		constantes.RUTA_ADMIN = environment.getProperty("rutaServidorAdministrador");
		constantes.RUTA_PORTAL = environment.getProperty("rutaServidorPortal");
		constantes.RUTA_SAKAI = environment.getProperty("rutaSakai");
		constantes.RUTA_GENERAR_FACTURA = environment.getProperty("rutaGenerarFacturas");
		constantes.RUTA_INSCRIPCIONES_FORMAL = environment.getProperty("rutaInscripcionesFormal");
		constantes.LOGIN = environment.getProperty("login");
		constantes.TRANKEY = environment.getProperty("tranKey");
		constantes.ENDPOINT = environment.getProperty("endpoint");
		constantes.PAGOSENLINEA_ESTADO_PENDIENTE = environment.getProperty("wepPagosenLineaEstadoPendiente");
		constantes.RUTA_GENERAR_FACTURA_ONLINE = environment.getProperty("rutaGenerarFacturaOnline");
		return constantes;
	}

	@Bean(name = "fileUtil")
	public FileUtil getFileUtil() {
		String[] pahts = { environment.getProperty("imagePath"), environment.getProperty("certificadosPath"),
				environment.getProperty("rutaGuiasSakaiEstudiantes"), environment.getProperty("rutaGuiasSakaiDocentes"),
				environment.getProperty("routeAssets") };
		return new FileUtil(pahts);
	}

	@Bean(name = "dataSourceAcademiaInvitado")
	public DataSource dataSourceAcademiaInvitado() throws Exception {
		/*
		 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 * dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		 * dataSource.setUrl(
		 * "jdbc:jtds:sqlserver://172.16.1.85:1433/academia3000_jancarlos");
		 * dataSource.setUsername("jancarlos");
		 * dataSource.setPassword("123Jankarlos:.,"); return dataSource;
		 */

		Context ctx = new InitialContext();

		String dataSourceName = environment.getProperty("dataSource");
		return (DataSource) ctx.lookup(dataSourceName);
	}

}
