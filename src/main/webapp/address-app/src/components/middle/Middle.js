import './Middle.css';
import React, { Component } from 'react';
import Popup from '../popup/Popup';

class Middle extends Component {
  testURL = '';
  constructor(props) {
    super(props);
    this.state =
    {
      showPopup: false,
      addresses: [],
      o1: null,
      o2: null,
      firstname: null,
      surname: null
    };
    this.search();
    this.search = this.search.bind(this);
    this.newItem = this.newItem.bind(this);
    this.closeItem = this.closeItem.bind(this);
    this.updateItem = this.updateItem.bind(this);
    this.print = this.print.bind(this);
    this.myChangeHandlero1 = this.myChangeHandlero1.bind(this);
    this.myChangeHandlero2 = this.myChangeHandlero2.bind(this);
  }
  
  search() {

    console.log("search");
    this.setState({
      showPopup: false
    });
    if (this.state.o1 != null && this.state.o2 != null && this.state.o1.trim() != '' && this.state.o2.trim() != '') {
      // eslint-disable-next-line
      this.testURL = 'http://localhost:8887/address-book/AddressEntry/get' + '/' + this.state.o1 + '/' + this.state.o2;
    } else if (this.state.o2 != null && this.state.o2.trim() != '') {
      // eslint-disable-next-line
      this.testURL = 'http://localhost:8887/address-book/AddressEntry/get' + '/' + this.state.o2;
    } else if (this.state.o1 != null && this.state.o1.trim() != '') {
      // eslint-disable-next-line
      this.testURL = 'http://localhost:8887/address-book/AddressEntry/getFirst' + '/' + this.state.o1;
    } else {
      // eslint-disable-next-line
      this.testURL = 'http://localhost:8887/address-book/AddressEntry/getAll';
    }
    console.log("testURL", this.testURL);

    const myInit = {
      method: 'GET',
      // mode: 'no-cors',
      headers: {
        'content-type': 'application/json'
      }
    };

    const myRequest = new Request(this.testURL, myInit);
    fetch(myRequest)
      .then(async response => {
        const data = await response.json();
        this.setState({ addresses: data });
      })
      .catch(error => {
        console.log(error)
      });
  }
  newItem() {
    console.log("new");
    this.setState({
      showPopup: true, firstname: null, surname: null
    });
  }
  closeItem() {
    console.log("new");
    this.setState({
      showPopup: false
    });
  }
  deleteItem(forename, surname) {
    console.log("delete");
    return function() {
      // eslint-disable-next-line
      const testURL = 'http://localhost:8887/address-book/AddressEntry/delete/' + forename + '/' + surname;
      console.log("testURL", testURL);

      const myInit = {
        method: 'DELETE',
        // mode: 'no-cors',
        headers: {
          'content-type': 'application/json'
        }

      };

      const myRequest = new Request(testURL, myInit);
      fetch(myRequest)
        .then(async response => {
          const data = await response.json();
          //this.setState({addresses: data});
        })
        .catch(error => {
          console.log(error)
        });
      }
  }
  print() {
        console.log("print");

        // Simple POST request with a JSON body using fetch
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.state.addresses)
        };
        fetch('http://localhost:8887/address-book/AddressEntry/print', requestOptions)
             .then(response => response.blob())
  .then(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.style.display = 'none';
    a.href = url;
    // the filename you want
    a.download = 'result.pdf';
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    alert('your file has downloaded!'); // or you know, something with better UX...
  })    
      .catch(error => {
        console.log(error)
      });
    }
  
  
  
       
  
  updateItem = function(forename,surname) {
    console.log("update");
    return function(){this.setState({showPopup: true, firstname: forename, surname: surname})};
  }
  myChangeHandlero1 = (event) => {
    this.setState({ o1: event.target.value });
    console.log('o1=' + this.state.o1)
  }
  myChangeHandlero2 = (event) => {
    this.setState({ o2: event.target.value });
    console.log('o2=' + this.state.o2)
  }

  render() {

    const headings = [
      "title",
      "firstname",
      "surname",
      "address",
      "homeTel",
      "workTel",
      "mobile",
      "personalEmail",
      "workEmail"];
    const headingItems = headings.map((d, index) => <th key={index}>{d}</th>);

    const headingLine = <tr>{headingItems}<th>update</th><th>delete</th></tr>
    return (
      <div>
        <button onClick={this.newItem}>New</button>
        <button onClick={this.search}>Search</button>
        <input type="text" placeholder="Forename" onChange={this.myChangeHandlero1} />
        <input type="text" placeholder="Surname" onChange={this.myChangeHandlero2} />
        <button onClick={this.print}>Print Labels</button>
        {this.state.showPopup ?
          <Popup
            text='Update/New Entry Pop Up'
            firstname={this.state.firstname}
            surname={this.state.surname}
            closePopup={this.closeItem} /> :
          <table width="100%">
            <thead>{headingLine}</thead>
            <tbody>{this.state.addresses.map((d, index) => <tr key={index}><td>{d.title}</td><td>{d.firstname}</td><td>{d.surname}</td><td>{d.address}</td><td>{d.homeTel}</td>
              <td>{d.workTel}</td><td>{d.mobile}</td><td>{d.personalEmail}</td><td>{d.workEmail}</td>
              <td><button onClick={this.updateItem(d.firstname, d.surname).bind(this)}>update</button></td>
              <td><button onClick={this.deleteItem(d.firstname, d.surname)}>delete</button></td>
            </tr>)}</tbody>
          </table>
        }
      </div>
    );
  }
}

export default Middle;
