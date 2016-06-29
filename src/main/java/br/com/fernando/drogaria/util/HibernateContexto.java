package br.com.fernando.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getFabricadesessoes().close();
		
	}
	
	//Método chamado qdo ligamos o Tomcat
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getFabricadesessoes();
		
	}

}
