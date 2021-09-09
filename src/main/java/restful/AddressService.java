package restful;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mongodb.MongoBean;
import com.mongodb.MongoBeanRepository;

import restful.InputBeanGeneral2.ArrayOfItems;

@Service
public class AddressService {

    @Autowired
    private MongoBeanRepository beanRepository;

    public synchronized Boolean save(RequestBean input) {
        assert(input != null);
        delete(input.getFirstname(), input.getSurname());
        MongoBean mb  = new MongoBean();
        mb.setAddress(input.getAddress());
        mb.setFirstname(input.getFirstname());
        mb.setHomeTel(input.getHomeTel());
        mb.setMobile(input.getMobile());
        mb.setSurname(input.getSurname());
        mb.setTitle(input.getTitle());
        mb.setWorkTel(input.getWorkTel());
        mb.setPersonalEmail(input.getPersonalEmail());
        mb.setWorkEmail(input.getWorkEmail());
        beanRepository.save(mb);
        return true;
    }

    public synchronized Boolean delete(String firstName, String lastName) {
        MongoBean mb = beanRepository.findByFirstnameSurname(firstName, lastName);
        if (mb == null) return false;
        beanRepository.deleteById(mb.getId());
        return true;
    }

    public ResponseBean get(String firstName, String lastName) {
        MongoBean mb  = beanRepository.findByFirstnameSurname(firstName,lastName);
        if (mb == null) return null;
        ResponseBean rb = new ResponseBean();
        rb.setAddress(mb.getAddress());
        rb.setFirstname(mb.getFirstname());
        rb.setHomeTel(mb.getHomeTel());
        rb.setMobile(mb.getMobile());
        rb.setSurname(mb.getSurname());
        rb.setTitle(mb.getTitle());
        rb.setWorkTel(mb.getWorkTel());
        rb.setPersonalEmail(mb.getPersonalEmail());
        rb.setWorkEmail(mb.getWorkEmail());
        return rb;
    }

