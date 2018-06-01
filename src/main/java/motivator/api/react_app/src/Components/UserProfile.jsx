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
    axios.get(`http://127.0.0.1:8080/userProfile`)
    .then((result) => {
      if (result.status === 200) {
        this.redir();
      }
    var name = this.state.name;
    var password = this.state.password;
    var email = this.state.email
    var gitHubProfile = this.state.gitHubProfile;
    var trelloProfile = this.state.trelloProfile;
    var slackProfile = this.state.slackProfile;
    });
  }

  redir = (props) => {
    this.props.history.push('/');
  };

  handleChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  handleSubmit = event => {
    event.preventDefault();

    var name = this.state.name;
    var password = this.state.password;
    var email = this.state.email
    var gitHubProfile = this.state.gitHubProfile;
    var trelloProfile = this.state.trelloProfile;
    var slackProfile = this.state.slackProfile;

    axios.get('/userProfile')
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }
  


   /* axios.get(`http://127.0.0.1:8080/userProfileEdit`, { name, password, email, gitHubProfile, trelloProfile, slackProfile })
      .then((result) => {
        if (result.status === 200) {
          this.redir();
        }
      });
  }*/


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
          <NavLink to='/userProfileEdit'><Button bsStyle='success'>Edit</Button></NavLink>
        </label>
      </div>
    )
  }
}


export default UserProfile;