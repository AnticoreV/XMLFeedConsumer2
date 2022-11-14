package com.XMLFeedConsumer2.xml;

import com.XMLFeedConsumer2.model.Description;
import com.XMLFeedConsumer2.model.Product;
import com.XMLFeedConsumer2.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StAXParser {
    private static final Logger log = LoggerFactory.getLogger(StAXParser.class);
    private final ProductServiceImpl productService;
    public StAXParser(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Value("${path.xml}")
    private String path;

    @Bean
    public void parse() {
        try {
            Reader fileReader = new FileReader(path);
            XMLInputFactory xmlInputFactory =
                    XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader =
                    xmlInputFactory.createXMLStreamReader(fileReader);


            List<String> product_names = new ArrayList<>();
            int iter = 0;
            Description description = new Description();
            Product product = new Product();

            while (xmlStreamReader.hasNext()) {
                //Get integer value of current event
                xmlStreamReader.next();

                if(xmlStreamReader.isStartElement()){
                    if(xmlStreamReader.getLocalName().equals("Product")){
                        iter++;
                    } else if (xmlStreamReader.getLocalName().equals("ID")) {
                        xmlStreamReader.next();
                        product.setProduct_id(Integer.parseInt(xmlStreamReader.getText()));
                    } else if (xmlStreamReader.getLocalName().equals("Name")) {
                        xmlStreamReader.next();
                        if (xmlStreamReader.hasText() && xmlStreamReader.getText().trim().length() > 0){
                            product_names.add(xmlStreamReader.getText());
                        }
                    }
                }
                else if (xmlStreamReader.hasText() && xmlStreamReader.getText().trim().length() > 0) {
                }
                else if(xmlStreamReader.isEndElement()){
                    if(xmlStreamReader.getLocalName().equals("Product")){
                        iter++;
                    }
                }

                if((iter%2)==0 && iter!=0){
                    product.setNames(product_names.toString());
                    productService.save(product);
                    product = new Product();
                    iter = 0;
                }
            }
            System.out.println(product_names.toString());

        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException("Runtime Exception " + e);
        }
    }
}
