package ru.appline.framework.managers;

import ru.appline.framework.pages.BasketPage;
import ru.appline.framework.pages.SearchPage;
import ru.appline.framework.pages.StartPage;
import ru.appline.framework.pages.ZakazPage;


public class ManagerPages {


    private static ManagerPages managerPages;


    StartPage startPage;


    BasketPage basketPage;


    SearchPage searchPage;


    ZakazPage zakazPage;


    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }


    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }


    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }


    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
        }
        return searchPage;
    }


    public ZakazPage getZakazPage() {
        if (zakazPage == null) {
            zakazPage = new ZakazPage();
        }
        return zakazPage;
    }
}




