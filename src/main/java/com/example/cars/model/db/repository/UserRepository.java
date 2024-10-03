package com.example.cars.model.db.repository;


import com.example.cars.model.CarStatus;
import com.example.cars.model.UserStatus;
import com.example.cars.model.db.entity.Car;
import com.example.cars.model.db.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("select c from User c where c.status <> :status")
    Page<User> findAllByStatusNot(Pageable request, UserStatus status);

    @Query("select c from User c where c.status <> :status and c.lastName like %:filter% or c.firstName like %:filter% or c.middleName like %:filter%")
    Page<User> findAllByStatusNotFiltered(Pageable request, UserStatus status,@Param("filter") String filter);
}
