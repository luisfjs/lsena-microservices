package br.com.lsena.core.repository;

import br.com.lsena.core.model.ApplicationUser;
import br.com.lsena.core.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
