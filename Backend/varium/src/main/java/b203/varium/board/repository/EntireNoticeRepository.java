// Spring Data JPA 사용
package b203.varium.board.repository;

import b203.varium.board.entity.EntireNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntireNoticeRepository extends JpaRepository<EntireNotice, Integer> {
    //    List<EntireNotice> findByEntireNoticeTitleContaining(String title);
    boolean existsById(int id);

    EntireNotice findById(int id);
//    List<EntireNotice> findByEntireNoticeContentContaining(String content);
}
