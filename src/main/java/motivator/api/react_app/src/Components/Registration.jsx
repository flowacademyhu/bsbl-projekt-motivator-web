import React, { Component } from 'react';
import axios from 'axios';

class Create extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      password: '',
      email: '',
      gitHubProfile: '',
      trelloProfile: '',
      slackProfile: ''
    };
  }

  redir = (props) => {
    this.props.history.push('/login');
  };

  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  };

  onSubmit = (e) => {
    e.preventDefault();
    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;
    if (!this.passwordEquals()) {
      alert("Password confirmation doesn't match.")
      return false;
    } else {
      if (!this.emailRegex()) {
        alert('Please enter a valid email address.');
        return false;
      } else {
        axios.post(`http://127.0.0.1:8080/register`, { name, password, email, gitHubProfile, trelloProfile, slackProfile })
          .then((result) => {
            if (result.status === 200) {
              this.redir();
            }
          });
      }
    }
  }
  emailRegex = () => {
    var email = document.getElementById("email").value;
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;    ;
    if (!re.test(email)) {
      return false;
    }
  }

  passwordEquals = () => {
    var pass = document.getElementById("password").value;
    var confPass = document.getElementById("password2").value;
    if (pass === confPass) {
      return true
    } else {
      return false;
    }
  }

  render() {
    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">Registrate your new profile</h3>
          </div>
          <div className="panel-body">
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Give a name" />
              </div>
              <div className="form-group">
                <label htmlFor="title">Password:</label>
                <input id='password' type="password" className="form-control" name="password" onChange={this.onChange} placeholder="Give a password" />
              </div>
              <div className="form-group">
                <label htmlFor="title">Confirm password:</label>
                <input id='password2' type="password" className="form-control" name="password" onChange={this.onChange} placeholder="Confirm your password" />
              </div>
              <div className="form-group">
                <label htmlFor="publisher">E-mail Address:</label>
                <input id='email' type="email" className="form-control" name="email" value={email} onChange={this.onChange} placeholder="E-mail Address" />
              </div>
              <div className="form-group">
                <label htmlFor="author">GitHub Profile Url:</label>
                <input type="text" className="form-control" name="gitHubProfile" value={gitHubProfile} onChange={this.onChange} placeholder="GitHub Profile Url" />
              </div>
              <div className="form-group">
                <label htmlFor="author">Trello Profile Url:</label>
                <input type="text" className="form-control" name="trelloProfile" value={trelloProfile} onChange={this.onChange} placeholder="Trello Profile Url" />
              </div>
              <div className="form-group">
                <label htmlFor="author">Slack Display Name:</label>
                <input type="text" className="form-control" name="slackProfile" value={slackProfile} onChange={this.onChange} placeholder="Slack Display Name" />
              </div>
              <button type="submit" className="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;
