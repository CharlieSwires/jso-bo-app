package restful;

public class ResponseBean {
    public ResponseBean() {
        
    }
    private String title;
    private String firstname;
    private String surname;
	private String extension;
    private String aofs;
    private String station;
	private String relevantDay;
    private String relevantTime;
    private String callDay;
    private String callTime;
	private String releaseExtension;
    private String releaseStation;
    private String releaseCallDay;
    private String releaseCallTime;
	private String held;
	private String arrestComments;
    private String team;
    private String region;
    private String suspectedCourt;
    private String courtOutcome;
    private String courtComments;
    private String timeInCustody;
    public String getTitle() {
 		return title;
 	}
 	public void setTitle(String title) {
 		this.title = title;
 	}
 	public String getFirstname() {
 		return firstname;
 	}
 	public void setFirstname(String firstname) {
 		this.firstname = firstname;
 	}
 	public String getSurname() {
 		return surname;
 	}
 	public void setSurname(String surname) {
 		this.surname = surname;
 	}
 	public String getExtension() {
 		return extension;
 	}
 	public void setExtension(String extension) {
 		this.extension = extension;
 	}
 	public String getAofs() {
 		return aofs;
 	}
 	public void setAofs(String aofs) {
 		this.aofs = aofs;
 	}
 	public String getStation() {
 		return station;
 	}
 	public void setStation(String station) {
 		this.station = station;
 	}
 	public String getRelevantDay() {
 		return relevantDay;
 	}
 	public void setRelevantDay(String relevantDay) {
 		this.relevantDay = relevantDay;
 	}
 	public String getRelevantTime() {
 		return relevantTime;
 	}
 	public void setRelevantTime(String relevantTime) {
 		this.relevantTime = relevantTime;
 	}
 	public String getCallDay() {
 		return callDay;
 	}
 	public void setCallDay(String callDay) {
 		this.callDay = callDay;
 	}
 	public String getCallTime() {
 		return callTime;
 	}
 	public void setCallTime(String callTime) {
 		this.callTime = callTime;
 	}
 	public String getReleaseExtension() {
 		return releaseExtension;
 	}
 	public void setReleaseExtension(String releaseExtension) {
 		this.releaseExtension = releaseExtension;
 	}
 	public String getReleaseStation() {
 		return releaseStation;
 	}
 	public void setReleaseStation(String releaseStation) {
 		this.releaseStation = releaseStation;
 	}
 	public String getReleaseCallDay() {
 		return releaseCallDay;
 	}
 	public void setReleaseCallDay(String releaseCallDay) {
 		this.releaseCallDay = releaseCallDay;
 	}
 	public String getReleaseCallTime() {
 		return releaseCallTime;
 	}
 	public void setReleaseCallTime(String releaseCallTime) {
 		this.releaseCallTime = releaseCallTime;
 	}
 	public String getHeld() {
 		return held;
 	}
 	public void setHeld(String held) {
 		this.held = held;
 	}
 	public String getTeam() {
 		return team;
 	}
 	public void setTeam(String team) {
 		this.team = team;
 	}
 	public String getRegion() {
 		return region;
 	}
 	public void setRegion(String region) {
 		this.region = region;
 	}
 	public String getSuspectedCourt() {
 		return suspectedCourt;
 	}
 	public void setSuspectedCourt(String suspectedCourt) {
 		this.suspectedCourt = suspectedCourt;
 	}
 	public String getCourtOutcome() {
 		return courtOutcome;
 	}
 	public void setCourtOutcome(String courtOutcome) {
 		this.courtOutcome = courtOutcome;
 	}
 	public String getCourtComments() {
 		return courtComments;
 	}
 	public void setCourtComments(String courtComments) {
 		this.courtComments = courtComments;
 	}
 	public String getTimeInCustody() {
 		return timeInCustody;
 	}
 	public void setTimeInCustody(String timeInCustody) {
 		this.timeInCustody = timeInCustody;
 	}
	@Override
	public String toString() {
		return "ResponseBean [title=" + title + ", firstname=" + firstname + ", surname=" + surname + ", extension="
				+ extension + ", aofs=" + aofs + ", station=" + station + ", relevantDay=" + relevantDay
				+ ", relevantTime=" + relevantTime + ", callDay=" + callDay + ", callTime=" + callTime
				+ ", releaseExtension=" + releaseExtension + ", releaseStation=" + releaseStation + ", releaseCallDay="
				+ releaseCallDay + ", releaseCallTime=" + releaseCallTime + ", held=" + held + ", arrestComments="
				+ arrestComments + ", team=" + team + ", region=" + region + ", suspectedCourt=" + suspectedCourt
				+ ", courtOutcome=" + courtOutcome + ", courtComments=" + courtComments + ", timeInCustody="
				+ timeInCustody + "]";
	}
	public String getArrestComments() {
		return arrestComments;
	}
	public void setArrestComments(String arrestComments) {
		this.arrestComments = arrestComments;
	}
 	
}
