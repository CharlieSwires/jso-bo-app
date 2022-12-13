package restful;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@RestController
@RequestMapping(path = "/AddressEntry")
public class RController  {

    Logger log = LoggerFactory.getLogger(RController.class);
    @Autowired
    private AddressService service;
    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping(path="/add", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> post( @RequestBody RequestBean input) {
        
        return new ResponseEntity<Boolean>(service.save(input), HttpStatus.OK);
    }    
    @PostMapping(path="/print", produces="application/octet-stream", consumes="application/json")
    public synchronized ResponseEntity<Resource> post( @RequestBody RequestBean[] input,HttpServletRequest request) throws IOException, Exception {        
        String result = service.print(input);
        log.info("result="+result);
        log.info("request="+request.toString());
        return downloadFile(result, request);
    }
    @DeleteMapping(path="/delete/{firstName}/{lastName}", produces="application/json")
    public ResponseEntity<Boolean> delete(@PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<Boolean>(service.delete(firstName,lastName), HttpStatus.OK);
    }
    @GetMapping(path="/get/{firstName}/{lastName}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> get(@PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<List<ResponseBean>>(service.get(firstName,lastName), HttpStatus.OK);
    }
    @GetMapping(path="/get/{lastName}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getList(@PathVariable("lastName") String lastName) {
        
        return new ResponseEntity<List<ResponseBean>>(service.getAll(lastName), HttpStatus.OK);
    }
    @GetMapping(path="/getFirst/{firstname}", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getListByFirstname(@PathVariable("firstname") String firstname) {
        
        return new ResponseEntity<List<ResponseBean>>(service.getAllFirstname(firstname), HttpStatus.OK);
    }
    @GetMapping(path="/getAllCSV", produces="application/json")
    public synchronized ResponseEntity<Resource> getAllCSV(HttpServletRequest request) throws Exception {
        service.getAllCSV();
        return downloadFile("addressbook.csv", request);
    }
    @GetMapping(path="/getAll", produces="application/json")
    public ResponseEntity<List<ResponseBean>> getAll() throws Exception {
        return new ResponseEntity<List<ResponseBean>>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping(path="/getAllArray/{page}", produces="application/json")
    public ResponseBean2[] getAllArray(@PathVariable("page") Integer page) throws Exception {
        return service.getAllArray(page);
    }
    @GetMapping(path="/updatePrintable/{firstName}/{lastName}", produces="application/json")
    public Boolean getPrintable(@PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) throws Exception {
        return service.getPrintable(firstName, lastName);
    }
    @GetMapping(path="/loadAll", produces="application/json")
    public synchronized ResponseEntity<Boolean> loadAll() throws Exception {
        service.loadFromCSV();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
 
    private ResponseEntity<Resource> downloadFile(String filename, HttpServletRequest request) throws Exception {
 
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(filename);
 
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}