package br.com.fernando.drogaria.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fernando.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;
	
	//Construtor pra poder descobrir qual é a classe que está chamando o generic - Estudar API Reflection
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}	
	
	public void salvar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
			
		} catch (RuntimeException erro) {
			if (transacao != null) {   	// Se começou a transação e mesmo assim deu problema, voltamos ao estado original
				transacao.rollback();
			}
			throw erro;					//Propagando o erro para as camadas superiores
		}
		finally {
			sessao.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}				
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}				
	}
	
	public void excluir(Entidade entidade){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
			
		} catch (RuntimeException erro) {
			if (transacao != null) {   	
				transacao.rollback();
			}
			throw erro;				
		}
		finally {
			sessao.close();
		}	
	}
	
	public void editar(Entidade entidade){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
			
		} catch (RuntimeException erro) {
			if (transacao != null) {   	
				transacao.rollback();
			}
			throw erro;				
		}
		finally {
			sessao.close();
		}	
	}
}