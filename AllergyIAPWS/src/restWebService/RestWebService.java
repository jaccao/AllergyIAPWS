package restWebService;
import com.allergyiap.beans.*;
import com.allergy.service.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RequestScoped
@Path("/allergyws")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })

public class RestWebService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello()
	{
		// http://localhost:8080/AllergyIAPWS/rest/allergyws/hello
		return "Hello World";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{mail}/{password}")
	public User isValidLogin(@PathParam("mail") String mail, @PathParam("password") String password){
		return UserService.isValidLogin(mail, password);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers/{id}")
	public Customer getCustomer(@PathParam("id") long customerid){
		return CustomerService.get(customerid);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers")
	public List<Customer> getAllCustomer(){
		return CustomerService.getAll();
	} 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productCatalog/{id}")
	public ProductCatalog getProduct(@PathParam("id") long productid){
		return ProductCatalogService.get(productid);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productCatalog")
	public List<ProductCatalog> getProduct(){
		return ProductCatalogService.getAll();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/{id}")
	public User getUser(@PathParam("id") long userid){
		return UserService.get(userid);
	}
	
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addUser")
	public String addNewUser(User user){		
		try {
			UserService.insert(user);
			return "User added successfully";
  
		} catch (Exception e) {
			return "Some error adding new user";
		}
 
	}

}
