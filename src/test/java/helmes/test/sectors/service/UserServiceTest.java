package helmes.test.sectors.service;

import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.User;
import helmes.test.sectors.model.UserData;
import helmes.test.sectors.model.UserSector;
import helmes.test.sectors.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void saveUser() {
        UserData userToSave = createUserData();
        UserData savedUser = userService.saveUserData(userToSave);
        assertEquals(userToSave.getName(), savedUser.getName());
        assertEquals(userToSave.getSectors(), savedUser.getSectors());
    }

    public UserData createUserData() {
        List<Sector> sectors = new ArrayList<>();
        sectors.add(new Sector(1, "Manufacturing", 1, ""));
        return new UserData(null, "test", sectors);
    }

    @Test
    public void updateUser() {
        UserData userToSave = createUserData();
        UserData savedUser = userService.saveUserData(userToSave);
        List<Sector> sectors = userToSave.getSectors();
        sectors.add(new Sector(2, "Construction Materials", 19, "Manufacturing"));
        UserData userToUpdate = new UserData(savedUser.getId(), "testtest", sectors);
        UserData updatedUser = userService.saveUserData(userToUpdate);
        assertEquals(savedUser.getId(), updatedUser.getId());
        assertNotEquals(savedUser.getSectors().size(), updatedUser.getSectors().size());
        assertNotEquals(savedUser.getName(), updatedUser.getName());
    }

    @Test
    public void testUserSavingWithValidData() {
        ArrayList<UserSector> userSectors = new ArrayList<>();
        UserSector userSector = new UserSector();
        userSector.setSector(new Sector(1, "Manufacturing", 1, ""));
        userSectors.add(userSector);
        User user = new User();
        user.setName("test");
        user.setSectors(userSectors);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }
    @Test
    public void testUserSavingWithInvalidName() {
        ArrayList<UserSector> userSectors = new ArrayList<>();
        UserSector userSector = new UserSector();
        userSector.setSector(new Sector(1, "Manufacturing", 1, ""));
        userSectors.add(userSector);
        User user = new User();
        user.setName("");
        user.setSectors(userSectors);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

}
