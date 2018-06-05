import React, { Component } from 'react';
import axios from 'axios';

class UserProfileEdit extends Component {

  state = {
    oldPass: '',
    resdata: {
      name: '',
      currentPassword: '',
      password: '',
      email: '',
      gitHubProfile: '',
      trelloProfile: '',
      slackProfile: ''
    }
  };

  componentWillMount() {
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
            resdata: {
              name: res.name,
              password: res.password,
              email: res.email,
              gitHubProfile: res.gitHubProfile,
              trelloProfile: res.trelloProfile,
              slackProfile: res.slackProfile
            }
          }
          self.setState(newData)
          self.setState({oldPass: newData.resdata.password});
          console.log(res)
        }
      });
  }

  redir = (props) => {
    this.props.history.push('/userProfile');
  };

  onChange = (event) => {
    const state = this.state
    state[event.target.name] = event.target.value;
    this.setState(state);
  }

  newPasswordEquals = () => {
    var newPass = document.getElementById("newPassw").value;
    var confNewPass = document.getElementById("confPassw").value;
    if (newPass === confNewPass) {
      return true
    } else {
      return false;
    }
  }

  passwordEquals = () => {
    var currPass = document.getElementById("currPassw").value;
    var oldPass = this.state.oldPass;
    if (oldPass === currPass) {
      return true;
    } else {
      return false;
    }
  }

  onSubmit = (event) => {
    event.preventDefault();
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }
    if (this.newPasswordEquals() === false || this.passwordEquals() === false) {
      alert("Old password wrong or password confirmation doesn't match.")
      return false;
    } else {
      const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;

      axios.post(`http://127.0.0.1:8080/userprofileupdate`, { name, password, email, gitHubProfile, trelloProfile, slackProfile }, config)
        .then((response) => {
          console.log(response);
          this.setState(response);
          this.redir();
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  }

  render() {
    return (
      <div>
        <p>E-mail address: {this.state.email}</p>
        <form onSubmit={this.onSubmit}>
          <label>
            Username:
          <input type='text' name='name' placeholder={this.state.name} onChange={this.onChange} />
          </label>
          <label>
            Current password:
          <input id='currPassw' type='password' name='currentPassword' placeholder='Current password' />
          </label>
          <label>
            New password:
          <input id='newPassw' type='password' name='password' placeholder='New password' />
          </label>
          <label>
            Confirm password:
          <input id='confPassw' type='password' name='password' placeholder='Confirm password' onChange={this.onChange} />
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
          <button type="submit" className="btn btn-default" onClick={this.onSubmit} >Submit</button>
        </form>
      </div>
    )
  }
}


export default UserProfileEdit;