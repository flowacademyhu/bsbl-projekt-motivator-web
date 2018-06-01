
import React, { Component } from 'react';
import axios from 'axios';

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

    axios.post(`http://127.0.0.1:8080/groups/create`, { name, gitHubGrupRep, trelloGroup, slackGroupHook })
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
                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Give a name" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">GitHub:</label>
                <input type="text" className="form-control" name="gitHubGrupRe" value={gitHubGrupRe} onChange={this.onChange} placeholder="Give a git" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">Trello:</label>
                <input type="text" className="form-control" name="trelloGroup" value={trelloGroup} onChange={this.onChange} placeholder="Give a trello" />
              </div>
              <div className="form-group">
                <label htmlFor="isbn">Slack:</label>
                <input type="text" className="form-control" name="slackGroupHoo" value={slackGroupHoo} onChange={this.onChange} placeholder="Give a slack" />
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


