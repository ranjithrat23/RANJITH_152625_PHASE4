package com.cg.springwallet.exception;

public class WalletExceptionImpl extends Exception{
	private static final long serialVersionUID = 1L;
	
	public WalletExceptionImpl() {
		super();
	}
	public WalletExceptionImpl(String message) {
		super(message);
	}
	
}
