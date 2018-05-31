import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';

class Groups extends Component {

  state = {
    Motivator: {
      admins: ['Tasi', 'Feri']
    },
    PubOrder: {
      admins: ['Tyson', 'Bruno']
    }
  };

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

/*   getGroups = () => {
    axios.get(`http://127.0.0.1:8080/current/groups`)
      .then(function (response) {
        console.log(response.data);
        console.log(response.status);
        if (response.status === 200) {
          groupsGet = response.data.map((group) => {
          render() { return ( <li key={group.id}>{group.name} - {group.admin}</li> ) }
          }
        }
      }
    });
  } */

export default Groups;
