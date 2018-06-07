import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, ButtonToolbar } from 'react-bootstrap';
import axios from 'axios';


class GroupsProfile extends Component {

  state = {
    name: '',
    gitHubGrupRep: '',
    trelloGroup: '',
    slackGroupHook: ''
  };

  componentWillMount() {
    var self = this;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }

  axios.get(`http://127.0.0.1:8080/app/currentuser/groups/profile`, config)
    .then((result) => {
      if (result.status === 200) {
        var res = result.data;
        var newData = {
          name: res.name,
          gitHubGrupRep: res.gitHubGrupRep,
          trelloGroup: res.trelloGroup,
          slackGroupHook: res.slackGroupHook
        }
        self.setState(newData)
        console.log(res)
      }
  });
}

  render() {
    return (
      <div>
        <h1>Group Profile</h1>
        <label><br />
          Group's name: {this.state.name}
        </label><br />
        <label>
          GitHub: {this.state.gitHubGrupRep}
        </label><br />
        <label>
          Trello: {this.state.trelloGroup}
        </label><br />
        <label>
          Slack: {this.state.slackGroupHook}
        </label><br />
        <label>
          <NavLink to='/groupsedit'><Button bsStyle='success'>Edit</Button></NavLink>
        </label>
      </div>
    )
  }
}


export default GroupsProfile;