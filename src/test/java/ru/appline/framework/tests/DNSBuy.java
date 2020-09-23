package ru.appline.framework.tests;

import org.junit.Test;
import ru.appline.framework.baseTestsClass.BaseTests;
import ru.appline.framework.utils.ConstConteiner;

public class DNSBuy extends BaseTests {
    @Test
    public void startTest() {
        app.getStartPage().fillSearchField(ConstConteiner.psName)
                .clickSearch()
                .clickPsSlimB()
                .writeCostPS()
                .setGuaranteeValue(ConstConteiner.guaranteeName)
                .writeCostPSWithGuarantee()
                .clickBuyButton()
                .fillSearchField(ConstConteiner.gameName)
                .clickSearch()
                .writeCostDetroit()
                .clickBuyButton()
                .checkCost()
                .clickBasket()
                .checkTick()
                .checkCostPS()
                .checkCostGame()
                .checkItog()
                .dellDetroitWithCheck()
                .addSonyPlus()
                .addSonyPlus()
                .checkThreePS()
                .clickReturn()
                .checkLastAddDet();

    }
}
