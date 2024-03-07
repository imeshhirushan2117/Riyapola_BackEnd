package lk.riyapola.riyapola.repo;

import lk.riyapola.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Imesh Hirushan
 * Project Name : riyapola
 * Package Name : lk.riyapola.riyapola.repo
 * Date : Mar 7, 2024
 * Time : 11:51 PM
 */
public interface AdminRepo extends JpaRepository <Admin , Long> {
}
