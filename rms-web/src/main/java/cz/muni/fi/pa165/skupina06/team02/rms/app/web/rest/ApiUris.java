package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the 
 * other URIs also for the sub-resources so that it can 
 * reused globally from all the controllers
 * 
 * @author Vojtech Prusa
 */
public abstract class ApiUris {
    public static final String API_PREFIX = "/rest";
    public static final String ROOT_URI_SHOPPINGITEMS   = API_PREFIX + "/shoppingitems"; 
    public static final String ROOT_URI_USERS      = API_PREFIX + "/users";
    public static final String ROOT_URI_SHOPPINGLISTS     = API_PREFIX + "/shoppinglists";
    public static final String ROOT_URI_HOUSEHOLDS = API_PREFIX + "/households";  
}