    public List<ResponseBean> getAll(String lastName) {
        List<MongoBean> mbs = beanRepository.findBySurnameOrderByFirstname(lastName);
        if (mbs == null) return null;
        List<ResponseBean> rbs = new ArrayList<ResponseBean>();
        for (MongoBean mb: mbs) {
            ResponseBean rb = new ResponseBean();
            rb.setAddress(mb.getAddress());
            rb.setFirstname(mb.getFirstname());
            rb.setHomeTel(mb.getHomeTel());
            rb.setMobile(mb.getMobile());
            rb.setSurname(mb.getSurname());
            rb.setTitle(mb.getTitle());
            rb.setWorkTel(mb.getWorkTel());
            rb.setPersonalEmail(mb.getPersonalEmail());
            rb.setWorkEmail(mb.getWorkEmail());
            rbs.add(rb);
        }
        return rbs;
    }
    public List<ResponseBean> getAllFirstname(String firstname) {
        List<MongoBean> mbs = beanRepository.findByFirstnameOrderBySurname(firstname);
        if (mbs == null) return null;
        List<ResponseBean> rbs = new ArrayList<ResponseBean>();
        for (MongoBean mb: mbs) {
            ResponseBean rb = new ResponseBean();
            rb.setAddress(mb.getAddress());
            rb.setFirstname(mb.getFirstname());
            rb.setHomeTel(mb.getHomeTel());
            rb.setMobile(mb.getMobile());
            rb.setSurname(mb.getSurname());
            rb.setTitle(mb.getTitle());
            rb.setWorkTel(mb.getWorkTel());
            rb.setPersonalEmail(mb.getPersonalEmail());
            rb.setWorkEmail(mb.getWorkEmail());
            rbs.add(rb);
        }
        return rbs;
    }
    public synchronized void getAllCSV() throws IOException {
        List<MongoBean> mbs = beanRepository.findAll();
        Collections.sort(mbs, new Comparator<MongoBean>() {

            @Override
            public int compare(MongoBean mb1, MongoBean mb2) {
                if (mb1 == null || mb2 == null) return 0;
                String dmb1 = mb1.getSurname() != null ? mb1.getSurname() : "";
                String dmb2 = mb2.getSurname() != null ? mb2.getSurname() : "";
                int comp = dmb1.compareToIgnoreCase(dmb2);
                dmb1 = mb1.getFirstname() != null ? mb1.getFirstname() : "";
                dmb2 = mb2.getFirstname() != null ? mb2.getFirstname() : "";
                if (comp == 0) comp = dmb1.compareToIgnoreCase(dmb2);
                return comp;
            }
        });
//        ProcessBuilder builder = new ProcessBuilder();
//        builder.command("sh", "-c", "rm *.csv");
//        Process process = builder.start();

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("addressbook.csv"));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Title","Firstname","Surname","Address","HomeTel","WorkTel","Mobile",
                                "PersonalEmail","WorkEmail"));
                ) {
            for(MongoBean mb: mbs) {
                csvPrinter.printRecord(mb.getTitle(),
                        mb.getFirstname(),
                        mb.getSurname(),
                        mb.getAddress(),
                        mb.getHomeTel(),
                        mb.getWorkTel(),
                        mb.getMobile(),
                        mb.getPersonalEmail(),
                        mb.getWorkEmail());

            }

            csvPrinter.flush();            
        }    
    }
    public synchronized void loadFromCSV() throws IOException {
        beanRepository.deleteAll();
        try (
                Reader reader = Files.newBufferedReader(Paths.get("addressbook.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("Title","Firstname","Surname","Address","HomeTel","WorkTel","Mobile",
                                "PersonalEmail","WorkEmail")
                        .withIgnoreHeaderCase()
                        .withTrim());
                ) {
            int line = 0;
            for (CSVRecord csvRecord : csvParser) {
                if (line++ == 0) continue;
                MongoBean mb = new MongoBean();
                // Accessing values by the names assigned to each column
                mb.setTitle(csvRecord.get("Title"));
                mb.setFirstname(csvRecord.get("Firstname"));
                mb.setSurname(csvRecord.get("Surname"));
                mb.setAddress(csvRecord.get("Address"));
                mb.setHomeTel(csvRecord.get("HomeTel"));
                mb.setWorkTel(csvRecord.get("WorkTel"));
                mb.setMobile(csvRecord.get("Mobile"));
                mb.setPersonalEmail(csvRecord.get("PersonalEmail"));
                mb.setWorkEmail(csvRecord.get("WorkEmail"));
                beanRepository.save(mb);

            }
        }
    }

    public List<ResponseBean> getAll() {
        List<MongoBean> mbs = beanRepository.findAll();

        Collections.sort(mbs, new Comparator<MongoBean>() {

            @Override
            public int compare(MongoBean mb1, MongoBean mb2) {
                if (mb1 == null || mb2 == null) return 0;
                String dmb1 = mb1.getSurname() != null ? mb1.getSurname() : "";
                String dmb2 = mb2.getSurname() != null ? mb2.getSurname() : "";
                int comp = dmb1.compareToIgnoreCase(dmb2);
                dmb1 = mb1.getFirstname() != null ? mb1.getFirstname() : "";
                dmb2 = mb2.getFirstname() != null ? mb2.getFirstname() : "";
                if (comp == 0) comp = dmb1.compareToIgnoreCase(dmb2);
                return comp;
            }
        });

        List<ResponseBean> rbs = new ArrayList<ResponseBean>();
        if (mbs == null || mbs.size() == 0) return rbs;
        for (MongoBean mb: mbs) {
            ResponseBean rb = new ResponseBean();
            rb.setAddress(mb.getAddress());
            rb.setFirstname(mb.getFirstname());
            rb.setHomeTel(mb.getHomeTel());
            rb.setMobile(mb.getMobile());
            rb.setSurname(mb.getSurname());
            rb.setTitle(mb.getTitle());
            rb.setWorkTel(mb.getWorkTel());
            rb.setPersonalEmail(mb.getPersonalEmail());
            rb.setWorkEmail(mb.getWorkEmail());
            rbs.add(rb);
        }
        return rbs;
    }

    public String print(RequestBean[] input) throws IOException {
        assert(input != null);
        InputBeanGeneral2 ib = new InputBeanGeneral2();
        ib.setHeaderHTML("<html><body>");
        ib.setFooterHTML("</body></html>");
        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("/usr/local/tomcat/addresses.ftl")));
        while((line = fileReader.readLine())!=null){
            sb.append(line+"\n");
        }
        fileReader.close();

        ib.setBodyFTL(sb.toString());
 
        ib.setWho(System.getenv("HOSTNAME")+":"+System.getenv("LOGNAME"));
        List<RequestBean> filteredInput = new ArrayList<RequestBean>();
        for(RequestBean item: input) {
            if(item.getAddress() != null && !item.getAddress().isEmpty()) {
                filteredInput.add(item);
            }
        }

        ArrayOfItems[] ai = new ArrayOfItems[(filteredInput.size() / 15) + (filteredInput.size() % 15 != 0 ? 1 :0)];
        sb = new StringBuilder();
        for (int i = 0; i < filteredInput.size(); i++) {
            String[] rows = filteredInput.get(i).getAddress().split(",");
            RequestBean item = filteredInput.get(i);
            switch(i%15) {
            case 0:
                sb.append("addr1row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr1row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr1row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr1row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr1row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr1row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr1row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 1:
                sb.append("addr2row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr2row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr2row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr2row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr2row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr2row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr2row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 2:
                sb.append("addr3row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr3row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr3row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr3row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr3row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr3row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr3row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 3:
                sb.append("addr4row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr4row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr4row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr4row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr4row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr4row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr4row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;
            case 4:
                sb.append("addr5row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr5row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr5row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr5row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr5row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr5row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr5row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 5:
                sb.append("addr6row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr6row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr6row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr6row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr6row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr6row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr6row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 6:
                sb.append("addr7row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr7row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr7row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr7row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr7row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr7row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr7row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 7:
                sb.append("addr8row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr8row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr8row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr8row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr8row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr8row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr8row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 8:
                sb.append("addr9row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr9row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr9row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr9row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr9row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr9row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr9row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 9:
                sb.append("addr10row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr10row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr10row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr10row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr10row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr10row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr10row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 10:
                sb.append("addr11row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr11row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr11row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr11row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr11row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr11row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr11row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 11:
                sb.append("addr12row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr12row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr12row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr12row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr12row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr12row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr12row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 12:
                sb.append("addr13row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr13row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr13row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr13row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr13row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr13row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr13row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 13:
                sb.append("addr14row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr14row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr14row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr14row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr14row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr14row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr14row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            case 14:
                sb.append("addr15row1,"+item.getTitle()+" "+item.getFirstname()+" "+item.getSurname()+"\n");
                sb.append("addr15row2,"+(rows.length > 0? rows[0].trim():"")+"\n");
                sb.append("addr15row3,"+(rows.length > 1? rows[1].trim():"")+"\n");
                sb.append("addr15row4,"+(rows.length > 2? rows[2].trim():"")+"\n");
                sb.append("addr15row5,"+(rows.length > 3? rows[3].trim():"")+"\n");
                sb.append("addr15row6,"+(rows.length > 4? rows[4].trim():"")+"\n");
                sb.append("addr15row7,"+(rows.length > 5? rows[5].trim():"")+"\n");
                break;

            }
            if (i % 15 == 14) {
                ArrayOfItems item2 = new ArrayOfItems();
                item2.setInputCSV(sb.toString());
                item2.setFindingsText(null);
                ai[i/15] = item2;
                sb = new StringBuilder();
            } else if (i == (filteredInput.size() -1)) {
                ArrayOfItems item2 = new ArrayOfItems();
                item2.setInputCSV(sb.toString());
                item2.setFindingsText(null); 
                ai[i/15] = item2;
                sb = new StringBuilder();
            }
        }
        ib.setArrayOfItems(ai);
  
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", "rm *.pdf");
        Process process = builder.start();

        final String uri = "http://container1:8080/freemarker/generalArrayToPDF";
        RestTemplate restTemplate = new RestTemplate(); 
        ReturnBean result = restTemplate.postForObject( uri, ib, ReturnBean.class);
        Base64.Decoder b64d = Base64.getDecoder();
        try (FileOutputStream fos = new FileOutputStream("result.pdf")) {
            fos.write(b64d.decode(result.getFileB64()));
         }
 
        return "result.pdf";
    }
}
