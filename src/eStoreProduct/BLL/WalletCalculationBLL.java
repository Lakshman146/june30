package eStoreProduct.BLL;

import org.springframework.stereotype.Component;

import eStoreProduct.DAO.walletDAO;
import eStoreProduct.ExceptionUser.Emptywalletexception;
import eStoreProduct.model.wallet;

@Component
public class WalletCalculationBLL {
	walletDAO wdao;

	// method for calculate the total order price on wallet
	public double WalletCalc(wallet w, double orderprice) throws Emptywalletexception {
		double walletamt = w.getAmount();
		double amttopay = 0;
		// customer only use the 0.8% of amount on order price
		double walletlimitused = 0.8;

		walletlimitused = walletlimitused * orderprice;
		if ((walletamt != 0&&walletlimitused < walletamt) {
			amttopay = orderprice - walletlimitused;
		} else if ((walletamt != 0&&walletlimitused >= walletamt) {
			amttopay = orderprice - walletamt;

		}
		// if wallet amount is zero its throw the exception
		else if (w.getAmount() == 0) {
			throw new Emptywalletexception(w.getAmount(), "Your wallet is Empty");
		}

		return amttopay;
	}

}
