package konkon.repository;

import konkon.model.Customer;
import konkon.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
  Iterable<Customer> findAllByProvince(Province province);
}