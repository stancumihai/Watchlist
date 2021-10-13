package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Address;
import com.proiect.watchlist.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Works
     */
    @GetMapping("/addresses")
    public List<Address> listAllAddresses() {
        return addressService.listAllAddresses();
    }

    /**
     * Works
     */
    @GetMapping("/addresses/{id}")
    public Optional<Address> getAddressById(@PathVariable("id") Integer id) {
        return addressService.getAddressById(id);
    }

    /**
     * Works
     */
    @DeleteMapping("/addresses/{id}")
    public int deleteAddress(@PathVariable("id") int id) {
        return addressService.deleteAddress(id);
    }

    /**
     * Works
     */
    @PostMapping("/addresses")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    /**
     * Works
     */
    @PutMapping("/addresses/{id}")
    public Address updateAddress(@PathVariable("id") int id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }
}
