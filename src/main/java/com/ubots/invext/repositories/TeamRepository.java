package com.ubots.invext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubots.invext.entities.Team;
import java.util.List;
import com.ubots.invext.enums.TimeSetorEnum;



public interface TeamRepository extends JpaRepository<Team, Long>{

	public List<Team> findBySetor(TimeSetorEnum setor);
}
