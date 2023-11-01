package speedhome.interview.boot.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import speedhome.interview.boot.Main.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member  findByEmail(String email);
    Member findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
