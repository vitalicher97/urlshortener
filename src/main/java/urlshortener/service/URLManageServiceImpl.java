package urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlshortener.model.URLsModel;
import urlshortener.repository.URLsRepository;

import java.util.List;

@Service
public class URLManageServiceImpl implements URLManageService {

    @Autowired
    URLsRepository urlsRepository;

    public String findLongUrl(String shortUrl){

        List<URLsModel> foundUrlList = urlsRepository.findByShortURL(shortUrl);

        if(!foundUrlList.isEmpty()){
            for(URLsModel foundUrl : foundUrlList){
                if(foundUrl.getShortURL().equals(shortUrl)){
                    return foundUrl.getLongURL();
                }
            }
        } else {
            return "null";
        }


        return "null";
    }

    public String findShortUrl(String longUrl){

        List<URLsModel> foundUrlList = urlsRepository.findByLongURL(longUrl);

        if(!foundUrlList.isEmpty()){
            for(URLsModel foundUrl : foundUrlList){
                if(foundUrl.getLongURL().equals(longUrl)){
                    return foundUrl.getShortURL();
                }
            }
        } else {
            return "null";
        }

        return "null";
    }

    public List findAllUrls(){

        List<URLsModel> foundUrlList = urlsRepository.findAll();

        return  foundUrlList;

    }

    public boolean deleteById(int id){

        try{
            urlsRepository.deleteById(id);
        } catch (Exception e){
            return false;
        }

        return true;

    }

    public boolean deleteAll(){

        urlsRepository.deleteAll();

        return true;

    }

}
