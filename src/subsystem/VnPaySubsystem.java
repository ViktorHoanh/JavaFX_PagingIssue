package subsystem;

//import entity.payment.CreditCard;

import common.exception.TransactionFailedException;
import common.exception.TransactionNotDoneException;
import common.exception.TransactionReverseException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentTransaction;
import subsystem.vnPay.VnPaySubsystemController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 *
 *
 */
public class VnPaySubsystem implements VnPayInterface {

    /**
     * Represent the controller of the subsystem.
     */
    private VnPaySubsystemController ctrl;

    /**
     * Initializes a newly created {@code InterbankSubsystem} object so that it
     * represents an Interbank subsystem.
     */
    public VnPaySubsystem() {
        this.ctrl = new VnPaySubsystemController();
    }

    /**
     * @see VnPayInterface#payOrder(entity.payment.CreditCard, int,
     * java.lang.String)
     */
    public String generatePayUrl(int amount, String contents) {

        try {
            return ctrl.generatePayOrderUrl(amount, contents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentTransaction makePaymentTransaction(Map<String, String> response) throws ParseException {
            return ctrl.makePaymentTransaction(response);
    }
}
