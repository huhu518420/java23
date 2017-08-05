package com.kaishengit;

import com.kaishengit.pojo.Customer;
import com.kaishengit.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by huhu5 on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerTest {
    @Autowired
    private CustomerService customerService;
    @Test
    public void findByCustomerId() {

        Customer customer = customerService.findById(1000);
        System.out.println(customer.getCustName());
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setCustName("赵莹莹");
        customer.setAddress("台湾");
        customer.setMobile("1236441");

        customerService.saveCustomer(customer);
    }

    @Test
    public void findAllCustomer() {

       List<Customer> customerList =  customerService.findAll();

       for(Customer customer : customerList) {
           System.out.println(customer.getCustName()+ "->" +customer.getAddress());
       }

    }
}
