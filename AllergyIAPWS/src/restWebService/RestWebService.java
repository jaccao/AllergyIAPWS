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
import java.util.ArrayList;

@RequestScoped
@Path("/allergyws")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })

public class RestWebService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/{mail}/{password}")
	public List<User> isValidLogin(@PathParam("mail") String mail, @PathParam("password") String password) {
		List<User> user = new ArrayList<>();
		User u = UserService.isValidLogin(mail, password);
		if (u != null) {
			user.add(u);
		}
		return user;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers/{idcustomer}")
	public List<CustomerWS> getCustomer(@PathParam("idcustomer") long customerid) {
		List<CustomerWS> customer = new ArrayList<>();
		Customer c = CustomerService.get(customerid);
		if (c != null) {
			customer.add(new CustomerWS(c));
		}
		return customer;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customers")
	public List<CustomerWS> getAllCustomer() {
		List<CustomerWS> customers = new ArrayList<>();
		for (Customer customer : CustomerService.getAll()) {
			customers.add(new CustomerWS(customer));
		}
		return customers;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productCatalog/{id}")
	public List<ProductCatalogWS> getProduct(@PathParam("id") long productid) {
		List<ProductCatalogWS> product = new ArrayList<>();
		ProductCatalog pc = ProductCatalogService.get(productid);
		if (pc != null) {
			product.add(new ProductCatalogWS(pc));
		}
		return product;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productCatalog")
	public List<ProductCatalogWS> getProduct() {
		List<ProductCatalogWS> products = new ArrayList<>();
		for (ProductCatalog product : ProductCatalogService.getAll()) {
			products.add(new ProductCatalogWS(product));
		}
		return products;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/{id}")
	public List<User> getUser(@PathParam("id") long userid) {
		List<User> user = new ArrayList<>();
		User u = UserService.get(userid);
		if (u != null) {
			user.add(u);
		}
		return user;
	}

	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addUser")
	public int addNewUser(User user) {
		try {
			UserService.insert(user);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
}
