
import React, { Component } from 'react';
import axios from 'axios';
import { NavLink } from 'react-router-dom';
import { Button } from 'react-bootstrap';

class CreateGroup extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      gitHubGrupRep: '',
      trelloGroup: '',
      slackGroupHook: ''
    };
  }

  redir = (props) => {
    this.props.history.push('/home');
  };

  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
};

  onSubmit = (e) => {
    e.preventDefault();
    const { name, gitHubGrupRep, trelloGroup, slackGroupHook } = this.state;

    axios.post(`http://127.0.0.1:8080/currentuser/groups/create`, { name, gitHubGrupRep, trelloGroup, slackGroupHook })
      .then((result) => {
        if (result.status === 200) {
          this.redir();
        }
      });
  }

  render() {
    const { name, gitHubGrupRep, trelloGroup, slackGroupHook } = this.state;
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
                <a href="https://slack.com/oauth/authorize?scope=incoming-webhook,commands&client_id=242600405299.364909370132"><img alt="Add to Slack" height="40" width="139" src="https://platform.slack-edge.com/img/add_to_slack.png" srcset="https://platform.slack-edge.com/img/add_to_slack.png 1x, https://platform.slack-edge.com/img/add_to_slack@2x.png 2x" /></a>
                <label htmlFor="slack">Slack:</label>
                <input type="text" className="form-control" name="slackGroupHook" value={slackGroupHook} onChange={this.onChange} placeholder="Give us a SlackHook" />
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


