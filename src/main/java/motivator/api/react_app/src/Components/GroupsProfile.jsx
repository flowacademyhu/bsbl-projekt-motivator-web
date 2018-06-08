import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import axios from 'axios';


class GroupsProfile extends Component {

  state = {
    name: '',
    gitHubGrupRep: '',
    trelloGroup: '',
    slackGroupHook: '',
    members: []
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
          self.setState(newData);
          console.log(res);
          this.getGroupMembersList(config);
        }
      });
  }

  getGroupMembersList = (config) => {
    var self = this;
    axios.get(`http://127.0.0.1:8080/app/currentuser/groups/profile/members`, config)
      .then((response) => {
        if (response.status === 200 || response.status === 202) {
          self.setState({ members: response.data });
        }
      })
  };

  renderMembers () {
    var self = this;
    return self.state.members.map((member, i) => {
      return (
        <div key={i}>
          <ul>{member}</ul>
        </div>
      );
    });
  }

  render () {
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
        <h3>Member of the group</h3>
        <div>{this.renderMembers()}</div>
      </div>
    )
  }
}

export default GroupsProfile;
