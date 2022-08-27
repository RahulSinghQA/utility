package org.evs.Vtiger.VerifyTestcase;

import org.evs.Vtiger.Myhomepage.homelandingpage;
import org.evs.Vtiger.Util.Util;
import org.evs.Vtiger.loginpage.Loginpage;
import org.evs.Vtiger.marketingAccounts.AccountsLandingPage;
import org.evs.Vtiger.marketingCampaigns.CampaignsLandingPage;

public class testcase {
	public static void main(String[]args) {
	Util utlobj=new Util();
	utlobj.browser();
	utlobj.seturl("http://localhost:8888/");
	Loginpage lp=new Loginpage(utlobj);
	lp.validlogin();

	homelandingpage obj=new homelandingpage(utlobj);
	obj.navigateToMyhome();
	obj.navigateToHome();
	obj.navigateToHomeCalendar();
	obj.navigateToMarketing();
	obj.navigateToMarketingCampaigns();
    CampaignsLandingPage clp=new CampaignsLandingPage(utlobj);
    clp.searchButton();
    clp.searchNow();
    
   // obj.navigateToMarketingAccounts();
    //AccountsLandingPage alp= new AccountsLandingPage(utlobj);
    //alp.searchButtom();
    
//	obj.navigatehometosales();
//	obj.navigatehometosupports();
//	obj.navigatehometoAnalytics();
//	obj.navigatehometoInventry();
//	obj.navigatehometoTools();
//	obj.navigatehometoSettings();
	}
	

}
