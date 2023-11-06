package restful;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoBean;
import com.mongodb.MongoBeanRepository;

@Service
public class JSOService {
	final static String publicKey;
	final static String privateKey;
	static {
		publicKey=System.getenv("PUBLIC_KEY");
		privateKey=System.getenv("PRIVATE_KEY");
	}
    Logger log = LoggerFactory.getLogger(JSOService.class);

	@Autowired
	private MongoBeanRepository beanRepository;

	public synchronized Boolean save(RequestBean input) {
		assert(input != null);
		delete(input.getFirstname(), input.getSurname());
		MongoBean mb  = new MongoBean();
		mb.setTitle(input.getTitle());
		mb.setFirstname(input.getFirstname());
		mb.setSurname(input.getSurname());
		mb.setExtension(input.getExtension());
		mb.setAofs(input.getAofs());
		mb.setStation(input.getStation());
		mb.setRelevantDay(input.getRelevantDay() != null ? Conversions.yyMMddToLocalDate(input.getRelevantDay()).toString():null);
		mb.setRelevantTime(input.getRelevantTime() != null ? Conversions.HDotmmToLocalTime(input.getRelevantTime()).toString():null);
		mb.setCallDay(input.getCallDay() != null ? Conversions.yyMMddToLocalDate(input.getCallDay()).toString():null);
		mb.setCallTime(input.getCallTime() != null ? Conversions.HDotmmToLocalTime(input.getCallTime()).toString():null);
		mb.setReleaseExtension(input.getReleaseExtension());
		mb.setReleaseStation(input.getReleaseStation());
		mb.setReleaseCallDay(input.getReleaseCallDay() != null ? Conversions.yyMMddToLocalDate(input.getReleaseCallDay()).toString():null);
		mb.setReleaseCallTime(input.getReleaseCallTime() != null ? Conversions.HDotmmToLocalTime(input.getReleaseCallTime()).toString():null);
		mb.setHeld(input.getHeld());
		mb.setArrestComments(input.getArrestComments());
		mb.setTeam(input.getTeam());
		mb.setRegion(input.getRegion());
		mb.setSuspectedCourt(input.getSuspectedCourt());
		mb.setCourtOutcome(input.getCourtOutcome());
		mb.setCourtComments(input.getCourtComments());
		beanRepository.save(mb);
		return true;
	}

	public synchronized Boolean delete(String firstName, String lastName) {
		List<MongoBean> mbs = beanRepository.findByFirstnameSurname(firstName,lastName);
		if (mbs == null) return false;
		for (MongoBean mb: mbs) {
			beanRepository.deleteById(mb.getId());
		}
		return true;
	}

