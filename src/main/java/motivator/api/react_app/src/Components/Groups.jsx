import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';
/* import axios from 'axios'; */

class Groups extends Component {

  state = {
    Motivator: {
      admins: ['Tasi', 'Feri']
    },
    PubOrder: {
      admins: ['Tyson', 'Bruno']
    }
  };

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

  render() {
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
      </div>
    );
  }
}

export default Groups;
