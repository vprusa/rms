package cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.controllers;

import cz.muni.fi.pa165.skupina06.team02.rms.app.web.rest.ApiUris;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;

//@Secured("USER")
@RequestMapping(ApiUris.API_PREFIX)
public abstract class BaseController {}