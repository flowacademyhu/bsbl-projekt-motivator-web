import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import Popup from 'reactjs-popup';
import axios from 'axios';

class Groups extends Component {
  constructor () {
    super();
    this.state = {
      response: ''
    };
  }

  /*       Motivator: {
          admins: ['Tasi', 'Feri']
        },
        PubOrder: {
          admins: ['Tyson', 'Bruno']
        }
      };
    } */

  getGroupInfo () {
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/currentuser`, config)
      .then(function (response) {
        console.log(response);
        self.setState({ respone: response.data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  componentDidMount () {
    this.getGroupInfo();
  }

  render () {
    return (
      <div>
        <h2>Groups of the user:</h2>
        <NavLink to='/groupsCreate'><Button bsStyle='danger'>Create New Group</Button></NavLink>
        <Popup trigger={<button>GETTING STARTED</button>} position='top left'>
          {close => (
            <div>
              HI, MAN! YOU ARE ABOUT TO START THIS BEATIFUL APPLICATON, AND I WANT TO EXPLAIN YOU SOME THINGS!
              <a className='close' onClick={close}>
                &times;
              </a>
            </div>
          )}
        </Popup>
      </div>
    );
  }
}

export default Groups;
