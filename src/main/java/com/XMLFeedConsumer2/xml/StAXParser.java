package com.XMLFeedConsumer2.xml;

import com.XMLFeedConsumer2.model.Description;
import com.XMLFeedConsumer2.model.Product;
import com.XMLFeedConsumer2.service.DescriptionServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StAXParser {
    private static final Logger log = LoggerFactory.getLogger(StAXParser.class);
    private final ProductServiceImpl productService;
    private final DescriptionServiceImpl descriptionService;

    public StAXParser(ProductServiceImpl productService, DescriptionServiceImpl descriptionService) {
        this.productService = productService;
        this.descriptionService = descriptionService;
    }

    @Value("${path.xml}")
    private String path;

    @Bean
    public void parse() {
        try {
            XMLStreamReader xmlStreamReader = getXmlStreamReader();
            Map<String, List<String>> map = updateMap();
            int iter = 0;
            int param_iter = 0;
            Description description = new Description();
            Product product = new Product();
            while (xmlStreamReader.hasNext()) {
                //Get integer value of current event
                xmlStreamReader.next();
                if(xmlStreamReader.isStartElement()){
                    if(isElement(xmlStreamReader, "Product")){
                        iter++;
                    } else if (isElement(xmlStreamReader, "ID")) {
                        idElementProcessing(xmlStreamReader, product);
                    } else if (isElement(xmlStreamReader, "Name")) {
                        nameElementProcessing(xmlStreamReader, map.get("product_names"));
                    } else if (isElement(xmlStreamReader, "PartNumber")) {
                        partNumberElementProcessing(xmlStreamReader, product);
                    } else if (isElement(xmlStreamReader, "Country")) {
                        countryElementProcessing(xmlStreamReader, map.get("countries"));
                    } else if (isElement(xmlStreamReader, "Param")) {
                        param_iter++;
                    }
                }else if (xmlStreamReader.hasText() && xmlStreamReader.getText().trim().length() > 0){
                    param_iter = getParam_iter(xmlStreamReader, map.get("param"), param_iter);
                }
                else if(xmlStreamReader.isEndElement()){
                    if(isElement(xmlStreamReader, "Product")){
                        iter++;
                    }
                }
                //Saving product with description into database
                //In XML we have pair tags, if we hit the second tag "Product" =>
                // => iter == 2, than we save an object and create new dataset
                //for the next product
                if((iter%2)==0 && iter!=0){
                    saveProductToDb(map.get("product_names"), map.get("info"), map.get("countries"), map.get("param"), description, product);
                    log.info("Product was added " + product.getProduct_id());
                    map = updateMap();
                    product = new Product();
                    description = new Description();
                    iter = 0;
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException("Runtime Exception " + e);
        }
    }

    private XMLStreamReader getXmlStreamReader() throws FileNotFoundException, XMLStreamException {
        Reader fileReader = new FileReader(path);
        XMLInputFactory xmlInputFactory =
                XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader =
                xmlInputFactory.createXMLStreamReader(fileReader);
        return xmlStreamReader;
    }

    private Map<String, List<String>> updateMap() {
        Map<String, List<String>> map;
        map = new HashMap<>();
        map.put("product_names", new ArrayList<>());
        map.put("info", new ArrayList<>());
        map.put("countries", new ArrayList<>());
        map.put("param", new ArrayList<>());
        return map;
    }

    private void saveProductToDb(List<String> product_names, List<String> info, List<String> countries, List<String> param, Description description, Product product) {
        description.setOther_info(info.toString());
        description.setParam(param.toString());
        product.setNames(product_names.toString());
        product.setCountries(countries.toString());
        productService.save(product);
        descriptionService.save(description);
    }

    private int getParam_iter(XMLStreamReader xmlStreamReader, List<String> param, int param_iter) {
        if(param_iter % 2 != 0){
            param.add(xmlStreamReader.getText());
            param_iter = 0;
        }
        return param_iter;
    }

    private void idElementProcessing(XMLStreamReader xmlStreamReader, Product product) throws XMLStreamException {
        xmlStreamReader.next();
        product.setProduct_id(Integer.parseInt(xmlStreamReader.getText()));
    }

    private void nameElementProcessing(XMLStreamReader xmlStreamReader, List<String> product_names) throws XMLStreamException {
        xmlStreamReader.next();
        if (xmlStreamReader.hasText() && xmlStreamReader.getText().trim().length() > 0){
            product_names.add(xmlStreamReader.getText());
        }
    }

    private void partNumberElementProcessing(XMLStreamReader xmlStreamReader, Product product) throws XMLStreamException {
        xmlStreamReader.next();
        product.setPart_number(xmlStreamReader.getText());
    }

    private void countryElementProcessing(XMLStreamReader xmlStreamReader, List<String> countries) throws XMLStreamException {
        xmlStreamReader.next();
        if (xmlStreamReader.hasText() && xmlStreamReader.getText().trim().length() > 0){
            countries.add(xmlStreamReader.getText());
        }
    }

    private boolean isElement(XMLStreamReader xmlStreamReader, String Product) {
        return xmlStreamReader.getLocalName().equals(Product);
    }
}
