package com.egcodes.storedetectiveservice.persitence.repository;

import com.egcodes.storedetectiveservice.persitence.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

}
