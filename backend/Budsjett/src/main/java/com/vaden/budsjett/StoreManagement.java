/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaden.budsjett;

import com.vaden.budsjett.entities.Account;
import com.vaden.budsjett.entities.Products;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Vaden
 */
@Path("Store")
@RequestScoped
public class StoreManagement {

    Random rand;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StoreManagement
     */
    public StoreManagement() {
        rand = new Random();
    }

    /**
     * Retrieves representation of an instance of com.vaden.budsjett.StoreManagement
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of StoreManagement
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Add(@QueryParam("name")String name, 
            @QueryParam("price")String price, 
            @QueryParam("storename")String storename,
            @QueryParam("sessionId")String sessionId)
    {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager entityManager = emFactory.createEntityManager();
        int productID = rand.nextInt(9999);
        entityManager.getTransaction().begin();
        
        List<Account> account = entityManager.createNamedQuery("Account.findBySession").setParameter("session", sessionId)
                .getResultList();
        if (account.size() == 0) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } 
        Products product = new Products();
        product.setName(name);
        product.setPrice(price);
        product.setStorename(storename);
        product.setUserid(account.get(0).getId());
        product.setId(String.valueOf(productID));
        entityManager.persist(product);
        
        entityManager.getTransaction().commit();
        entityManager.close();
        
        
        return Response.status(Response.Status.OK).entity("OK").build();
    }
    
    @GET
    @Path("/getProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> GetProducts()
    {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager entityManager = emFactory.createEntityManager();
        int productID = rand.nextInt(9999);
        entityManager.getTransaction().begin();
        
        List<Products> products = entityManager.createNamedQuery("Products.findAll").getResultList();
         
        entityManager.getTransaction().commit();
        entityManager.close();
        
        
        return products;
    }
    
}
