package com.cg.springwallet.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.springwallet.beans.Customer;
import com.cg.springwallet.exception.WalletExceptionImpl;

public interface PaymentWalletService {

	public void createAccount(Customer customer);
	
	public BigDecimal showBalance(String userId);

	public String deposit(String phNumber, BigDecimal amount);

	public String withdraw(String phNumber, BigDecimal amount) throws WalletExceptionImpl;

	public String fundTransfer(String phSender, String phReceiver, BigDecimal amount) throws WalletExceptionImpl;

	public String printTransaction(String userId);
	
	public Customer findCustomer(String userId);

	public List<Customer> showCustomer();
}
