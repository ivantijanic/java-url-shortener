package com.v1.urlshortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerRepository urlShortenerRepository;

    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    public ResponseEntity<Void> getShortenUrl(@RequestBody UrlShortenerForm url) throws MalformedURLException {
        String randomString = url.buildUrlShortenerModel().getRandomString();
        while(urlShortenerRepository.findByRandomString(randomString) != null){
            randomString = url.buildUrlShortenerModel().getRandomString();
        }
        return new ResponseEntity(urlShortenerRepository.save(url.buildUrlShortenerModel()), HttpStatus.OK);
    }

//if not using default port, please enter custom one to provide valid access
    @RequestMapping(value="/{randomstring}", method= RequestMethod.GET)
    public void redirectToOrigin(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        try {
            response.setHeader("Location", urlShortenerRepository.findByRandomString(randomString).getOriginalLink());
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        response.setHeader("Connection", "close");
    }
}
