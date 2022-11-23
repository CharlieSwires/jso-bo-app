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
          address: null,
          homeTel: null,
          workTel: null,
          mobile: null,
          personalEmail: null,
          workEmail: null,
          printable: null}],
        };
        this.search2();
        this.search2 = this.search2.bind(this);
        this.save = this.save.bind(this);
        this.myChangeHandlerTitle = this.myChangeHandlerTitle.bind(this);
        this.myChangeHandlerFirstname = this.myChangeHandlerFirstname.bind(this);
        this.myChangeHandlerSurname = this.myChangeHandlerSurname.bind(this);
        this.myChangeHandlerAddress = this.myChangeHandlerAddress.bind(this);
        this.myChangeHandlerHomeTel = this.myChangeHandlerHomeTel.bind(this);
        this.myChangeHandlerWorkTel = this.myChangeHandlerWorkTel.bind(this);
        this.myChangeHandlerMobile = this.myChangeHandlerMobile.bind(this);
        this.myChangeHandlerPersonalEmail = this.myChangeHandlerPersonalEmail.bind(this);
        this.myChangeHandlerWorkEmail = this.myChangeHandlerWorkEmail.bind(this);
        }
    search2() {

        console.log("search2 " + this.props.firstname + ', ' + this.props.surname);
        if (this.props.firstname != null && this.props.surname != null){
  
          // eslint-disable-next-line
          const testURL = 'http://localhost:8887/address-book/AddressEntry/get' + '/' + this.props.firstname + '/' + this.props.surname;
        
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
            address: null,
            homeTel: null,
            workTel: null,
            mobile: null,
            personalEmail: null,
            workEmail: null,
            printable: null}],
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
        fetch('http://localhost:8887/address-book/AddressEntry/add', requestOptions)
            .then(response => response.json())
            .then(dummy => { alert("File Saved");});
    }
    myChangeHandlerTitle = (event) => {
        this.setState({address:[{ title: event.target.value,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
        }]});
        console.log('title=' + this.state.address[0].title)
      }
      myChangeHandlerFirstname = (event) => {
        this.setState({address:[{ title: this.state.address.title,
            firstname: event.target.value,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('firstname=' + this.state.address[0].firstname)
      }
      myChangeHandlerSurname = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: event.target.value,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
           }]});
        console.log('surname=' + this.state.address[0].surname)
      }
      myChangeHandlerAddress = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: event.target.value,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('address=' + this.state.address[0].address)
      }
      myChangeHandlerHomeTel = (event) => {
        this.setState({address:[{ title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: event.target.value,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('homeTel=' + this.state.address[0].homeTel)
      }
      myChangeHandlerWorkTel = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: event.target.value,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('workTel=' + this.state.address[0].workTel)
      }
      myChangeHandlerMobile = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: event.target.value,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('mobile=' + this.state.address[0].mobile)
      }
      myChangeHandlerPersonalEmail = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: event.target.value,
            workEmail: this.state.address[0].workEmail,
            printable: this.state.address[0].printable
            }]});
        console.log('personalEmail=' + this.state.address[0].personalEmail)
      }
      myChangeHandlerWorkEmail = (event) => {
        this.setState({address:[{title: this.state.address[0].title,
            firstname: this.state.address[0].firstname,
            surname: this.state.address[0].surname,
            address: this.state.address[0].address,
            homeTel: this.state.address[0].homeTel,
            workTel: this.state.address[0].workTel,
            mobile: this.state.address[0].mobile,
            personalEmail: this.state.address[0].personalEmail,
            workEmail: event.target.value,
            printable: this.state.address[0].printable
            }]});
        console.log('workEmail=' + this.state.address[0].workEmail)
      }
                                   
  render() {  
return (  
<div className='popup'>  
<div align="center" className='popup\_inner'>  
<h1>{this.props.text}</h1>  
<table border="2px" width="50%"><thead><tr><th>Title</th><th>Firstname</th><th>Surname</th></tr></thead>
<tbody><tr><td><input type="text" defaultValue={this.state.address[0].title} onChange={this.myChangeHandlerTitle}></input></td>
<td><input type="text" defaultValue={this.state.address[0].firstname} onChange={this.myChangeHandlerFirstname}></input></td>
<td><input type="text" defaultValue={this.state.address[0].surname} onChange={this.myChangeHandlerSurname}></input></td></tr>
<tr><th colSpan="3">Address</th></tr>
<tr><td width="100%" colSpan="3"><input size="100" type="text" defaultValue={this.state.address[0].address} onChange={this.myChangeHandlerAddress}></input></td></tr>
<tr><th>HomeTel</th><th>WorkTel</th><th>Mobile</th></tr>
<tr><td><input type="text" defaultValue={this.state.address[0].homeTel} onChange={this.myChangeHandlerHomeTel}></input></td>
<td><input type="text" defaultValue={this.state.address[0].workTel} onChange={this.myChangeHandlerWorkTel}></input></td>
<td><input type="text" defaultValue={this.state.address[0].mobile} onChange={this.myChangeHandlerMobile}></input></td></tr>
<tr><th align="center" colSpan="2">PersonalEmail</th><th>WorkEmail</th></tr>
<tr><td align="center" colSpan="2"><input type="text" defaultValue={this.state.address[0].personalEmail} onChange={this.myChangeHandlerPersonalEmail}></input></td>
<td><input type="text" defaultValue={this.state.address[0].workEmail} onChange={this.myChangeHandlerWorkEmail}></input></td></tr>
<tr><td></td>
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