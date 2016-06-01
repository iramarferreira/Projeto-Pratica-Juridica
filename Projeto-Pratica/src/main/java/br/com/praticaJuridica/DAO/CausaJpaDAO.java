package br.com.praticaJuridica.DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.pratica.Juridica.JBDC.ConnectionFactory;
import br.com.praticaJuridica.model.Causa;



public class CausaJpaDAO {

	private Connection connection;
	private static CausaJpaDAO instance;
    protected EntityManager entityManager;
    
    public static CausaJpaDAO getInstance(){ 
    	if (instance == null){ 
    		instance = new CausaJpaDAO(); 
    	} 
    	return instance; 
    	
    }
    
    private CausaJpaDAO() {
    	
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager(){ 
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudPraticaJuridica"); 
    	if(entityManager == null){ 
    		entityManager = factory.createEntityManager(); 
    	} 
    	return entityManager; 
    }

    public Causa getById(final int id){ 
    	instance = null;
		List <Causa> causas = findAll();
		for(Causa causaBD : causas){
			if(causaBD.getId() == id){
				return causaBD;
				
			}								
		}
		return null;
    }
    
    @SuppressWarnings("unchecked") 
    public List<Causa> findAll(){ 
//    	this.connection = new ConnectionFactory().getConnection();
//    	try{
//			List<Causa> causas = new ArrayList<Causa>();
//			PreparedStatement stmt = connection.prepareStatement("select *from causa ");
//			ResultSet rs = stmt.executeQuery();
//			
//			while(rs.next()){
//				
//				Causa causa = new Causa();
//				
//				causa.setId(rs.getInt("id_causa"));
//				causa.setInteresseAjuizar(rs.getString("interesse_ajuizar"));
//				causa.setNatureza(rs.getString("natureza_causa"));
//				causa.setParteContraria(rs.getString("parte_contraria"));
//				causa.setStatus(rs.getString("status"));
//				causa.getCliente().setId(rs.getInt("id_cliente"));
//				causa.getPessoa().setId(rs.getInt("id_pessoa"));
//				causa.getUsuario().setId(rs.getInt("id_usuario"));
//				
////				Calendar data = Calendar.getInstance();
////				data.setTime(rs.getDate("data_atendimento"));
//				causa.setDataAtendimento(rs.getDate("data_atendimento"));
//				
//				causas.add(causa);
//			}
//			rs.close();
//			stmt.close();
//			return causas;
//		}catch(Exception e){
//			throw new RuntimeException(e);
//		}
	
   	
    	//entityManager.getTransaction().begin();
    	//entityManager.flush();
//    	entityManager.getEntityManagerFactory().getCache().evictAll();
//    	entityManager.clear();
    	List<Causa> causas = entityManager.createQuery("FROM " + Causa.class.getName()).getResultList();
    	//entityManager.getTransaction().commit();
    	for(Causa causa : causas){
    		entityManager.refresh(causa);
    	}
    	//instance = null;
    	return causas;
    }

    public void persist(Causa causa){ 
    	try{
    		System.out.println("1");
    		entityManager.getTransaction().begin(); 
    		entityManager.persist(causa); 
    		entityManager.getTransaction().commit(); 
    		System.out.println("2");
    		instance = null;
    	}catch(Exception ex){ 
    		System.out.println("3");
    		ex.printStackTrace(); 
    		entityManager.getTransaction().rollback(); 
    	} 
    }
    
 public void insert(Causa causa){
    	
    	this.connection = new ConnectionFactory().getConnection();
    	Calendar dataAtendimento;
    	
    	dataAtendimento = Calendar.getInstance();
		dataAtendimento.setTime(causa.getDataAtendimento());
    	
    	String sql = "insert into causa (data_atendimento, interesse_ajuizar, natureza_causa, "
    			+ "parte_contraria, status, id_cliente, id_pessoa, id_usuario) "
    			+ "values (?,?,?,?,?,?,?,?)";
    
    
    	try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setDate(1, new Date(dataAtendimento.getTimeInMillis()));
			stmt.setString(2, causa.getInteresseAjuizar());
			stmt.setString(3, causa.getNatureza());
			stmt.setString(4, causa.getParteContraria());
			stmt.setString(5, causa.getStatus());
			stmt.setInt(6, causa.getCliente().getId());
			stmt.setInt(7, causa.getPessoa().getId());
			stmt.setInt(8, causa.getUsuario().getId());
		
			stmt.execute();
			stmt.close();
			connection = null;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
    	
    }
    
    
    public void getMerge(Causa causa){ 
    	try{ 
    		entityManager.getTransaction().begin(); 
    		entityManager.merge(causa); 
    		entityManager.getTransaction().commit(); 
    		}catch(Exception ex){ 
    			ex.printStackTrace(); 
    			entityManager.getTransaction().rollback(); 
    		} 
    }
    
    public void update(Causa causa){
    	instance = null;
    	this.connection = new ConnectionFactory().getConnection();
    	Calendar dataAtendimento;
    	
    	dataAtendimento = Calendar.getInstance();
		dataAtendimento.setTime(causa.getDataAtendimento());
    	
    	String sql = "update causa set data_atendimento=?, interesse_ajuizar=?, natureza_causa=?, "
    			+ "parte_contraria=?, status=?, id_cliente=?, id_pessoa=?, id_usuario=? where id_causa=?";
    
    
    	try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setDate(1, new Date(dataAtendimento.getTimeInMillis()));
			stmt.setString(2, causa.getInteresseAjuizar());
			stmt.setString(3, causa.getNatureza());
			stmt.setString(4, causa.getParteContraria());
			stmt.setString(5, causa.getStatus());
			stmt.setInt(6, causa.getCliente().getId());
			stmt.setInt(7, causa.getPessoa().getId());
			stmt.setInt(8, causa.getUsuario().getId());
			stmt.setInt(9, causa.getId());
		
			stmt.execute();
			stmt.close();
			connection = null;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
    	
    }
    
//    @SuppressWarnings("unchecked")
//    public void atualizaChaves(int idCliente, int idPessoa,int idUsuario, int idCausa){
//    	entityManager.getTransaction().begin();
//    	Query query1 = entityManager.createNativeQuery("UPDATE causa u SET u.id_cliente = " + idCliente + " WHERE u.id_causa = " + idCausa);
//		//Query query1 = entityManager.createQuery("UPDATE Causa u SET u.cliente.id = " + idCliente + " WHERE u.id LIKE :idCausa");
//		//query1.setParameter("idCliente", "%" + idCliente + "%");
//		//query1.setParameter("idCausa",idCausa );
//		System.out.println(query1.executeUpdate());
//		Query query2 = entityManager.createQuery("UPDATE Causa u SET u.idPessoa = idPessoa WHERE u.idCausa LIKE :idCausa");
//		query2.setParameter("idPessoa", "%" + idPessoa + "%");
//		query2.setParameter("idCausa", "%" + idCausa + "%");
//		System.out.println(query2.executeUpdate());
//		Query query3 = entityManager.createQuery("UPDATE Causa u SET u.idUsuario = idUsuario WHERE u.idCausa LIKE :idCausa");
//		query3.setParameter("idUsuario", "%" + idUsuario + "%");
//		query3.setParameter("idCausa", "%" + idCausa + "%");
//		System.out.println(query3.executeUpdate());
//		entityManager.getTransaction().commit(); 
//	}
    
//    public void remove(Causa causa){ 
//    	
//    	try{ 
//    		entityManager.getTransaction().begin(); 
//    		causa = entityManager.find(Causa.class, causa.getId()); 
//    		entityManager.remove(causa); 
//    		entityManager.getTransaction().commit(); 
//    		}catch(Exception ex){ 
//    			ex.printStackTrace(); 
//    			entityManager.getTransaction().rollback(); 
//    		} 
//    }
    
    public int remove(int id){
    	instance = null;
    	this.connection = new ConnectionFactory().getConnection();
		try{
			List <Causa> causas = findAll();
			for(Causa causaBD : causas){
				
				if(causaBD.getId() == id){
					PreparedStatement stmt = connection.prepareStatement("delete from causa where id_causa=?");
					stmt.setLong(1, id);
					stmt.execute();
					stmt.close();
					return 1;
				}								
			}
			return 0;
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
	}
    
//    public void removeById(final int id){ 
//    	try{ 
//    		Causa causa = getById(id); 
//    		remove(causa); 
//    		}catch(Exception ex){ 
//    			ex.printStackTrace(); 
//    		} 
//    }

    

    
    



}
