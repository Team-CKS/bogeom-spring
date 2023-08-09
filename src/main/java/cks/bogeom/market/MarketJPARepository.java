package cks.bogeom.market;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketJPARepository extends JpaRepository<Market, Integer> {
//    Optional<User> findByEmail(String email);

}
