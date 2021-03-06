package br.com.praticaJuridica.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.praticaJuridica.model.Causa;
import br.com.praticaJuridica.model.Endereco;
import br.com.praticaJuridica.model.Pessoa;


public class PessoaJpaDAO {

	private static PessoaJpaDAO instance;
    protected EntityManager entityManager;
	
    private PessoaJpaDAO(){
    	entityManager = getEntityManager();
    }
 
    public static PessoaJpaDAO getInstance(){ 
    	if (instance == null){ 
    		instance = new PessoaJpaDAO(); 
    	} 
    	return instance; 
    }
    
    private EntityManager getEntityManager(){ 
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudPraticaJuridica"); 
    	if(entityManager == null){ 
    		entityManager = factory.createEntityManager(); 
    	} 
    	return entityManager; 
    }
    
    public Pessoa getById(final int id){ 
    	
    	return entityManager.find(Pessoa.class, id); 
    	
    }
    
    @SuppressWarnings("unchecked") 
    public List<Pessoa> findAll(){ 
    	List<Pessoa> pessoas = entityManager.createQuery("FROM " + Pessoa.class.getName()).getResultList();
    	for(Pessoa pessoa: pessoas){
    		entityManager.refresh(pessoa);
    	}
    	return pessoas;
    }
    
    
    public int pegaId(){
    	int resultado;
    	resultado = entityManager.createNativeQuery("SELECT max(id_endereco) from endereco").getMaxResults();
    	return resultado;
    }

    public void persist(Pessoa pessoa){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(pessoa); 
    		entityManager.getTransaction().commit(); 
    		instance = null;
    	}catch(Exception ex){ 
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
    public void getMerge(Pessoa pessoa){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.merge(pessoa); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void remove(Pessoa pessoa){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		pessoa = entityManager.find(Pessoa.class, pessoa.getId()); 
    		entityManager.remove(pessoa); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void removeById(final int id){ 
    	try{ 
    		Pessoa pessoa = getById(id); 
    		remove(pessoa); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    		} 
    }

    public void adicionaEndereco(Endereco endereco){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(endereco); 
    		entityManager.getTransaction().commit(); 
    	}catch(Exception ex){ 
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
  public Endereco getByIdEndereco(final int id){ 
    	
    	return entityManager.find(Endereco.class, id); 
    	
    }
  
  public void getMergeEndereco(Endereco endereco){ 
  	try{ 
  		entityManager.getTransaction().begin(); 
  		entityManager.merge(endereco); 
  		entityManager.getTransaction().commit(); 
  		}catch(Exception ex){ 
  			ex.printStackTrace(); 
  			entityManager.getTransaction().rollback(); 
  		} 
  }
    
    
    
    
}
