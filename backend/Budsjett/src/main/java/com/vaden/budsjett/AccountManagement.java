/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaden.budsjett;

import com.vaden.budsjett.entities.Account;
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
@Path("account")
@RequestScoped
public class AccountManagement {

    Random rand;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccountManagement
     */
    public AccountManagement() {
        rand = new Random();
    }

    /**
     * Retrieves representation of an instance of com.vaden.budsjett.AccountManagement
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AccountManagement
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Register(@QueryParam("username")String username, 
            @QueryParam("password")String password, 
            @QueryParam("email")String email, 
            @QueryParam("city")String city,
            @QueryParam("address")String address,
            @QueryParam("firstname")String firstname,
            @QueryParam("lastname")String lastname)
    {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager entityManager = emFactory.createEntityManager();
       
        entityManager.getTransaction().begin();
        
        int userID = rand.nextInt(9999);
        Account account = new Account();
        account.setUsername(username);
        account.setId(String.valueOf(userID));
        account.setPassword(password);
        account.setEmail(email);
        account.setCity(city);
        account.setAddress(address);
        account.setFirstname(firstname);
        account.setLastname(lastname);
        
        entityManager.merge(account);
        
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(account.getUsername());
        return Response.status(Response.Status.OK).entity("OK").build();
    }
    
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Login(@QueryParam("username")String username, @QueryParam("password")String password)
    {
         int sessionID = rand.nextInt(9999);
        
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        List<Account> resultByEmail = entityManager.createNamedQuery("Account.findByEmail").setParameter("email", username).getResultList();
        List<Account> resultByUsername = entityManager.createNamedQuery("Account.findByUsername").setParameter("username", username).getResultList();
        if (resultByEmail.size() == 0 && resultByUsername.size() == 0) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (resultByEmail.size() != 0) {
            resultByEmail.get(0).setSession(String.valueOf(sessionID));
            entityManager.merge(resultByEmail.get(0));
        } else {
            resultByUsername.get(0).setSession(String.valueOf(sessionID));
            entityManager.merge(resultByUsername.get(0));
        }
        
              
        entityManager.getTransaction().commit();
       
        int foundAccount = entityManager.createNamedQuery("Account.authorize2").setParameter("email", username).setParameter("password", password)
                .getResultList().size();
        entityManager.close();
        
        if (foundAccount == 1) {
            //Success
            return Response.status(Response.Status.OK).entity(sessionID).build();
        }
        else {
            //Failure
            return Response.status(Response.Status.NOT_FOUND).build();
        }     
    }
    
    
}
