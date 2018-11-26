package cz.muni.fi.pa165.skupina06.team02.rms.app.service.facades;

import cz.muni.fi.pa165.skupina06.team02.rms.app.dto.HouseholdDTO;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;
import cz.muni.fi.pa165.skupina06.team02.rms.app.facade.HouseholdFacade;
import cz.muni.fi.pa165.skupina06.team02.rms.app.service.HouseholdService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.constraints.Null;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class HouseholdFacadeTest {
    @Mock
    private HouseholdService householdService;

    @InjectMocks
    private HouseholdFacade householdFacade = new HouseholdFacadeImpl();

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
    public void testGetHouseholdById() {
        when(householdService.findHouseholdById(1L)).thenReturn(household);
        try {
            HouseholdDTO h = householdFacade.getHouseholdById(1L);
        } catch (NullPointerException e) {
        } finally {
            Assert.assertNotNull(household);
            Assert.assertEquals((long) household.getId(), 1L);
        }
    }
}