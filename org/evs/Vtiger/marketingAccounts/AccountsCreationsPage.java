package org.evs.Vtiger.marketingAccounts;

import org.evs.Vtiger.Util.Util;

public class AccountsCreationsPage {
	public Util utl;
	public AccountsCreationsPage(Util obutil) {
		utl=obutil;
	}
	public void saveButton() {
		utl.sendkey("//input[@name='campaignname']", "xpath", "vinodyadav");
	}
	public void cancleButton() {
		utl.sendkey("//input[@name='campaignname']", "xpath", "vinodyadav");
	}


}
