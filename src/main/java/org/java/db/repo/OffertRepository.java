package org.java.db.repo;

import org.java.db.pojo.Offert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertRepository extends JpaRepository<Offert, Integer> {

}
