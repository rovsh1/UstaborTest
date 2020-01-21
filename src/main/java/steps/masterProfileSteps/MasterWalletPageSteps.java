package steps.masterProfileSteps;

import net.thucydides.core.annotations.Step;
import pages.masterProfile.MasterWalletPage;

public class MasterWalletPageSteps extends MasterProfileSteps {

    private MasterWalletPage masterWalletPage;

    @Step
    public void pageShouldBeVisible() {
        masterWalletPage.balanceAmountShouldBeVisible();
        masterWalletPage.amountInputShouldBeVisible();
        masterWalletPage.paymentTypeSelectorShouldBeVisible();
        masterWalletPage.submitBtnShouldBeVisible();
    }
}