	public List<ResponseBean> get(String firstName, String lastName) {
		log.info("get "+"firstname="+firstName+" lastname="+lastName+" publicKey="+publicKey);
		List<MongoBean> mbs = beanRepository.findByFirstnameSurname(firstName,lastName);
		if (mbs == null) return null;
		for (MongoBean mb: mbs) {
			log.info("result="+mb.toString());
		}
		List<MongoBean> sortedList = mbs.stream()
				.sorted(Comparator.comparing(MongoBean::getSurname))
				.collect(Collectors.toList());
		List<ResponseBean> rbs = new ArrayList<ResponseBean>();
		for (MongoBean mb: sortedList) {
			ResponseBean rb = new ResponseBean();
			rb.setTitle(mb.getTitle());
			rb.setFirstname(mb.getFirstname());
			rb.setSurname(mb.getSurname());
			rb.setExtension(mb.getExtension());
			rb.setAofs(mb.getAofs());
			rb.setStation(mb.getStation());
			rb.setRelevantDay(mb.getRelevantDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getRelevantDay())):null);
			rb.setRelevantTime(mb.getRelevantTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getRelevantTime())):null);
			rb.setCallDay(mb.getCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getCallDay())):null);
			rb.setCallTime(mb.getCallTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getCallTime())):null);
			rb.setReleaseExtension(mb.getReleaseExtension());
			rb.setReleaseStation(mb.getReleaseStation());
			rb.setReleaseCallDay(mb.getReleaseCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getReleaseCallDay())):null);
			rb.setReleaseCallTime(mb.getReleaseCallTime() != null ?  Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getReleaseCallTime())):null);
			rb.setHeld(mb.getHeld());
			rb.setArrestComments(mb.getArrestComments());
			rb.setTeam(mb.getTeam());
			rb.setRegion(mb.getRegion());
			rb.setSuspectedCourt(mb.getSuspectedCourt());
			rb.setCourtOutcome(mb.getCourtOutcome());
			rb.setCourtComments(mb.getCourtComments());
			if (mb.getRelevantDay() != null && mb.getRelevantTime() != null) {
				rb.setTimeInCustody(Conversions.arrayToDotSeparatedString(Conversions.timeDifference(
						(mb.getReleaseCallTime() != null && mb.getReleaseCallDay() != null) ?
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getReleaseCallDay()), 
										Conversions.localTimeFromIso(mb.getReleaseCallTime())):null, 
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getRelevantDay()), 
										Conversions.localTimeFromIso(mb.getRelevantTime())))
						)
						);

			} else {
				rb.setTimeInCustody(null);
			}
			rbs.add(rb);
		}
		return rbs;
	}

	public List<ResponseBean> getAll(String lastName) {
		List<MongoBean> mbs = beanRepository.findBySurname(lastName);
		if (mbs == null) return null;
		// Sort the List by "surname" using the Stream API
		List<MongoBean> sortedList = mbs.stream()
				.sorted(Comparator.comparing(MongoBean::getSurname))
				.collect(Collectors.toList());

		List<ResponseBean> rbs = new ArrayList<ResponseBean>();
		for (MongoBean mb: sortedList) {
			ResponseBean rb = new ResponseBean();
			rb.setTitle(mb.getTitle());
			rb.setFirstname(mb.getFirstname());
			rb.setSurname(mb.getSurname());
			rb.setExtension(mb.getExtension());
			rb.setAofs(mb.getAofs());
			rb.setStation(mb.getStation());
			rb.setRelevantDay(mb.getRelevantDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getRelevantDay())):null);
			rb.setRelevantTime(mb.getRelevantTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getRelevantTime())):null);
			rb.setCallDay(mb.getCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getCallDay())):null);
			rb.setCallTime(mb.getCallTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getCallTime())):null);
			rb.setReleaseExtension(mb.getReleaseExtension());
			rb.setReleaseStation(mb.getReleaseStation());
			rb.setReleaseCallDay(mb.getReleaseCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getReleaseCallDay())):null);
			rb.setReleaseCallTime(mb.getReleaseCallTime() != null ?  Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getReleaseCallTime())):null);
			rb.setHeld(mb.getHeld());
			rb.setArrestComments(mb.getArrestComments());
			rb.setTeam(mb.getTeam());
			rb.setRegion(mb.getRegion());
			rb.setSuspectedCourt(mb.getSuspectedCourt());
			rb.setCourtOutcome(mb.getCourtOutcome());
			rb.setCourtComments(mb.getCourtComments());
			if (mb.getRelevantDay() != null && mb.getRelevantTime() != null) {
				rb.setTimeInCustody(Conversions.arrayToDotSeparatedString(Conversions.timeDifference(
						(mb.getReleaseCallTime() != null && mb.getReleaseCallDay() != null) ?
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getReleaseCallDay()), 
										Conversions.localTimeFromIso(mb.getReleaseCallTime())):null, 
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getRelevantDay()), 
										Conversions.localTimeFromIso(mb.getRelevantTime())))
						)
						);

			} else {
				rb.setTimeInCustody(null);
			}
			rbs.add(rb);
		}
		return rbs;
	}
	public List<ResponseBean> getAllFirstname(String firstname) {
		List<MongoBean> mbs = beanRepository.findByFirstname(firstname);
		if (mbs == null) return null;
		// Sort the List by "surname" using the Stream API
		List<MongoBean> sortedList = mbs.stream()
				.sorted(Comparator.comparing(MongoBean::getSurname))
				.collect(Collectors.toList());
		List<ResponseBean> rbs = new ArrayList<ResponseBean>();
		for (MongoBean mb: sortedList) {
			ResponseBean rb = new ResponseBean();
			rb.setTitle(mb.getTitle());
			rb.setFirstname(mb.getFirstname());
			rb.setSurname(mb.getSurname());
			rb.setExtension(mb.getExtension());
			rb.setAofs(mb.getAofs());
			rb.setStation(mb.getStation());
			rb.setRelevantDay(mb.getRelevantDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getRelevantDay())):null);
			rb.setRelevantTime(mb.getRelevantTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getRelevantTime())):null);
			rb.setCallDay(mb.getCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getCallDay())):null);
			rb.setCallTime(mb.getCallTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getCallTime())):null);
			rb.setReleaseExtension(mb.getReleaseExtension());
			rb.setReleaseStation(mb.getReleaseStation());
			rb.setReleaseCallDay(mb.getReleaseCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getReleaseCallDay())):null);
			rb.setReleaseCallTime(mb.getReleaseCallTime() != null ?  Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getReleaseCallTime())):null);
			rb.setHeld(mb.getHeld());
			rb.setArrestComments(mb.getArrestComments());
			rb.setTeam(mb.getTeam());
			rb.setRegion(mb.getRegion());
			rb.setSuspectedCourt(mb.getSuspectedCourt());
			rb.setCourtOutcome(mb.getCourtOutcome());
			rb.setCourtComments(mb.getCourtComments());
			if (mb.getRelevantDay() != null && mb.getRelevantTime() != null) {
				rb.setTimeInCustody(Conversions.arrayToDotSeparatedString(Conversions.timeDifference(
						(mb.getReleaseCallTime() != null && mb.getReleaseCallDay() != null) ?
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getReleaseCallDay()), 
										Conversions.localTimeFromIso(mb.getReleaseCallTime())):null, 
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getRelevantDay()), 
										Conversions.localTimeFromIso(mb.getRelevantTime())))
						)
						);

			} else {
				rb.setTimeInCustody(null);
			}
			rbs.add(rb);
		}
		return rbs;
	}
	public synchronized void getAllCSV() throws IOException {
		List<MongoBean> mbs = beanRepository.findAll();
		// Sort the List by "surname" using the Stream API
		List<MongoBean> sortedList = mbs.stream()
				.sorted(Comparator.comparing(MongoBean::getSurname))
				.collect(Collectors.toList());
		//        ProcessBuilder builder = new ProcessBuilder();
		//        builder.command("sh", "-c", "rm *.csv");
		//        Process process = builder.start();

		try (
				BufferedWriter writer = Files.newBufferedWriter(Paths.get("addressbook.csv"));

				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withHeader(    
								"title",
								"firstname",
								"surname",
								"extension",
								"aofs",
								"station",
								"relevantDay",
								"relevantTime",
								"callDay",
								"callTime",
								"releaseExtension",
								"releaseStation",
								"releaseCallDay",
								"releaseCallTime",
								"held",
								"arrestComments",
								"team",
								"region",
								"suspectedCourt",
								"courtOutcome",
								"courtComments"
								));
				) {
			for(MongoBean mb: sortedList) {
				csvPrinter.printRecord(    
						mb.getTitle(),
						mb.getFirstname(),
						mb.getSurname(),
						mb.getExtension(),
						mb.getAofs(),
						mb.getStation(),
						mb.getRelevantDay(),
						mb.getRelevantTime(),
						mb.getCallDay(),
						mb.getCallTime(),
						mb.getReleaseExtension(),
						mb.getReleaseStation(),
						mb.getReleaseCallDay(),
						mb.getReleaseCallTime(),
						mb.getHeld(),
						mb.getArrestComments(),
						mb.getTeam(),
						mb.getRegion(),
						mb.getSuspectedCourt(),
						mb.getCourtOutcome(),
						mb.getCourtComments()
						);

			}

			csvPrinter.flush();            
		}    
	}
	public synchronized void loadFromCSV() throws IOException {
		beanRepository.deleteAll();
		try (
				Reader reader = Files.newBufferedReader(Paths.get("addressbook.csv"));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withHeader(
								"title",
								"firstname",
								"surname",
								"extension",
								"aofs",
								"station",
								"relevantDay",
								"relevantTime",
								"callDay",
								"callTime",
								"releaseExtension",
								"releaseStation",
								"releaseCallDay",
								"releaseCallTime",
								"held",
								"arrestComments",
								"team",
								"region",
								"suspectedCourt",
								"courtOutcome",
								"courtComments")
						.withIgnoreHeaderCase()
						.withTrim());
				) {
			int line = 0;
			for (CSVRecord csvRecord : csvParser) {
				if (line++ == 0) continue;
				MongoBean mb = new MongoBean();
				// Accessing values by the names assigned to each column
				mb.setTitle(csvRecord.get("title"));
				mb.setFirstname(csvRecord.get("firstname"));
				mb.setSurname(csvRecord.get("surname"));
				mb.setExtension(csvRecord.get("extension"));
				mb.setAofs(csvRecord.get("aofs"));
				mb.setStation(csvRecord.get("station"));
				mb.setRelevantDay(csvRecord.get("relevantDay"));
				mb.setRelevantTime(csvRecord.get("relevantTime"));
				mb.setCallDay(csvRecord.get("callDay"));
				mb.setCallTime(csvRecord.get("callTime"));
				mb.setReleaseExtension(csvRecord.get("releaseExtension"));
				mb.setReleaseStation(csvRecord.get("releaseStation"));
				mb.setReleaseCallDay(csvRecord.get("releaseCallDay"));
				mb.setReleaseCallTime(csvRecord.get("releaseCallTime"));
				mb.setHeld(csvRecord.get("held"));
				mb.setArrestComments(csvRecord.get("arrestComments"));
				mb.setTeam(csvRecord.get("team"));
				mb.setRegion(csvRecord.get("region"));
				mb.setSuspectedCourt(csvRecord.get("suspectedCourt"));
				mb.setCourtOutcome(csvRecord.get("courtOutcome"));
				mb.setCourtComments(csvRecord.get("courtComments"));

				beanRepository.save(mb);

			}
		}
	}


	public List<ResponseBean> getAll() {
		List<MongoBean> mbs = beanRepository.findAll();

		// Sort the List by "surname" using the Stream API
		List<MongoBean> sortedList = mbs.stream()
				.sorted(Comparator.comparing(MongoBean::getSurname))
				.collect(Collectors.toList());

		List<ResponseBean> rbs = new ArrayList<ResponseBean>();
		if (mbs == null || mbs.size() == 0) return rbs;
		for (MongoBean mb: sortedList) {
			ResponseBean rb = new ResponseBean();
			rb.setTitle(mb.getTitle());
			rb.setFirstname(mb.getFirstname());
			rb.setSurname(mb.getSurname());
			rb.setExtension(mb.getExtension());
			rb.setAofs(mb.getAofs());
			rb.setStation(mb.getStation());
			rb.setRelevantDay(mb.getRelevantDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getRelevantDay())):null);
			rb.setRelevantTime(mb.getRelevantTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getRelevantTime())):null);
			rb.setCallDay(mb.getCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getCallDay())):null);
			rb.setCallTime(mb.getCallTime() != null ? Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getCallTime())):null);
			rb.setReleaseExtension(mb.getReleaseExtension());
			rb.setReleaseStation(mb.getReleaseStation());
			rb.setReleaseCallDay(mb.getReleaseCallDay() != null ? Conversions.LocalDateToyyMMdd(Conversions.localDateFromIso(mb.getReleaseCallDay())):null);
			rb.setReleaseCallTime(mb.getReleaseCallTime() != null ?  Conversions.LocalTimeToHDotmm(Conversions.localTimeFromIso(mb.getReleaseCallTime())):null);
			rb.setHeld(mb.getHeld());
			rb.setArrestComments(mb.getArrestComments());
			rb.setTeam(mb.getTeam());
			rb.setRegion(mb.getRegion());
			rb.setSuspectedCourt(mb.getSuspectedCourt());
			rb.setCourtOutcome(mb.getCourtOutcome());
			rb.setCourtComments(mb.getCourtComments());
			if (mb.getRelevantDay() != null && mb.getRelevantTime() != null) {
				rb.setTimeInCustody(Conversions.arrayToDotSeparatedString(Conversions.timeDifference(
						(mb.getReleaseCallTime() != null && mb.getReleaseCallDay() != null) ?
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getReleaseCallDay()), 
										Conversions.localTimeFromIso(mb.getReleaseCallTime())):null, 
								Conversions.combineDateTime(
										Conversions.localDateFromIso(mb.getRelevantDay()), 
										Conversions.localTimeFromIso(mb.getRelevantTime())))
						)
						);

			} else {
				rb.setTimeInCustody(null);
			}			rbs.add(rb);
		}
		return rbs;
	}


}
