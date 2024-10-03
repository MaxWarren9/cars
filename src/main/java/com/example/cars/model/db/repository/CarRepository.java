package com.example.cars.model.db.repository;

import com.example.cars.model.db.entity.Car;
import com.example.cars.model.CarStatus;
import com.example.cars.model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByModelNameAndStatus(String modelName, CarStatus status);
    Car findByModelNameAndStatusAndColor(String modelName,CarStatus status, Color color);
    List<Car> findAllByModelName(String modelName);
    @Query(nativeQuery = true, value = "select * from cars where id > 2 limit 1")
    Car getCar();
    @Query(nativeQuery = true,value = "select * from cars where is_new = :isNew limit 1")
    Car getSomeCar(@Param("isNew") boolean isNew);

    @Query("select c from Car c where c.status <>'DELETED' ")
    List<Car> findAllNotDeletedCars();
    @Query("select c from Car c where c.status <> :status")
    List<Car> findAllNotInStatus(@Param("status") CarStatus status);
    @Query("select c from Car c where c.status <> :status")
    Page<Car> findAllByStatusNot( Pageable request, CarStatus status);
    @Query("select c from Car c where c.status <> :status and c.modelName like %:filter%")
    Page<Car> findAllByStatusNotFiltered(Pageable request,CarStatus status,@Param("filter") String filter);


    @Query("select c from Car c where c.user.id <>:id and c.status <>:status")
    Page<Car> findAllCarsByStatusNot(Long id, Pageable request, CarStatus status);
    @Query("select c from Car c where c.user.id <>:id and c.status <>:status")
    Page<Car> findAllCarsByStatusNotFiltered(Long id, Pageable request, CarStatus status,@Param("status") String filter);
}
