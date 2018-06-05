import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Button, ButtonToolbar } from 'react-bootstrap';
import axios from 'axios';


class UserProfile extends Component {
  state = {
    name: '',
    password: '',
    email: '',
    gitHubProfile: '',
    trelloProfile: '',
    slackProfile: ''
  };

  getUserData = () => {
  }

  componentDidMount() {
    var self = this;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }
    axios.get(`http://127.0.0.1:8080/app/userprofile`, config)
      .then((result) => {
        if (result.status === 200) {
          var res = result.data;
          var newData = {
            name: res.name,
            currentPassword: res.currentPassword,
            password: res.password,
            email: res.email,
            gitHubProfile: res.gitHubProfile,
            trelloProfile: res.trelloProfile,
            slackProfile: res.slackProfile
          }
          self.setState(newData)
          console.log(res)
        }
      });
  }

  render() {
    return (
      <div>
        <h1>User Profile</h1>
        <label>
          Username: {this.state.name}
        </label>
        <label>
          E-mail address: {this.state.email}
        </label>
        <label>
          Github URL: {this.state.gitHubProfile}
        </label>
        <label>
          Slack URL: {this.state.slackProfile}
        </label>
        <label>
          Trello URL: {this.state.trelloProfile}
        </label>
        <label>
          <NavLink to='/userprofileedit'><Button bsStyle='success'>Edit</Button></NavLink>
        </label>
      </div>
    )
  }
}


export default UserProfile;