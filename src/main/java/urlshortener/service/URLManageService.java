package urlshortener.service;

import java.util.List;

public interface URLManageService {

    String findLongUrl(String shortUrl);

    String findShortUrl(String longUrl);

    List findAllUrls();

    boolean deleteById(int id);

    boolean deleteAll();

}
