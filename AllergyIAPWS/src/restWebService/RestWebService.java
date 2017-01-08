package restWebService;

import com.allergyiap.beans.*;
import com.allergy.service.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	/*For check if the login is correct -> mail and password correct*/
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
	@Path("/customer")
	public List<CustomerWS> getAllCustomer() {
		List<CustomerWS> customers = new ArrayList<>();
		for (Customer customer : CustomerService.getAll()) {
			customers.add(new CustomerWS(customer));
		}
		return customers;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products/{id}")
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
	@Path("/product_catalog")
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
	
	//Method to modify the configuration of a user -> Is useful for 
	//change the configuration or change the password of the user
	@PUT
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/updateUser")
	public int updateUser(User user) {
		try {
			UserService.update(user);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
	
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/addUserAllergy/")
	public int addUserAllergy(UserAllergy u) {
		try {
			UserAllergyService.insert(new UserAllergy(u.getId_user(), u.getId_allergy()));
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
	
	@DELETE
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/deleteUserAllergy/")
	public int deleteUserAllergy(UserAllergy u) {
		try {
			UserAllergyService.delete(u.getId_user(), u.getId_allergy());
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userAllergies/{iduser}")
	public List<Allergy> getUserAllergies(@PathParam("iduser")long iduser) {
		List<Allergy> allergies = new ArrayList<>();
		allergies = UserAllergyService.getAllergyesByUser(iduser);
		return allergies;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/station")
	public List<Station> getStations() {
		List<Station> stations = new ArrayList<>();
		stations = StationService.getAll();
		return stations;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allergy")
	public List<Allergy> getAllergy() {
		List<Allergy> allergies = new ArrayList<>();
		allergies = AllergyService.getAll();
		return allergies;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/stations/{id}")
	public List<Station> getStationById(@PathParam("id")long idstation) {
		List<Station> station = new ArrayList<>();
		Station s = StationService.get((int)idstation);
		if (s != null) {
			station.add(s);
		}
		return station;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pharmacy")
	public List<Pharmacy> getPharmacies() {
		List<Pharmacy> pharmacies = new ArrayList<>();
		pharmacies = PharmacyService.getAll();
		return pharmacies;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pharmacies/{id}")
	public List<Pharmacy> getPharmacy(@PathParam("id") long pharmacy_id) {
		List<Pharmacy> pharmacy = new ArrayList<>();
		Pharmacy p = PharmacyService.get(pharmacy_id);
		if (p != null) {
			pharmacy.add(p);
		}
		return pharmacy;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/customerPharmacies/{id}")
	public List<Pharmacy> getPharmaciesByCustomer(@PathParam("id") long customerid) {
		List<Pharmacy> pharmacies = new ArrayList<>();
		pharmacies = RelationPharmaciesCustomersService.getPharmaciesByCustomer(customerid);
		return pharmacies;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products/customer/{id}")
	public List<ProductCatalog> getProdcutsByCustomer(@PathParam("id") long id_customer) {
		List<ProductCatalog> products = new ArrayList<>();
		products = ProductCatalogService.getAllByCustomer(id_customer);
		return products;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allergy_level")
	public List<AllergyLevel> getAllergiesLevel() {
		List<AllergyLevel> levels = new ArrayList<>();
		levels = AllergyLevelService.getAll();
		return levels;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/relation_pharmacies_customers")
	public List<RelationPharmaciesCustomers> getRelationPharmaciesCustomers() {
		List<RelationPharmaciesCustomers> all = RelationPharmaciesCustomersService.getAll();
		return all;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user_allergies")
	public List<UserAllergy> getUserAllergy() {
		List<UserAllergy> all = UserAllergyService.getAll();
		return all;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user_information")
	public List<User> getUserInformation() {
		List<User> all = UserService.getAll();
		return all;
	}
}
