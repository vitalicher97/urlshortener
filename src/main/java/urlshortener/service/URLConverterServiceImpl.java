package urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlshortener.model.URLsModel;
import urlshortener.repository.URLsRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class URLConverterServiceImpl implements URLConverterService {

    @Autowired
    private URLsRepository urlsRepository;

    @Autowired
    private IDConverterService idConverterService;

    @Autowired
    private URLManageService urlManageService;

    public String shortUrlGen(HttpServletRequest request){

        String queryString = "";

        String serverPortName = request.getServerName() + ":" + request.getServerPort() + "/";

        if(request.getQueryString() != null){
            queryString = queryString + "?" + request.getQueryString();
        }

        String longUrl = (request.getRequestURI() + queryString).split("/makeshort/")[1];

        // To check if given longUrl has already been converted
        String shortUrl = urlManageService.findShortUrl(longUrl);
        if(!shortUrl.equals("null")){
            return serverPortName + shortUrl;
        }

        // To get last id number stored in database
        int requestId;
        List<URLsModel> lastTableRow = urlsRepository.findFirstByOrderByRequestIDDesc();
        if(!lastTableRow.isEmpty()){
            requestId = lastTableRow.get(0).getRequestID() + 1;
        } else {
            requestId = 1;
        }

        shortUrl = idConverterService.convertRequestNum(requestId);
        URLsModel data = new URLsModel(requestId, longUrl, shortUrl);
        urlsRepository.save(data);

        return serverPortName + shortUrl;

    }

}
