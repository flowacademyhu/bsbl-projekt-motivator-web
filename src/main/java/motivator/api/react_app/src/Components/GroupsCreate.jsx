import React, { Component } from 'react';
import axios from 'axios';

class CreateGroup extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      gitHubGrupRep: '',
      trelloGroup: '',
      slackGroupHook: '',
      owner: '',
      repoName: ''
    };
  }

  redir = (props) => {
    this.props.history.push('/groups');
  };

  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  };

  onSubmit = (e) => {
    e.preventDefault();
    const { name, gitHubGrupRep, trelloGroup, slackGroupHook } = this.state;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }

    axios.post(`http://127.0.0.1:8080/app/currentuser/groups/create`, { name, gitHubGrupRep, trelloGroup, slackGroupHook }, config)
      .then((result) => {
        if (result.status === 200) {
          this.repoSubmit()
        }
      });
  }

  repoSubmit = () => {
    const { repoName, owner } = this.state;
    var token = window.localStorage.getItem('Authorization');
    var config = {
      headers: {
        Authorization: "Bearer " + token
      }
    }

    axios.post(`http://127.0.0.1:8080/app/currentuser/groups/create/repo`, { repoName, owner }, config)
      .then((result) => {
        if (result.status === 200) {
          this.redir();
        }
      });
  }

  render() {
    const { name, gitHubGrupRep, trelloGroup, owner, repoName } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">Create Group</h3>
          </div>
          <div className="panel-body">
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">Name:</label>
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name of the group." />
              </div>
              <div className="form-group">
                <label htmlFor="github">GitHub:</label>
                <input type="text" className="form-control" name="gitHubGrupRep" value={gitHubGrupRep} onChange={this.onChange} placeholder="Give us a GitRep" />
              </div>
              <div className="form-group">
                <label htmlFor="trello">Trello:</label>
                <input type="text" className="form-control" name="trelloGroup" value={trelloGroup} onChange={this.onChange} placeholder="Give us a Trello" />
              </div>
              <div className="form-group">
                <p>Click at the link and create a Slack App if your group doesn't have any!</p>
                <a href="https://slack.com/oauth/authorize?scope=incoming-webhook,commands&client_id=242600405299.364909370132">
                <img alt="Add to Slack" height="40" width="139" src="https://platform.slack-edge.com/img/add_to_slack.png" 
                srcset="https://platform.slack-edge.com/img/add_to_slack.png 1x, https://platform.slack-edge.com/img/add_to_slack@2x.png 2x" /></a>
              </div>
              <h2>Your Repository</h2>
              <div className="form-group">
                <label htmlFor="owner">Owner's name:</label>
                <input type="text" className="form-control" name="owner" value={owner} onChange={this.onChange} placeholder="The repository's owner" />
              </div><div className="form-group">
                <label htmlFor="repoName">Repository's name:</label>
                <input type="text" className="form-control" name="repoName" value={repoName} onChange={this.onChange} placeholder="The repository's name" />
              </div>
              <button type="submit" className="btn btn-default">Submit</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateGroup;

