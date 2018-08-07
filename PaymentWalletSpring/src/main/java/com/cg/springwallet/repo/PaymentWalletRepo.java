package com.cg.springwallet.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.springwallet.beans.Customer;

@Repository("walletrepo")
public interface PaymentWalletRepo extends CrudRepository<Customer, String> {

}
