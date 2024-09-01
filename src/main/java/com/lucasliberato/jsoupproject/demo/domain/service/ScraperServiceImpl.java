package com.lucasliberato.jsoupproject.demo.domain.service;

import com.lucasliberato.jsoupproject.demo.domain.dtos.RespondentDTO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScraperServiceImpl implements ScraperService {

    @Value("#{'${website.urls}'.split(',')}")
    List<String> urls;

    @Override
    public Set<RespondentDTO> getVehicleByModel(String vehicleModel) {
       Set<RespondentDTO> respondentDTOS = new HashSet<>();

       for (String url: urls) {
           if (url.contains("ikman")) {
               extractDataFromIkman(respondentDTOS, url + vehicleModel);
           } else if (url.contains("riyasewana")) {
               extractDataFromRiyasewana(respondentDTOS, url + vehicleModel);
           }
       }
       return respondentDTOS;
    }

    private void extractDataFromRiyasewana(Set<RespondentDTO> respondentDTOS, String url) {
        try {
            Document document = Jsoup.connect(url).get();

            Element element = document.getElementById("content");
            Elements elements = element.getElementsByTag("a");

            for (Element ads : elements) {
                RespondentDTO respondentDTO = new RespondentDTO();

                if (!StringUtils.isEmpty(ads.attr(("title")))) {
                    respondentDTO.setTitle(ads.attr("title"));
                    respondentDTO.setUrl(ads.attr("href"));
                }
                if (respondentDTO.getUrl() != null) {
                    respondentDTOS.add(respondentDTO);
                }
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
    private void extractDataFromIkman(Set<RespondentDTO> respondentDTOS, String url) {

        try {
            Document document = Jsoup.connect(url).get();

            Element element = document.getElementsByClass("list--3NxGO").first();
            Elements elements = element.getElementsByTag("a");

            for (Element ads : elements) {
                RespondentDTO respondentDTO = new RespondentDTO();

                if (!StringUtils.isEmpty(ads.attr(("href")))) {
                    respondentDTO.setTitle(ads.attr("title"));
                    respondentDTO.setUrl("https://ikman.lk"+ ads.attr("href"));
                }
                if (respondentDTO.getUrl() != null) {
                    respondentDTOS.add(respondentDTO);
                }
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

}
