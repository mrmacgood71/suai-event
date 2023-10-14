package it.macgood.suaieventserver.user.repository;

import com.fasterxml.jackson.databind.node.LongNode;
import it.macgood.suaieventserver.user.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

}
