package com.employee.details.controller;

import com.employee.details.model.Employee;
import com.employee.details.service.EmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @EJB
    private EmployeeService employeeService;

    @POST
    public Response createEmployee(Employee employee) {
        employeeService.createEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @GET
    @Path("/{empId}")
    public Response getEmployeeById(@PathParam("empId") int empId) {
        Employee employee = employeeService.findEmployeeById(empId);
        if (employee != null) {
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PUT
    @Path("/{empId}")
    public Response updateEmployee(@PathParam("empId") int empId, Employee employee) {
        Employee existingEmployee = employeeService.findEmployeeById(empId);
        if (existingEmployee != null) {
            employee.setEmpId(empId);
            employeeService.updateEmployee(employee);
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{empId}")
    public Response deleteEmployee(@PathParam("empId") int empId) {
        employeeService.deleteEmployee(empId);
        return Response.noContent().build();
    }


}
























//package com.employee.details.controller;
//
//import com.employee.details.model.Address;
//import com.employee.details.model.Employee;
//import com.employee.details.model.Manager;
//import com.employee.details.model.AssociateConsultantDelivery;
//import com.employee.details.model.ConsultantDelivery;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.HashMap;
//import java.util.Map;
//
//@Path("/")
//public class EmployeeController {
//
//    private static final Map<Integer, Address> addressMap = new HashMap<>();
//    private static final Map<Integer, Employee> employeeMap = new HashMap<>();
//    private static final Map<Integer, Manager> managerMap = new HashMap<>();
//    private static final Map<Integer, AssociateConsultantDelivery> associateMap = new HashMap<>();
//    private static final Map<Integer, ConsultantDelivery> consultantMap = new HashMap<>();
//
//    // Address Endpoints
//    @GET
//    @Path("/addresses")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllAddresses() {
//        return Response.ok(addressMap.values()).build();
//    }
//
//    @GET
//    @Path("/addresses/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAddress(@PathParam("id") int id) {
//        Address address = addressMap.get(id);
//        if (address == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(address).build();
//    }
//
//    @POST
//    @Path("/addresses")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAddress(Address address) {
//        addressMap.put(address.getAddressId(), address);
//        return Response.status(Response.Status.CREATED).entity(address).build();
//    }
//
//    @PUT
//    @Path("/addresses/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateAddress(@PathParam("id") int id, Address address) {
//        if (!addressMap.containsKey(id)) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        address.setAddressId(id);
//        addressMap.put(id, address);
//        return Response.ok(address).build();
//    }
//
//    @DELETE
//    @Path("/addresses/{id}")
//    public Response deleteAddress(@PathParam("id") int id) {
//        if (addressMap.remove(id) == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.noContent().build();
//    }
//
//    // Employee Endpoints
//    @GET
//    @Path("/employees")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllEmployees() {
//        return Response.ok(employeeMap.values()).build();
//    }
//
//    @GET
//    @Path("/employees/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getEmployee(@PathParam("id") int id) {
//        Employee employee = employeeMap.get(id);
//        if (employee == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(employee).build();
//    }
//
//    @POST
//    @Path("/employees")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createEmployee(Employee employee) {
//        employeeMap.put(employee.getEmpId(), employee);
//        return Response.status(Response.Status.CREATED).entity(employee).build();
//    }
//
//    @PUT
//    @Path("/employees/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
//        if (!employeeMap.containsKey(id)) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        employee.setEmpId(id);
//        employeeMap.put(id, employee);
//        return Response.ok(employee).build();
//    }
//
//    @DELETE
//    @Path("/employees/{id}")
//    public Response deleteEmployee(@PathParam("id") int id) {
//        if (employeeMap.remove(id) == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.noContent().build();
//    }
//
//    // Manager Endpoints
//    @GET
//    @Path("/managers")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllManagers() {
//        return Response.ok(managerMap.values()).build();
//    }
//
//    @GET
//    @Path("/managers/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getManager(@PathParam("id") int id) {
//        Manager manager = managerMap.get(id);
//        if (manager == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(manager).build();
//    }
//
//    @POST
//    @Path("/managers")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createManager(Manager manager) {
//        managerMap.put(manager.getEmpId(), manager);
//        return Response.status(Response.Status.CREATED).entity(manager).build();
//    }
//
//    @PUT
//    @Path("/managers/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateManager(@PathParam("id") int id, Manager manager) {
//        if (!managerMap.containsKey(id)) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        manager.setEmpId(id);
//        managerMap.put(id, manager);
//        return Response.ok(manager).build();
//    }
//
//    @DELETE
//    @Path("/managers/{id}")
//    public Response deleteManager(@PathParam("id") int id) {
//        if (managerMap.remove(id) == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.noContent().build();
//    }
//
//    // Associate Consultant Delivery Endpoints
//    @GET
//    @Path("/associate-consultants")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllAssociates() {
//        return Response.ok(associateMap.values()).build();
//    }
//
//    @GET
//    @Path("/associate-consultants/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAssociate(@PathParam("id") int id) {
//        AssociateConsultantDelivery associate = associateMap.get(id);
//        if (associate == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(associate).build();
//    }
//
//    @POST
//    @Path("/associate-consultants")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAssociate(AssociateConsultantDelivery associate) {
//        associateMap.put(associate.getEmpId(), associate);
//        return Response.status(Response.Status.CREATED).entity(associate).build();
//    }
//
//    @PUT
//    @Path("/associate-consultants/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateAssociate(@PathParam("id") int id, AssociateConsultantDelivery associate) {
//        if (!associateMap.containsKey(id)) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        associate.setEmpId(id);
//        associateMap.put(id, associate);
//        return Response.ok(associate).build();
//    }
//
//    @DELETE
//    @Path("/associate-consultants/{id}")
//    public Response deleteAssociate(@PathParam("id") int id) {
//        if (associateMap.remove(id) == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.noContent().build();
//    }
//
//    // Consultant Delivery Endpoints
//    @GET
//    @Path("/consultants")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllConsultants() {
//        return Response.ok(consultantMap.values()).build();
//    }
//
//    @GET
//    @Path("/consultants/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getConsultant(@PathParam("id") int id) {
//        ConsultantDelivery consultant = consultantMap.get(id);
//        if (consultant == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.ok(consultant).build();
//    }
//
//    @POST
//    @Path("/consultants")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createConsultant(ConsultantDelivery consultant) {
//        consultantMap.put(consultant.getEmpId(), consultant);
//        return Response.status(Response.Status.CREATED).entity(consultant).build();
//    }
//
//    @PUT
//    @Path("/consultants/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateConsultant(@PathParam("id") int id, ConsultantDelivery consultant) {
//        if (!consultantMap.containsKey(id)) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        consultant.setEmpId(id);
//        consultantMap.put(id, consultant);
//        return Response.ok(consultant).build();
//    }
//
//    @DELETE
//    @Path("/consultants/{id}")
//    public Response deleteConsultant(@PathParam("id") int id) {
//        if (consultantMap.remove(id) == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.noContent().build();
//    }
//}
