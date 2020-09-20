package helmes.test.sectors.controller;

import helmes.test.sectors.model.Sector;
import helmes.test.sectors.model.SectorData;
import helmes.test.sectors.model.User;
import helmes.test.sectors.model.UserData;
import helmes.test.sectors.service.SectorService;
import helmes.test.sectors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private UserService userService;

    @GetMapping("/sectors")
    public List<SectorData> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @PostMapping("/user")
    public UserData addUser(@RequestBody UserData userData) {
        return userService.saveUserData(userData);
    }
}
