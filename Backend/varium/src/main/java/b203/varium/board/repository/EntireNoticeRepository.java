// Spring Data JPA 사용
package b203.varium.board.repository;

import b203.varium.board.entity.EntireNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntireNoticeRepository extends JpaRepository<EntireNotice, Integer> {
    List<EntireNotice> findByEntireNoticeTitleContaining(String title);

    List<EntireNotice> findByEntireNoticeContentContaining(String content);
}
