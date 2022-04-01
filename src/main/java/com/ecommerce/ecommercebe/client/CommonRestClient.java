package com.ecommerce.ecommercebe.client;

import com.ecommerce.ecommercebe.exception.CommonException;
import com.ecommerce.ecommercebe.exception.Non200Exception;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CommonRestClient {

    private RestTemplate restTemplate;

    public CommonRestClient(){
        restTemplate = new RestTemplate();
    }

    private URI getURI(String url, HashMap<String, Object>params, HashMap<String,Object>pathVariables) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if(params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        if(pathVariables != null){
            return builder.buildAndExpand(pathVariables).encode().toUri();
        } else {
            return builder.build().encode().toUri();
        }
    }
    protected Object getData(
            String url,
            HttpMethod method,
            Object payload,
            MediaType mediaType,
            HashMap<String,Object> params,
            HashMap<String, Object> pathVariables,
            HashMap<String, String> headers,
            Class responseType
    ) {
        try {
            HttpEntity entity = getHttpEntity(payload, mediaType, headers);
            log.info("Request: {}", entity);
            ResponseEntity responseEntity = restTemplate.exchange(getURI(url, params, pathVariables), method, entity, responseType);
            log.info("Response: {}", responseEntity);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new Non200Exception(responseEntity.getStatusCodeValue());
            }
        }catch (HttpClientErrorException exception){
            throw new Non200Exception(exception.getStatusCode().value(), exception.getMessage());
        } catch (Exception ex){
            throw new CommonException(ex.getMessage());
        }
    }

    private HttpEntity getHttpEntity(Object payload, MediaType mediaType, HashMap<String, String> headers){
        if(headers == null && payload == null){
            return null;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        if(headers != null){
            for(Map.Entry<String, String>entry: headers.entrySet()){
                httpHeaders.set(entry.getKey(), entry.getValue());
            }
        }
        httpHeaders.setContentType(mediaType);
        Object payloadString = serializePayload(payload, mediaType);
        return new HttpEntity(payloadString , httpHeaders);
    }

    private Object serializePayload(Object obj, MediaType mediaType){
        if(obj == null){
            return null;
        }
        if(mediaType == MediaType.APPLICATION_XML){
            return toXML(obj);
        } else if(mediaType == MediaType.APPLICATION_JSON){
            return toJson(obj);
        }
        return obj;
    }

    private String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private String toXML(Object obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(obj, sw);
            String xmlContent = sw.toString();
            sw.close();
            return xmlContent;
        }catch (Exception ex){
            throw new Non200Exception(200);
        }
    }
}