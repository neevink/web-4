package com.neevin.lab4.repositories;

import com.neevin.lab4.models.Point;
import com.neevin.lab4.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    interface NoUserInfo {
        double getX();
        double getY();
        double getR();
        boolean isHit();
    }

    List<NoUserInfo> findByOwner(User owner);
}
