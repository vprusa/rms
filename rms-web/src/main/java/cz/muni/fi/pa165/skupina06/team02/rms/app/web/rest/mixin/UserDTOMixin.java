package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author brossi
 */
@JsonIgnoreProperties({ "passwordHash"})
public class UserDTOMixin {
    
}