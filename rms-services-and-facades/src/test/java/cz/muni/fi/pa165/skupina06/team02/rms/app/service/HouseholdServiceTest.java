package cz.muni.fi.pa165.skupina06.team02.rms.app.service;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.HouseholdDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class HouseholdServiceTest {

    @Mock
    private HouseholdDao householdDao;

    @InjectMocks
    private HouseholdService householdService = new HouseholdServiceImpl();

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Household household;
    private User tenant1;
    private User tenant2;

    @BeforeMethod
    public void setUp() {
        household = new Household(1L);
        household.setBuildingNumber("num");
        household.setState("state");
        household.setZipCode("code");

        tenant1 = new User(1L);
        tenant2 = new User(2L);

        Integer i = 1;
        for (User user : Arrays.asList(tenant1, tenant2)) {
            String name = "user" + i;
            user.setEmail(name + "@user.com");
            user.setPassword(name);
            user.setFirstName(name);
            user.setLastName(name.toUpperCase());
        }
        household.addTenant(tenant1);
    }

    @Test
    public void testFindHouseholdById() {
        when(householdDao.findById(1L)).thenReturn(household);
        Household household = householdService.findHouseholdById(1L);
        Assert.assertTrue(household.getId().equals(1L));
        Assert.assertEquals(household.getBuildingNumber(), "num");
        Assert.assertEquals(household.getState(), "state");
        Assert.assertEquals(household.getZipCode(), "code");
        Assert.assertEquals(household.getTenants(), Arrays.asList(tenant1));
    }

    @Test
    public void testFindAll() {
        when(householdDao.findAll()).thenReturn(Collections.singletonList(household));
        List<Household> h = householdService.findAll();
        Assert.assertEquals(1, h.size());
        Assert.assertTrue(h.get(0).getId().equals(1L));
    }

    @Test
    public void testRemoveTenant() {
        householdService.removeTenant(tenant1, household);
        Assert.assertEquals(household.getTenants().size(), 0);
    }

    @Test
    public void testAddTenant() {
        householdService.addTenant(tenant2, household);
        Assert.assertEquals(household.getTenants().size(), 2);
        Assert.assertTrue(household.getTenants().contains(tenant1));
        Assert.assertTrue(household.getTenants().contains(tenant2));
    }
}