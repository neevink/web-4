package com.neevin.lab4.repositories;

import com.neevin.lab4.models.Point;
import com.neevin.lab4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findByUserName(String userName);
    void deleteByUserName(String userName);
}
