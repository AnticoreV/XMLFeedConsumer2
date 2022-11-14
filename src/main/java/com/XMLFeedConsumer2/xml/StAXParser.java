package com.XMLFeedConsumer2.xml;

import com.XMLFeedConsumer2.model.Product;
import com.XMLFeedConsumer2.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@Service
public class StAXParser {
    private static final Logger log = LoggerFactory.getLogger(StAXParser.class);
    private final ProductServiceImpl productService;
    public StAXParser(ProductServiceImpl productService) {
        this.productService = productService;
    }

    private String path = "/Users/ivansapronov/Desktop/XMLFeedConsumer2/src/main/resources/test.xml";

    @Bean
    public void parse() {
        try {
            Reader fileReader = new FileReader(path);
            XMLInputFactory xmlInputFactory =
                    XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader =
                    xmlInputFactory.createXMLStreamReader(fileReader);
            while (xmlStreamReader.hasNext()) {
                //Get integer value of current event
                xmlStreamReader.next();
                Product product = new Product();
                if(xmlStreamReader.isStartElement()){
                    System.out.println(xmlStreamReader.getLocalName());
                    product.setName(xmlStreamReader.getLocalName());
                    productService.save(product);
                    if(xmlStreamReader.getLocalName().equals("Product")){
                        System.out.println("EQUALS");
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException("Runtime Exception " + e);
        }
    }
}
