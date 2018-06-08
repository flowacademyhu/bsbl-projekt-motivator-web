import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, Panel } from 'react-bootstrap';
import Popup from 'reactjs-popup';
import axios from 'axios';

class Groups extends Component {
  constructor () {
    super();
    this.state = {
      response: []
    };
  }

  getGroupInfo () {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;

    axios.get(`http://127.0.0.1:8080/app/currentuser`, config)
      .then((res) => {
        self.setState({ response: res.data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  componentWillMount () {
    this.getGroupInfo();
  }

  renderGroups () {
    var self = this;
    return this.state.response.map((res, i) => {
      return (
        <div key={i}>
          <h3>{res.groupName}</h3>
          {self.renderAdmins(res)}
        </div>
      );
    });
  }

  renderAdmins (res) {
    return res.admins.map((admin, i) => {
      return (
        <div key={i}> {admin} </div>
      );
    });
  }

  render () {
    return (
      <div>
        <h2 div >
          Groups of the user:
        </h2>
        <NavLink to='/groupscreate'><Button bsStyle='danger'>Create New Group</Button></NavLink>
        <Popup trigger={<button>GETTING STARTED</button>} position='botton right  '>
          {close => (
            <div>
              Hi! We are glad to see you joining our motivator application program. Our main aim is to help you succeed at your work,
              and to grow attention on your skills, development and every day routine. Discover the options and changes with this application.
              <a className='close' onClick={close}>
                &times;
              </a>
            </div>
          )}
        </Popup>
        {this.renderGroups()}
      </div>
    );
  }
}

export default Groups;
