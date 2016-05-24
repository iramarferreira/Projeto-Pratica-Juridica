package br.com.praticaJuridica.DAO;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.praticaJuridica.model.Pessoa;
import br.com.praticaJuridica.model.Usuario;


public class UsuarioJpaDAO {

	private static UsuarioJpaDAO instance;
    protected EntityManager entityManager;
	
    private UsuarioJpaDAO(){
    	entityManager = getEntityManager();
    }
    
    public static UsuarioJpaDAO getInstance(){ 
    	if (instance == null){ 
    		instance = new UsuarioJpaDAO(); 
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
 
    public Usuario getById(final int id){ 
    	return entityManager.find(Usuario.class, id); 
    }
    
    @SuppressWarnings("unchecked") 
    public List<Usuario> findAll(){ 
    	List<Usuario> usuarios = entityManager.createQuery("FROM " + Usuario.class.getName()).getResultList();
    	for(Usuario usuario: usuarios){
    		entityManager.refresh(usuario);
    	}
    	return usuarios;
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> findAdmin(){
    	String administrador = "administrador";
    	Query query = entityManager.createQuery("FROM Usuario u WHERE u.tipoUsuario LIKE :administrador");
    	query.setParameter("administrador", "%" + administrador + "%");
    	return query.getResultList();
    }

    public void persist(Usuario usuario){ 
    	try{
    		System.out.println("1");
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(usuario); 
    		entityManager.getTransaction().commit(); 
    		System.out.println("2");
    		
    	}catch(Exception ex){ 
    		System.out.println("3");
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
    public void getMerge(Usuario usuario){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.merge(usuario); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void remove(Usuario usuario){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		usuario = entityManager.find(Usuario.class, usuario.getId()); 
    		entityManager.remove(usuario); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void removeById(final int id){ 
    	try{ 
    		Usuario usuario = getById(id); 
    		remove(usuario); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    		} 
    }

    
    
}
