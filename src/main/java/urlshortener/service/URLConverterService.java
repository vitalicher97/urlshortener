package urlshortener.service;

import javax.servlet.http.HttpServletRequest;

public interface URLConverterService {

    String shortUrlGen(HttpServletRequest request);

}
