package br.com.praticaJuridica.DAO;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.praticaJuridica.model.Acao;



public class AcaoJpaDAO {

	
	private static AcaoJpaDAO instance;
    protected EntityManager entityManager;
    
    public static AcaoJpaDAO getInstance(){ 
    	if (instance == null){ 
    		instance = new AcaoJpaDAO(); 
    	} 
    	return instance; 
    	
    }
    
    private AcaoJpaDAO() {
    	
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager(){ 
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudPraticaJuridica"); 
    	if(entityManager == null){ 
    		entityManager = factory.createEntityManager(); 
    	} 
    	return entityManager; 
    }

    public Acao getById(final int id){ 
    	return entityManager.find(Acao.class, id); 
    }
    
    @SuppressWarnings("unchecked") 
    public List<Acao> findAll(){ 
    	return entityManager.createQuery("FROM " + Acao.class.getName()).getResultList(); 
    }

    public void persist(Acao acao){ 
    	try{
    		System.out.println("1");
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(acao); 
    		entityManager.getTransaction().commit(); 
    		System.out.println("2");
    		
    	}catch(Exception ex){ 
    		System.out.println("3");
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
    public void getMerge(Acao acao){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.merge(acao); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void remove(Acao acao){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		acao = entityManager.find(Acao.class, acao.getIdAcao()); 
    		entityManager.remove(acao); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void removeById(final int id){ 
    	try{ 
    		Acao acao = getById(id); 
    		remove(acao); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    		} 
    }

    

    
    



}
