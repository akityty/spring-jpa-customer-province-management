package konkon.controller;

import konkon.model.Customer;
import konkon.model.Province;
import konkon.service.CustomerService;
import konkon.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CustomerService customerService;

  @GetMapping("/provinces")
  public ModelAndView showProvinces(){
    Iterable<Province> provinces = provinceService.findAll();
    ModelAndView modelAndView = new ModelAndView("/province/list");
    modelAndView.addObject("provinces", provinces);
    return modelAndView;
  }
  @GetMapping("/create-province")
  public ModelAndView showCreateProvinceForm(){
    ModelAndView modelAndView = new ModelAndView("/province/create");
    modelAndView.addObject("province", new Province());
    return modelAndView;
  }
  @PostMapping("/create-province")
  public ModelAndView createProvince(@ModelAttribute Province province){
    provinceService.save(province);
    ModelAndView modelAndView = new ModelAndView("/province/create");
    modelAndView.addObject("province", new Province());
    modelAndView.addObject("message", "New Province created successfully");
    return modelAndView;
  }
  @GetMapping("/edit-province/{id}")
  public ModelAndView showEditForm(@PathVariable Long id){
    Province provinceObject =  provinceService.findById(id);
    if(provinceObject != null){
      ModelAndView modelAndView = new ModelAndView("/province/edit");
      modelAndView.addObject("province", provinceObject);
      return modelAndView;
    }else{
      ModelAndView modelAndView = new ModelAndView("/error");
      return modelAndView;
    }
  }
  @PostMapping("/edit-province")
  public ModelAndView updateProvince(@ModelAttribute Province province){
    provinceService.save(province);
    ModelAndView modelAndView = new ModelAndView("/province/edit");
    modelAndView.addObject("province",province);
    modelAndView.addObject("message", "Province updated successfully");
    return modelAndView;
  }
  @GetMapping("/delete-province/{id}")
  public ModelAndView showDeleteForm(@PathVariable Long id){
    Province provinceObject = provinceService.findById(id);
    if(provinceObject != null){
      ModelAndView modelAndView = new ModelAndView("/province/delete");
      modelAndView.addObject("province", provinceObject);
      return modelAndView;
    }else{
      ModelAndView modelAndView = new ModelAndView("/error");
      return modelAndView;
    }
  }
  @PostMapping("/delete-province")
  public ModelAndView deleteProvince(@ModelAttribute Province province){
    provinceService.remove(province.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/province/list");
    return modelAndView;
  }
  @GetMapping("/view-province/{id}")
  public ModelAndView showViewForm(@PathVariable Long id){
    Province provinceObject =  provinceService.findById(id);
    if(provinceObject != null){
      Iterable<Customer>  customers = customerService.findAllByProvince(provinceObject);
      ModelAndView modelAndView = new ModelAndView("/province/view");
      modelAndView.addObject("province", provinceObject);
      modelAndView.addObject("customers",customers);
      return modelAndView;
    }else{
      ModelAndView modelAndView =  new ModelAndView("/error");
      return modelAndView;
    }
  }

}
