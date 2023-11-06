import React from 'react';  
import './Popup.css';  

class Popup extends React.Component {  
    constructor(props2) {
        super(props2);
        this.state =
        {
          address: [{title: null,
    firstname: null,
    surname: null,
    extension: null,
    aofs: null,
    station: null,
	relevantDay: null,
    relevantTime: null,
    callDay: null,
    callTime: null,
	releaseExtension: null,
    releaseStation: null,
    releaseCallDay: null,
    releaseCallTime: null,
	held: null,
	arrestComments: null,
    team: null,
    region: null,
    suspectedCourt: null,
    courtOutcome: null,
    courtComments: null,
    timeInCustody: null
}],
        };
        this.search2();
        this.search2 = this.search2.bind(this);
        this.save = this.save.bind(this);
        this.myChangeHandlerTitle = this.myChangeHandlerTitle.bind(this);
        this.myChangeHandlerFirstname = this.myChangeHandlerFirstname.bind(this);
        this.myChangeHandlerSurname = this.myChangeHandlerSurname.bind(this);
        this.myChangeHandlerExtension = this.myChangeHandlerExtension.bind(this);
        this.myChangeHandlerAofs = this.myChangeHandlerAofs.bind(this);
        this.myChangeHandlerStation = this.myChangeHandlerStation.bind(this);
        this.myChangeHandlerRelevantDay = this.myChangeHandlerRelevantDay.bind(this);
        this.myChangeHandlerRelevantTime = this.myChangeHandlerRelevantTime.bind(this);
        this.myChangeHandlerCallDay = this.myChangeHandlerCallDay.bind(this);
        this.myChangeHandlerCallTime = this.myChangeHandlerCallTime.bind(this);
        this.myChangeHandlerReleaseExtension = this.myChangeHandlerReleaseExtension.bind(this);
        this.myChangeHandlerReleaseStation = this.myChangeHandlerReleaseStation.bind(this);
        this.myChangeHandlerReleaseCallDay = this.myChangeHandlerReleaseCallDay.bind(this);
        this.myChangeHandlerReleaseCallTime = this.myChangeHandlerReleaseCallTime.bind(this);
        this.myChangeHandlerHeld = this.myChangeHandlerHeld.bind(this);
        this.myChangeHandlerArrestComments = this.myChangeHandlerArrestComments.bind(this);
        this.myChangeHandlerTeam = this.myChangeHandlerTeam.bind(this);
        this.myChangeHandlerRegion = this.myChangeHandlerRegion.bind(this);
        this.myChangeHandlerSuspectedCourt = this.myChangeHandlerSuspectedCourt.bind(this);
        this.myChangeHandlerCourtOutcome = this.myChangeHandlerCourtOutcome.bind(this);
        this.myChangeHandlerCourtComments = this.myChangeHandlerCourtComments.bind(this);
        this.myChangeHandlerTimeInCustody = this.myChangeHandlerTimeInCustody.bind(this);
        }
    search2() {

        console.log("search2 " + this.props.firstname + ', ' + this.props.surname);
        if (this.props.firstname != null && this.props.surname != null){
  
          const testURL = 'http://localhost:8881/jso-bo/JSOEntry/get' + '/' + this.props.firstname + '/' + this.props.surname;
        
        console.log("testURL", testURL);
    
        const myInit = {
          method: 'GET',
          // mode: 'no-cors',
          headers: {
            'content-type': 'application/json'
          }
        };
    
        const myRequest = new Request(testURL, myInit);
        fetch(myRequest)
          .then(async response => {
            const data = await response.json();
            this.setState({ address: data });
          })
          .catch(error => {
            console.log(error)
          });
      } else {
          this.setState({
            address: [{title: null,
    firstname: null,
    surname: null,
    extension: null,
    aofs: null,
    station: null,
	relevantDay: null,
    relevantTime: null,
    callDay: null,
    callTime: null,
	releaseExtension: null,
    releaseStation: null,
    releaseCallDay: null,
    releaseCallTime: null,
	held: null,
	arrestComments: null,
    team: null,
    region: null,
    suspectedCourt: null,
    courtOutcome: null,
    courtComments: null,
    timeInCustody: null}],
          });
      }
    }
    save() {
        // Simple POST request with a JSON body using fetch
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.state.address[0])
        };
        fetch('http://localhost:8881/jso-bo/JSOEntry/add', requestOptions)
            .then(response => response.json())
            .then(dummy => { alert("Row Saved");});
    }
    myChangeHandlerTitle = (event) => {
        this.setState({address:[{ 
			title: event.target.value,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody       }]});
        console.log('title=' + this.state.address[0].title)
      }
      myChangeHandlerFirstname = (event) => {
        this.setState({address:[{ 
			title: this.state.address[0].title,
            firstname: event.target.value,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody }]});
        console.log('firstname=' + this.state.address[0].firstname)
      }
      myChangeHandlerSurname = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: event.target.value,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 
 }]});
        console.log('surname=' + this.state.address[0].surname)
      }
        myChangeHandlerExtension = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: event.target.value,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody       
}]});
        console.log('extension=' + this.state.address[0].extension)
      }        
      myChangeHandlerAofs = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: event.target.value,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody       
}]});
        console.log('aofs=' + this.state.address[0].aofs)
      }
        myChangeHandlerStation = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: event.target.value,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('station=' + this.state.address[0].station)
      }
        myChangeHandlerRelevantDay = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: event.target.value,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			     
 }]});
        console.log('relevantDay=' + this.state.address[0].relevantDay)
      }
        myChangeHandlerRelevantTime = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: event.target.value,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      }]});
        console.log('relevantTime=' + this.state.address[0].relevantTime)
      }
        myChangeHandlerCallDay = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: event.target.value,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('callDay=' + this.state.address[0].callDay)
      }
        myChangeHandlerCallTime = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: event.target.value,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      }]});
        console.log('callTime=' + this.state.address[0].callTime)
      }
        myChangeHandlerReleaseExtension = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: event.target.value,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('releaseExtension=' + this.state.address[0].releaseExtension)
      }
        myChangeHandlerReleaseStation = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: event.target.value,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('releaseStation=' + this.state.address[0].releaseStation)
      }
        myChangeHandlerReleaseCallDay = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: event.target.value,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('releaseCallDay=' + this.state.address[0].releaseCallDay)
      }
        myChangeHandlerReleaseCallTime = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: event.target.value,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('releaseCallTime=' + this.state.address[0].releaseCallTime)
      }
        myChangeHandlerHeld = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: event.target.value,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('held=' + this.state.address[0].held)
      }
      
    myChangeHandlerArrestComments = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: event.target.value,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('arrestComments=' + this.state.address[0].arrestComments)
      }
        myChangeHandlerTeam = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: event.target.value,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('team=' + this.state.address[0].team)
      }
        myChangeHandlerRegion = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: event.target.value,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('region=' + this.state.address[0].region)
      }
        myChangeHandlerSuspectedCourt = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: event.target.value,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('suspectedCourt=' + this.state.address[0].suspectedCourt)
      }
        myChangeHandlerCourtOutcome = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: event.target.value,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('courtOutcome=' + this.state.address[0].courtOutcome)
      }
        myChangeHandlerCourtComments = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
 	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: event.target.value,
    timeInCustody: this.state.address[0].timeInCustody 			      
}]});
        console.log('courtComments=' + this.state.address[0].courtComments)
      }
        myChangeHandlerTimeInCustody = (event) => {
        this.setState({address:[{
			title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
    extension: this.state.address[0].extension,
    aofs: this.state.address[0].aofs,
    station: this.state.address[0].station,
	relevantDay: this.state.address[0].relevantDay,
    relevantTime: this.state.address[0].relevantTime,
    callDay: this.state.address[0].callDay,
    callTime: this.state.address[0].callTime,
	releaseExtension: this.state.address[0].releaseExtension,
    releaseStation: this.state.address[0].releaseStation,
    releaseCallDay: this.state.address[0].releaseCallDay,
    releaseCallTime: this.state.address[0].releaseCallTime,
	held: this.state.address[0].held,
	arrestComments: this.state.address[0].arrestComments,
    team: this.state.address[0].team,
    region: this.state.address[0].region,
    suspectedCourt: this.state.address[0].suspectedCourt,
    courtOutcome: this.state.address[0].courtOutcome,
    courtComments: this.state.address[0].courtComments,
    timeInCustody: this.state.address[0].timeInCustody 			      }]});
        console.log('timeInCustody=' + this.state.address[0].timeInCustody)
      }
 
                                   
  render() {  
return (  
<div className='popup'>  
<div align="center" className='popup\_inner'>  
<h1>{this.props.text}</h1>  
<table border="2px" width="50%"><thead><tr><th>Title</th><th>Firstname</th><th>Surname</th></tr></thead>
<tbody><tr><td><input type="text" defaultValue={this.state.address[0].title} onChange={this.myChangeHandlerTitle}></input></td>
<td><input type="text" defaultValue={this.state.address[0].firstname} onChange={this.myChangeHandlerFirstname}></input></td>
<td><input type="text" defaultValue={this.state.address[0].surname} onChange={this.myChangeHandlerSurname}></input></td>
<td></td>
</tr>
<tr><th>Extension</th><th>aofs</th><th>Station</th><td></td></tr>
<tr>
<td><input type="text" defaultValue={this.state.address[0].extension} onChange={this.myChangeHandlerExtension}></input></td>
<td><input type="text" defaultValue={this.state.address[0].aofs} onChange={this.myChangeHandlerAofs}></input></td>
<td><input type="text" defaultValue={this.state.address[0].station} onChange={this.myChangeHandlerStation}></input></td>
<td></td>
</tr>
<tr><th>RelevantDay</th><th>RelevantTime</th><th>CallDay</th><th>CallTime</th></tr>
<tr>
<td><input type="text" defaultValue={this.state.address[0].relevantDay} onChange={this.myChangeHandlerRelevantDay}></input></td>
<td><input type="text" defaultValue={this.state.address[0].relevantTime} onChange={this.myChangeHandlerRelevantTime}></input></td>
<td><input type="text" defaultValue={this.state.address[0].callDay} onChange={this.myChangeHandlerCallDay}></input></td>
<td><input type="text" defaultValue={this.state.address[0].callTime} onChange={this.myChangeHandlerCallTime}></input></td>
</tr>
<tr><th>ReleaseExtension</th><th>ReleaseStation</th><th>ReleaseCallDay</th><th>ReleaseCallTime</th></tr>
<tr>
<td><input type="text" defaultValue={this.state.address[0].releaseExtension} onChange={this.myChangeHandlerReleaseExtension}></input></td>
<td><input type="text" defaultValue={this.state.address[0].releaseStation} onChange={this.myChangeHandlerReleaseStation}></input></td>
<td><input type="text" defaultValue={this.state.address[0].releaseCallDay} onChange={this.myChangeHandlerReleaseCallDay}></input></td>
<td><input type="text" defaultValue={this.state.address[0].releaseCallTime} onChange={this.myChangeHandlerReleaseCallTime}></input></td>
</tr>
<tr><th>ArrestComments</th><th></th><th></th><th></th></tr>
<tr>
<td colSpan="4"> <textarea
        value={this.state.address[0].arrestComments}
		onChange={this.myChangeHandlerArrestComments}
        rows={2} // Specify the number of visible rows
        cols={120} // Specify the number of visible columns
      /></td>
</tr>
<tr><th>Held</th><th>Team</th><th>Region</th><th>SuspectedCourt</th></tr>
<tr>
<td><input type="text" defaultValue={this.state.address[0].held} onChange={this.myChangeHandlerHeld}></input></td>
<td><input type="text" defaultValue={this.state.address[0].team} onChange={this.myChangeHandlerTeam}></input></td>
<td><input type="text" defaultValue={this.state.address[0].region} onChange={this.myChangeHandlerRegion}></input></td>
<td><input type="text" defaultValue={this.state.address[0].suspectedCourt} onChange={this.myChangeHandlerSuspectedCourt}></input></td>
</tr>
<tr><th>CourtOutcome</th><th>TimeInCustody</th><th></th><th></th></tr>
<tr>
<td><input type="text" defaultValue={this.state.address[0].courtOutcome} onChange={this.myChangeHandlerCourtOutcome}></input></td>
<td><input type="text" defaultValue={this.state.address[0].timeInCustody} onChange={this.myChangeHandlerReleaseTimeInCustody}></input></td>
<td></td>
<td></td>
</tr>
<tr><th>CourtComments</th><th></th><th></th><th></th></tr>
<tr>
<td colSpan="4"><textarea
        value={this.state.address[0].courtComments}
		onChange={this.myChangeHandlerCourtComments}
        rows={2} // Specify the number of visible rows
        cols={120} // Specify the number of visible columns
      /></td>
</tr>

<tr><td></td>
<td></td>
<td></td>
<td><button align="right" onClick={this.props.closePopup}>Cancel</button> 
<button align="right" onClick={this.save} disabled=
{!this.state.address[0].firstname || !this.state.address[0].surname}>Save</button>  </td>
</tr>
</tbody></table> 
</div>  
</div>  
);  
}  
}  

export default Popup;