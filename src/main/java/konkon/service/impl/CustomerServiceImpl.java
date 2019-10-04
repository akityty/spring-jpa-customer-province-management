package konkon.service.impl;

import konkon.model.Customer;
import konkon.model.Province;
import konkon.repository.CustomerRepository;
import konkon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;
  @Override
  public Iterable<Customer> findAll() {
    return customerRepository.findAll();
  }

  @Override
  public Customer findById(Long id) {
    return customerRepository.findOne(id);
  }

  @Override
  public void save(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void remove(Long id) {
    customerRepository.delete(id);
  }

  @Override
  public Iterable<Customer> findAllByProvince(Province province) {
    return customerRepository.findAllByProvince(province);
  }
}
