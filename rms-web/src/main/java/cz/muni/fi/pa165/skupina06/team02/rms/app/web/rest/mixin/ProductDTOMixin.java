package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class shows an example of Jackson mix-ins in case we do not want to modify one DTO
 * in the API layer
 * @author brossi
 */
@JsonIgnoreProperties({ "imageMimeType", "image" })
public abstract class ProductDTOMixin {
}