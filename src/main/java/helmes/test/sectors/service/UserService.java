package helmes.test.sectors.service;

import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.User;
import helmes.test.sectors.model.UserData;
import helmes.test.sectors.model.UserSector;
import helmes.test.sectors.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserData saveUserData(UserData userData) {
        if (userData.getId() == null) {
            return addUser(userData);
        } else {
            return updateUser(userData);
        }
    }

    public UserData addUser(UserData userData) {
        User user = modifyUser(new User(), userData);
        return returnUser(user);
    }

    public UserData updateUser(UserData userData) {
        User existingUser = userRepository.findById(userData.getId()).orElse(null);
        if(existingUser != null) {
            return returnUser(modifyUser(existingUser, userData));
        }
        return null;
    }

    public User modifyUser(User user, UserData userData) {
        user.setName(userData.getName());
        List<UserSector> userSectors = new ArrayList<>();

        for (Sector sector: userData.getSectors()) {
            UserSector userSector = new UserSector();
            userSector.setSector(sector);
            userSectors.add(userSector);
        }
        user.setSectors(userSectors);
        userRepository.save(user);
        return user;
    }

    public UserData returnUser(User user) {
        List<Sector> userSectors = user.getSectors().stream().map(UserSector::getSector).collect(Collectors.toList());
        return new UserData(user.getId(), user.getName(), userSectors);
    }
}
