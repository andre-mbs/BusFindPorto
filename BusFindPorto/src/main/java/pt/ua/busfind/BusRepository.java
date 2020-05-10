package pt.ua.busfind;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author esP13
 */
@Repository
public interface BusRepository extends CrudRepository<Bus, Integer>{
}
