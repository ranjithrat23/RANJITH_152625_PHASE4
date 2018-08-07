package com.cg.springwallet.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springwallet.beans.Customer;
import com.cg.springwallet.exception.WalletException;
import com.cg.springwallet.exception.WalletExceptionImpl;
import com.cg.springwallet.repo.PaymentWalletRepo;

@Service
public class PaymentWalletServiceImpl implements PaymentWalletService {

	@Autowired
	private PaymentWalletRepo repo;

	@Override
	public void createAccount(Customer customer) {
		repo.save(customer);
	}

	@Override
	public BigDecimal showBalance(String userId) {
		return repo.findById(userId).get().getBalance();
	}

	@Override
	public String deposit(String userId, BigDecimal amount) {

		Customer customer = repo.findById(userId).get();
		customer.setBalance(customer.getBalance().add(amount));
		customer.setTransaction(customer.getTransaction().concat("\n Rupees " + amount + " Deposited"));
		repo.save(customer);
		return amount + " deposited to your account";
	}

	@Override
	public String withdraw(String userId, BigDecimal amount) throws WalletExceptionImpl {
		Customer customer = repo.findById(userId).get();
		if (customer.getBalance().compareTo(amount)==1) {
			customer.setBalance(customer.getBalance().subtract(amount));
			customer.setTransaction(customer.getTransaction().concat("\n Rupees " + amount + " Withdrawn"));
			repo.save(customer);
			return amount + " withdrawn";
		} else
			throw new WalletExceptionImpl(WalletException.ERROR1);
	}

	@Override
	public String fundTransfer(String idSender, String idReceiver, BigDecimal amount) throws WalletExceptionImpl {

		Customer sender = repo.findById(idSender).get();
		if (sender.getBalance().compareTo(amount)==1) {
			sender.setBalance(sender.getBalance().subtract(amount));
			sender.setTransaction(
					sender.getTransaction().concat("\n Rupees " + amount + " Transfered to " + idReceiver));
			repo.save(sender);

			Customer receiver = repo.findById(idReceiver).get();
			receiver.setBalance(receiver.getBalance().add(amount));
			receiver.setTransaction(
					receiver.getTransaction().concat("\n Rupees " + amount + " Received from " + idSender));
			repo.save(receiver);

			return amount + " Transfered from " + idSender + " to " + idReceiver;
		} else
			throw new WalletExceptionImpl(WalletException.ERROR1);
	}

	@Override
	public String printTransaction(String userId) {
		return repo.findById(userId).get().getTransaction();
	}

	@Override
	public Customer findCustomer(String userId) {

		return repo.findById(userId).get();
	}

	@Override
	public List<Customer> showCustomer() {
		List<Customer> list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		return list;
	}

}
