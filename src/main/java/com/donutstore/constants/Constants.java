package com.donutstore.constants;

public class Constants
{

    public static final String BASE_PACKAGE = "com.shop.services.mycart";

    // success codes
    public static final String SC_ORDER_ADDED = "01";
    public static final String SM_ORDER_ADDED = "Order added successfully";

    public static final String SC_ORDER_DELETED = "02";
    public static final String SM_ORDER_DELETED = "Order deleted successfully";


    // Messages

    public static final String EC_OPERATION_NOT_SUPPORTED = "901";
    public static final String EM_OPERATION_NOT_SUPPORTED = "Operation not supported";

    public static final String EC_ORDER_IN_PROCESS = "1001";
    public static final String EM_ORDER_IN_PROCESS = "Order already in process";

    public static final String EC_ORDER_NOT_EXISTS = "1002";
    public static final String EM_ORDER_NOT_EXISTS = "Order does not exists";

    public static final String EC_INVALID_QUANTITY = "1003";
    public static final String EM_INVALID_QUANTITY = "Invalid quantity range";

    public static final String EC_EXISTING_CUSTOMER = "1101";
    public static final String EM_EXISTING_CUSTOMER = "Customer already exist";

}
