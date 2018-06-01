import React, { Component } from 'react';
import axios from 'axios';

class UserProfileEdit extends Component {

  state = {
    name: '',
    password: '',
    email: '',
    gitHubProfile: '',
    trelloProfile: '',
    slackProfile: ''
  };

  getUserData = () => {
    axios.get(`http://127.0.0.1:8080/userProfileEdit`)
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

    axios.post('/user', { 
      name: this.name,
      password: this.password,
      email: this.email,
      gitHubProfile: this.gitHubProfile,
      trelloProfile: this.trelloProfile,
      slackProfile: this.slackProfile
    })
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  render() {
    return (
      <div>
        <label>
          Username:
          <input type='text' name='name' placeholder={this.state.name}/>
        </label>
        <label>
          New password:
          <input type='password' name='password' />
        </label>
        <label>
          Repeat new password:
          <input type='password' name='password' />
        </label>
        <label>
          E-mail address:
          <input type='text' name='email' placeholder={this.state.email}/>
        </label>
        <label>
          Github URL:
          <input type='text' name='gitHubProfile' placeholder={this.state.gitHubProfile}/>
        </label>
        <label>
          Trello URL:
          <input type='text' name='trelloProfile' placeholder={this.state.trelloProfile}/>
        </label>
        <label>
          Slack URL:
          <input type='text' name='slackProfile' placeholder={this.state.slackProfile}/>
        </label>
        <button type="submit" className="btn btn-default">Submit</button>
      </div>
    )
  }
}


export default UserProfileEdit;