package com.vaden.budsjett;

public class Services {
    public static final String BASE_URL = "http://espensv-linux.uials.no:8080/";
    public static final String LOGIN_URL = BASE_URL + "Budsjett/api/account/login?";
    public static final String REGISTER_URL = BASE_URL + "Budsjett/api/account/register";
    public static final String STORE_GET_URL = BASE_URL + "Budsjett/api/Store/getProducts";
    public static final String STORE_ADD_URL = BASE_URL + "Budsjett/api/Store/add";
    public static String SESSION_ID = "";
}
