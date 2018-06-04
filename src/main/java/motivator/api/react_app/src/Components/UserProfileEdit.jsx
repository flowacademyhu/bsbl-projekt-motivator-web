import React, { Component } from 'react';
import axios from 'axios';

class UserProfileEdit extends Component {

  state = {
    name: '',
    currentPassword: '',
    password: '',
    email: '',
    gitHubProfile: '',
    trelloProfile: '',
    slackProfile: ''
  };

  getUserData = () => {
    var self = this;
    var token = window.localStorage.getItem(`Authorization`);
    var config = {
      headers: {
        Authorization: `Bearer ` + token
      }
    };
    axios.get(`http://127.0.0.1:8080/userprofile`, config)
      .then((result) => {
        if (result.status === 200) {
          var res = result.data;
          self.state.name = res.name;
          self.state.currentPassword = res.currentPassword;
          self.state.password = res.password;
          self.state.email = res.email;
          self.state.gitHubProfile = res.gitHubProfile;
          self.state.trelloProfile = res.trelloProfile;
          self.state.slackProfile = res.slackProfile;
        }
      });
  }

  redir = (props) => {
    this.props.history.push('/userprofile');
  };

  onChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  onSubmit = event => {
    event.preventDefault();
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }
    axios.post(`/http://127.0.0.1:8080/userprofileedit`, {
      name: this.name,
      password: this.password,
      email: this.email,
      gitHubProfile: this.gitHubProfile,
      trelloProfile: this.trelloProfile,
      slackProfile: this.slackProfile
    }, config)
      .then(function (response) {
        console.log(response);
        this.redir();
      })
      .catch(function (error) {
        console.log(error);
      });

  }

  /*passwordEquals = () => {
    if (this.state.password === this.render.currentPassword) {

    } else {
      this.render();
    }
  }

  newPasswordEquals = () => {
    if (this.render.newPassword === this.render.repeatNewPassword) {
    } else {
      this.render();
    }
  }*/
  render() {
    return (
      <div>
        <form onSubmit={this.onSubmit}>
          <label>
            Username:
          <input type='text' name='name' placeholder={this.state.name} onChange={this.onChange} />
          </label>
          <label>
            Current password:
          <input type='password' name='currentPassword' placeholder='Current password' onChange={this.onChange} />
          </label>
          <label>
            New password:
          <input type='password' name='newPassword' placeholder='New password' onChange={this.onChange} />
          </label>
          <label>
            Repeat new password:
          <input type='password' name='repeatNewPassword' placeholder='Repeat new password' onChange={this.onChange} />
          </label>
          <label>
            E-mail address:
          <input type='text' name='email' placeholder={this.state.email} onChange={this.onChange} />
          </label>
          <label>
            Github URL:
          <input type='text' name='gitHubProfile' placeholder={this.state.gitHubProfile} onChange={this.onChange} />
          </label>
          <label>
            Trello URL:
          <input type='text' name='trelloProfile' placeholder={this.state.trelloProfile} onChange={this.onChange} />
          </label>
          <label>
            Slack URL:
          <input type='text' name='slackProfile' placeholder={this.state.slackProfile} onChange={this.onChange} />
          </label>
          <button type="submit" className="btn btn-default" onClick = {this.onSubmit} >Submit</button>
        </form>
      </div>
    )
  }
}


export default UserProfileEdit;