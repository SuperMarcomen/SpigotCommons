package org.spigot.commons.exceptions;

import org.spigot.commons.messages.Placeholder;

public class SupplierNotProvidedException extends RuntimeException {
	private static final long serialVersionUID = 6297467554119900503L;

	public SupplierNotProvidedException() {
		super();
	}
	
	public SupplierNotProvidedException(Placeholder p) {
		super("Supplier not provided for placeholder [" + p.getName() + "]");
	}

	public SupplierNotProvidedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SupplierNotProvidedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SupplierNotProvidedException(String arg0) {
		super(arg0);
	}

	public SupplierNotProvidedException(Throwable arg0) {
		super(arg0);
	}
	
}
