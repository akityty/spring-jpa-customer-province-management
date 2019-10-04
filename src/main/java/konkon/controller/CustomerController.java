package konkon.controller;


import konkon.model.Customer;
import konkon.model.Province;
import konkon.service.CustomerService;
import konkon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private ProvinceService provinceService;


  @ModelAttribute("provinces")
  public Iterable<Province> provinces() {
    return provinceService.findAll();
  }
  @GetMapping("/customers")
  public ModelAndView showCustomers(@RequestParam("s") Optional<String> s, Pageable pageable){
   /* Page<Customer> customers = customerService.findAll(pageable);*/
    Page<Customer> customers;
    if(s.isPresent()){
      customers = customerService.findAllByFirstNameContaining(s.get(), pageable);
    } else {
      customers = customerService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("/customer/list");
    modelAndView.addObject("customers", customers);
    return modelAndView;
  }

  @GetMapping("/create-customer")
  public ModelAndView showCreateCustomerForm(){
    ModelAndView modelAndView = new ModelAndView("/customer/create");
    modelAndView.addObject("customer", new Customer());
    return modelAndView;
  }

  @PostMapping("/create-customer")
  public ModelAndView saveCustomer(@ModelAttribute Customer customer){
    customerService.save(customer);
    ModelAndView modelAndView = new ModelAndView("/customer/create");
    modelAndView.addObject("customer", new Customer());
    modelAndView.addObject("message","New Customer created Successfully");
    return modelAndView;
  }

  @GetMapping("/edit-customer/{id}")
  public ModelAndView showEditForm (@PathVariable Long id){
   Customer customerObject =  customerService.findById(id);
   if(customerObject != null){
     ModelAndView modelAndView = new ModelAndView("/customer/edit");
     modelAndView.addObject("customer",customerObject);
     return modelAndView;
   }else{
     ModelAndView modelAndView = new ModelAndView("/error");
     return modelAndView;
   }
  }

  @PostMapping("/edit-customer/{id}")
  public ModelAndView updateCustomer(@ModelAttribute Customer customer){
    customerService.save(customer);
    ModelAndView modelAndView = new ModelAndView("customer/edit");
    modelAndView.addObject("customer", customer);
    modelAndView.addObject("message", "Customer update Successfully");
    return modelAndView;
  }
  @GetMapping("/delete-customer/{id}")
  public ModelAndView showDeleteForm(@PathVariable Long id){
    Customer customer = customerService.findById(id);
    if(customer != null){
      ModelAndView modelAndView = new ModelAndView("/customer/delete");
      modelAndView.addObject("customer",customer);
      return modelAndView;
    }else{
      ModelAndView modelAndView = new ModelAndView("/error");
      return modelAndView;
    }
  }
  @PostMapping("/delete-customer")
  public ModelAndView deleteCustomer(@ModelAttribute Customer customer){
    customerService.remove(customer.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/customer/list");
    return modelAndView;
  }
  @GetMapping("/index")
  public ModelAndView showIndex(){
    ModelAndView modelAndView = new ModelAndView("/index");
    return modelAndView;
  }
}
