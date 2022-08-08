package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.Role;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.repository.ChargePointRepository;
import com.extrawest.simplechargingstationtertyshniy.repository.LocationRepository;
import com.extrawest.simplechargingstationtertyshniy.repository.UserRepository;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class InjectDataController {
    private final UserService userService;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChargePointRepository chargePointRepository;

    @PostConstruct
    private void injectData() {
//        injectUsers();
//        injectLocations();
//        injectChargePoints();
    }

    private void injectUsers() {
        User volodya = new User("Volodya", "volodya337@gmail.com", "volodya337", Role.BUYER);
        User egor = new User("Egor", "egor11@gmail.com", "egor11", Role.SELLER);
        User vasiliy = new User("Vasiliy", "vasiliy99@gmail.com", "vasiliy99", Role.MANAGER);
        User gustav = new User("Gustav", "gustavErnandes@gmail.com", "gustav", Role.ADMIN);
        User oksana = new User("Oksana", "oksana45@gmail.com", "oksana45", Role.SELLER);
        User user = new User("user", "user@gmail.com", "user", Role.BUYER);
        List<User> users = List.of(volodya, egor, vasiliy, gustav, oksana, user);
        for (User usr : users) {
            usr.setPassword(passwordEncoder.encode(usr.getPassword()));
            userService.create(usr);
        }
    }

    private void injectLocations() {
        Location shevchenko = new Location("Shevchenka 67");
        Location pozniaky = new Location("Pozniaky 92");
        Location darnitsa = new Location("Darnitsa 32");
        Location shulavka = new Location("Shulavka 9");
        List<Location> locations = List.of(shevchenko, pozniaky, darnitsa, shulavka);
        locationRepository.saveAll(locations);
    }

    private void injectChargePoints() {
        ChargePoint cp1 = new ChargePoint("Schneider",
                locationRepository.getById(7L), userRepository.getById(2L));
        ChargePoint cp2 = new ChargePoint("Schneider",
                locationRepository.getById(8L), userRepository.getById(2L));
        ChargePoint cp3 = new ChargePoint("Wellbox",
                locationRepository.getById(9L), userRepository.getById(5L));
        ChargePoint cp4 = new ChargePoint("Wellbox",
                locationRepository.getById(10L), userRepository.getById(5L));
        List<ChargePoint> chargePoints = List.of(cp1, cp2, cp3, cp4);
        chargePointRepository.saveAll(chargePoints);
    }
}
