package urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import urlshortener.model.URLsModel;
import urlshortener.service.URLConverterService;
import urlshortener.service.URLManageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "URL not found")
class ResourceNotFoundException extends RuntimeException{

}

@Controller
public class MainController {

    @Autowired
    private URLConverterService urlConverterService;

    @Autowired
    private URLManageService urlManageService;

    @GetMapping({"/", "/index"})
    public ModelAndView homePage(ModelAndView modelAndView){

        modelAndView.setViewName("index");

        return modelAndView;

    }

    @GetMapping("/makeshort/**")
    public ModelAndView makeShort(HttpServletRequest request, ModelAndView modelAndView){

        String shortUrl = urlConverterService.shortUrlGen(request);

        modelAndView.addObject("shortUrl", shortUrl);
        modelAndView.setViewName("index");

        return modelAndView;

    }

    @GetMapping("{shortUrl}")
    public ModelAndView redirect(@PathVariable("shortUrl") String shortUrl, ModelAndView modelAndView){

        String longUrl = urlManageService.findLongUrl(shortUrl);

        String scheme = "";

        if(!longUrl.equals("null")){
            if(!longUrl.contains("http")){
                scheme = "http://";
            }
            modelAndView.setViewName("redirect:" + scheme + longUrl);
            return modelAndView;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    @GetMapping("/showall")
    public ModelAndView showAll(ModelAndView modelAndView){

        List<URLsModel> allUrlsList = urlManageService.findAllUrls();

        modelAndView.addObject("allUrlsList", allUrlsList);
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteById(@PathVariable int id, ModelAndView modelAndView, RedirectAttributes redirect){

        boolean message = urlManageService.deleteById(id);

        redirect.addFlashAttribute("message", message);
        modelAndView.setViewName("redirect:/index");
        return modelAndView;

    }

    @GetMapping("/deleteall")
    public ModelAndView deleteAll(ModelAndView modelAndView, RedirectAttributes redirect){

        boolean message = urlManageService.deleteAll();

        redirect.addFlashAttribute("message", message);
        modelAndView.setViewName("redirect:/index");
        return modelAndView;

    }




}
