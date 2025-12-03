package org.example.carlabfulls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://labtest15.netlify.app")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
