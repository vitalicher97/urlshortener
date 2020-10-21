package urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urlshortener.model.URLsModel;

import java.util.List;

public interface URLsRepository extends JpaRepository<URLsModel, Integer> {

    List<URLsModel> findFirstByOrderByRequestIDDesc();
    List<URLsModel> findByShortURL(String shortUrl);
    List<URLsModel> findByLongURL(String longUrl);

}
