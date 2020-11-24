package com.wavestoked.domain.team;

import com.wavestoked.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Member, Long> {
}
