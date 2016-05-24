package br.com.praticaJuridica.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.praticaJuridica.model.Causa;
import br.com.praticaJuridica.model.Cliente;
import br.com.praticaJuridica.model.Endereco;

public class ClienteJpaDAO {

	private static ClienteJpaDAO instance;
    protected EntityManager entityManager;
    
    private ClienteJpaDAO(){
    	entityManager = getEntityManager();
    }
 
    public static ClienteJpaDAO getInstance(){ 
    	if (instance == null){ 
    		instance = new ClienteJpaDAO(); 
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
    
    public Cliente getById(final int id){ 
    	
    	return entityManager.find(Cliente.class, id); 
    	
    }
    
    @SuppressWarnings("unchecked") 
    public List<Cliente> findAll(){ 
    	List<Cliente> clientes =  entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
    	for(Cliente cliente : clientes){
    		entityManager.refresh(cliente);
    	}
    	return clientes;
    }

    public void persist(Cliente cliente){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(cliente); 
    		entityManager.getTransaction().commit(); 
    	}catch(Exception ex){ 
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
    public void getMerge(Cliente cliente){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.merge(cliente); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void remove(Cliente cliente){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		cliente = entityManager.find(Cliente.class, cliente.getId()); 
    		entityManager.remove(cliente); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void removeById(final int id){ 
    	try{ 
    		Cliente cliente = getById(id); 
    		remove(cliente); 
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
