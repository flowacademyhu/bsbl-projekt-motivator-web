import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import Popup from 'reactjs-popup';
/* import axios from 'axios'; */

class Groups extends Component {
  constructor () {
    super();
    this.state = {
      Motivator: {
        admins: ['Tasi', 'Feri']
      },
      PubOrder: {
        admins: ['Tyson', 'Bruno']
      }
    };
  }

  /*   getGroupInfo() {
      var self = this;
      axios.get(`http://127.0.0.1:8080/current/groups`)
        .then(function (response) {
          console.log(response);
          self.setState({name: response.data.name})
        })
        .catch(function (error) {
          console.log(error);
        });
    } */

  render () {
    return (
      <div>
        <h2>Groups of the user:</h2>
        {
          Object.keys(this.state).map(groupName => {
            return (
              <div>
                <h3>{groupName}</h3>
                {
                  this.state[groupName].admins.map(adminName => {
                    return (<div>Admin: {adminName}</div>);
                  })
                }
              </div>
            );
          })
        }
        <NavLink to='/groups/create'><Button bsStyle='danger'>Create New Group</Button></NavLink>
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
