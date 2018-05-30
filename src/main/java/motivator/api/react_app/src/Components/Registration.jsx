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
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();

    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;

    axios.post(`http://127.0.0.1:8080/register`, { name, password, email, gitHubProfile, trelloProfile, slackProfile })
      .then((result) => {
          console.log("szarosgatya");
      });
  }

  render() {
    const { name, password, email, gitHubProfile, trelloProfile, slackProfile } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              Registrate a new profile fgt. !
            </h3>
          </div>
          <div className="panel-body">
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
              </div>
              <div className="form-group">
                <label htmlFor="title">Password:</label>
                <input type="password" className="form-control" name="password" value={password} onChange={this.onChange} placeholder="Password" />
              </div>
              <div className="form-group">
                <label htmlFor="publisher">Email:</label>
                <input type="email" className="form-control" name="email" value={email} onChange={this.onChange} placeholder="Email Address" />
              </div>
              <div className="form-group">
                <label htmlFor="author">GitHubProfile:</label>
                <input type="text" className="form-control" name="gitHubProfile" value={gitHubProfile} onChange={this.onChange} placeholder="GitHubProfile" />
              </div>
              <div className="form-group">
                <label htmlFor="author">TrelloProfile:</label>
                <input type="text" className="form-control" name="trelloProfile" value={trelloProfile} onChange={this.onChange} placeholder="TrelloProfile" />
              </div>
              <div className="form-group">
                <label htmlFor="author">SlackProfile:</label>
                <input type="text" className="form-control" name="slackProfile" value={slackProfile} onChange={this.onChange} placeholder="SlackProfile" />
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
