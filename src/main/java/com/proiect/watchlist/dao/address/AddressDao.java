package com.proiect.watchlist.dao.address;

import com.proiect.watchlist.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

    List<Address> listAllAddresses();

    Optional<Address> getAddressById(Integer id);

    int deleteAddress(int id);

    Address createAddress(Address address);

    Address updateAddress(int id, Address address);

}
