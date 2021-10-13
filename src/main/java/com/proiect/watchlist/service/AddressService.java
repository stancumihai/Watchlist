package com.proiect.watchlist.service;


import com.proiect.watchlist.dao.address.AddressDao;
import com.proiect.watchlist.model.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressDao addressDao;

    public AddressService(@Qualifier("AddressRepo") AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Transactional
    public List<Address> listAllAddresses() {
        return addressDao.listAllAddresses();
    }

    @Transactional
    public Optional<Address> getAddressById(Integer id) {
        return addressDao.getAddressById(id);
    }

    @Transactional
    public int deleteAddress(int id) {
        return addressDao.deleteAddress(id);
    }

    @Transactional
    public Address createAddress(Address address) {
        return addressDao.createAddress(address);
    }

    @Transactional
    public Address updateAddress(int id, Address address) {
        return addressDao.updateAddress(id, address);
    }
}
