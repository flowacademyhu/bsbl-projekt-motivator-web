import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

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
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;

    axios.post('localhost:8080/registration', { name, password, email, gitHubProfile, trelloProfile, slackProfile })
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;
    return (
      <div class="container">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">
              ADD PROFILE
            </h3>
          </div>
          <div class="panel-body">
            <h4><Link to="/"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> Contacts List</Link></h4>
            <form onSubmit={this.onSubmit}>
              <div class="form-group">
                <label for="isbn">Name:</label>
                <input type="text" class="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
              </div>
              <div class="form-group">
                <label for="title">Password:</label>
                <input type="password" class="form-control" name="password" value={password} onChange={this.onChange} placeholder="Password" />
              </div>
              <div class="form-group">
                <label for="publisher">Email:</label>
                <input type="email" class="form-control" name="email" value={email} onChange={this.onChange} placeholder="Email Address" />
              </div>
              <div class="form-group">
                <label for="author">GitHubProfile:</label>
                <input type="text" class="form-control" name="gitHubProfile" value={gitHubProfile} onChange={this.onChange} placeholder="GitHubProfile" />
              </div>
              <div class="form-group">
                <label for="author">TrelloProfile:</label>
                <input type="text" class="form-control" name="trelloProfile" value={trelloProfile} onChange={this.onChange} placeholder="TrelloProfile" />
              </div>
              <div class="form-group">
                <label for="author">SlackProfile:</label>
                <input type="text" class="form-control" name="slackProfile" value={slackProfile} onChange={this.onChange} placeholder="SlackProfile" />
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;