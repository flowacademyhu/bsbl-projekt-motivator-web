import React, { Component } from 'react';
import axios from 'axios';

class Slack extends Component {
  constructor() {
    super();
    this.state = {
      TextToSubmit: ''
    };
  }

  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  };

  onSubmit = (e) => {
    e.preventDefault();
    const { TextToSubmit } = this.state;

    axios.post(`http://127.0.0.1:8080/slack`, { TextToSubmit })
      .then((result) => {
        if (result.status === 200) {
          this.redir();
        }
      });
  }

  render() {
    const { TextToSubmit } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">Registrate your new profile</h3>
          </div>
          <div className="panel-body">
            <form onSubmit={this.onSubmit}>
              <div className="form-group">
                <label htmlFor="isbn">TextToSubmit:</label>
                <input type="text" className="form-control" name="TextToSubmit" value={TextToSubmit} onChange={this.onChange} placeholder="Give a name" />
              </div>
              <button type="send" className="btn btn-default">Send</button>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Slack;
