package restful;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Conversions {

	public static LocalDate yyMMddToLocalDate(String date) {
        // Define a DateTimeFormatter with the custom pattern "yymmdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        // Parse the date string and convert it to a LocalDate
        return LocalDate.parse(date, formatter);
	}
	public static String LocalDateToyyMMdd(LocalDate localDate) {
        // Define a DateTimeFormatter with the custom pattern "yymmdd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");

        // Format the LocalDate as a string in "yymmdd" format
        return localDate.format(formatter);
	}
	public static LocalTime HDotmmToLocalTime(String tod) {
        // Define a DateTimeFormatter with the custom pattern "HH.ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H.mm");

        // Parse the time string and convert it to a LocalTime
        return LocalTime.parse(tod, formatter);
	}
	public static String LocalTimeToHDotmm(LocalTime localTime) {
        // Define a DateTimeFormatter with the custom pattern "HH.ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H.mm");

        // Format the LocalTime as a string in the "HH.SS" format
        return localTime.format(formatter);
	}
	public static LocalDateTime combineDateTime(LocalDate localDate, LocalTime localTime) {
		return LocalDateTime.of(localDate, localTime);
	}
	public static long[] timeDifference(LocalDateTime releaseTime, LocalDateTime relevantTime) {
        // Get the current date and time as a LocalDateTime
        LocalDateTime now = releaseTime != null ? releaseTime : LocalDateTime.now();

        // Create a LocalDateTime to subtract from the current time
        LocalDateTime pastDateTime = relevantTime; // Example: November 4, 2023, 2:30 PM

        // Calculate the duration between now and the futureDateTime
        Duration duration = Duration.between(pastDateTime, now);

        // Get the hours, minutes, and seconds components
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.toSeconds() % 60;

		long result[] = {hours, minutes, seconds};
		return result;
	}
	public static LocalDate localDateFromIso(String dateString) {
        // Define a DateTimeFormatter with the standard ISO-8601 date format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        // Parse the date string and convert it to a LocalDate
        return LocalDate.parse(dateString, formatter);
	}
	public static LocalTime localTimeFromIso(String timeString) {
        // Define a DateTimeFormatter with the standard ISO-8601 time format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        // Parse the time string and convert it to a LocalTime
        return LocalTime.parse(timeString, formatter);
	}
	   public static String arrayToDotSeparatedString(long[] array) {
	        StringBuilder result = new StringBuilder();

	        for (int i = 0; i < array.length; i++) {
	            if (i > 0) {
	                result.append("."); // Add a comma separator if not the first element
	            }
	            result.append(array[i]); // Append the decimal value
	        }

	        return result.toString();
	    }
	
}
