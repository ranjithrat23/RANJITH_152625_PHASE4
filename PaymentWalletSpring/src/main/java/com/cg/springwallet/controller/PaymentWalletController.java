package com.cg.springwallet.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springwallet.beans.Customer;
import com.cg.springwallet.exception.WalletExceptionImpl;
import com.cg.springwallet.service.PaymentWalletService;

@RestController
public class PaymentWalletController {

	@Autowired
	private PaymentWalletService service;

	@RequestMapping(value = "/wallet/create", method = RequestMethod.POST)
	public void createAccount(@RequestBody Customer customer) {
		service.createAccount(customer);
	}

	@RequestMapping("/wallet/balance/{userId}")
	public BigDecimal showBalance(@PathVariable String userId) {

		return service.showBalance(userId);
	}

	@RequestMapping(value = "/wallet/deposit/{amount}/{id}", method = RequestMethod.PUT)
	public String deposit(@PathVariable String id, @PathVariable BigDecimal amount) {
		return service.deposit(id, amount);
	}

	@RequestMapping(value = "/wallet/withdraw/{amount}/{id}", method = RequestMethod.PUT)
	public String withdraw(@PathVariable String id, @PathVariable BigDecimal amount) {
		try {
			return service.withdraw(id, amount);
		} catch (WalletExceptionImpl e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/wallet/transfer/{amount}/{id}/{idReceiver}", method = RequestMethod.PUT)
	public String fundTransfer(@PathVariable BigDecimal amount, @PathVariable String id,
			@PathVariable String idReceiver) {
		try {
			return service.fundTransfer(id, idReceiver, amount);
		} catch (WalletExceptionImpl e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/wallet/transaction/{id}")
	public String printTransaction(@PathVariable String id) {
		return service.printTransaction(id);
	}

	@RequestMapping("/wallet/{userId}")
	public Customer findCustomer(@PathVariable String userId) {

		return service.findCustomer(userId);
	}

	@RequestMapping("/wallet")
	public List<Customer> showCustomer() {

		return service.showCustomer();
	}
}
